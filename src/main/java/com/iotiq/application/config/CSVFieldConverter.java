package com.iotiq.application.config;

import com.iotiq.application.messages.product.ProductCreateRequest;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.util.NumberUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CSVFieldConverter {

    public static String[] getFieldNames(Class<?> clazz) {
        return getFieldNamesRecursively(clazz, null).toArray(new String[0]);
    }

    public static List<String> getFieldNamesRecursively(Class<?> clazz, String parentPrefix) {
        Field[] fields = clazz.getDeclaredFields();
        List<String> fieldNames = new java.util.ArrayList<>();

        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = (parentPrefix != null ? parentPrefix + "." : "") + field.getName();
            if (isPrimitive(field.getType())) {
                fieldNames.add(fieldName);
            } else {

                fieldNames.addAll(getFieldNamesRecursively(field.getType(), fieldName));
            }
        }
        return fieldNames;
    }

    public static Object[] getFieldValues(Object obj) throws IllegalAccessException {
        return getFieldValuesRecursively(obj, ";").toArray();
    }

    public static List<Object> getFieldValuesRecursively(Object obj, String separator) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        List<Object> values = new java.util.ArrayList<>();

        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(obj);

            if (isPrimitive(field.getType())) {
                if (value instanceof List<?> list) {
                    // Enum listelerini verilen ayırıcı ile string'e çevir
                    String enumListAsString = list.stream()
                            .map(Object::toString)
                            .collect(Collectors.joining(separator));  // Özel separator kullanılıyor
                    values.add(enumListAsString);
                } else {
                    values.add(value);
                }
            } else {

                values.addAll(getFieldValuesRecursively(value, separator));
            }
        }
        return values;
    }

    private static boolean isPrimitive(Class<?> type) {
        return type.isPrimitive() || type.equals(String.class) || Number.class.isAssignableFrom(type)
                || Boolean.class.isAssignableFrom(type) || type.isEnum() || List.class.isAssignableFrom(type);
    }

    // CSV dosyasını Product objelerine mapleyen metod
    public static List<ProductCreateRequest> parseCsvToObjectList(MultipartFile file) throws IOException {
        List<ProductCreateRequest> createRequestList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));

             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreSurroundingSpaces()
                     .withNullString(""))) {

            // CSV'deki her satırı oku
            for (CSVRecord csvRecord : csvParser) {
                ProductCreateRequest createRequest = new ProductCreateRequest();

                // Her bir CSV başlığını işleyelim
                for (String header : csvParser.getHeaderMap().keySet()) {
                    setField(createRequest, header, csvRecord.get(header));
                }
                createRequestList.add(createRequest);
            }
        }

        return createRequestList;
    }

    private static void setField(Object object, String fieldPath, String value) throws IOException {
        try {
            String[] fieldNames = fieldPath.split("\\.");  // "." ile ayırıyoruz
            Object currentObject = object;  // Ana objeyi tutuyoruz
            Field field;

            // Nokta ile ayrılmış field'ları sırayla çözüyoruz
            for (int i = 0; i < fieldNames.length; i++) {
                String fieldName = fieldNames[i];
                 field = currentObject.getClass().getDeclaredField(fieldName);  // İlgili field'ı al
                field.setAccessible(true);  // Field erişilebilir yap

                // Eğer son field değilse (nested obje ile karşılaşırsak)
                if (i < fieldNames.length - 1) {
                    Object nestedObject = field.get(currentObject);  // Şu anki nested objeyi al

                    if (nestedObject == null) {
                        nestedObject = field.getType().newInstance();  // Nested objeyi yarat
                        field.set(currentObject, nestedObject);  // Ana objeye set et
                    }

                    currentObject = nestedObject;  // İç objeye geçiyoruz
                } else {
                    // Son field'ı set ediyoruz (örneğin categoryName gibi)
                    Object convertedValue = convertToFieldType(field, value);  // Değeri uygun türe çeviriyoruz
                    field.set(currentObject, convertedValue);  // Field'a değeri set et
                }
            }
        } catch (Exception e) {
            throw new IOException("CSV import hatası: " + e.getMessage(), e);
        }
    }

    // Field türüne göre string değeri uygun türe çeviren metod
    private static Object convertToFieldType(Field field, String value) {
        Class<?> fieldType = field.getType();

        if (fieldType.equals(String.class)) {
            return value;  // String olduğu gibi kalır
        } else if (fieldType.equals(BigDecimal.class)) {
            return NumberUtils.parseNumber(value, BigDecimal.class);
        } else if (fieldType.equals(double.class)) {
            return Double.parseDouble(value);  // Double'a çevir
        } else if (fieldType.equals(int.class)) {
            return Integer.parseInt(value);  // Integer'a çevir
        } else if (fieldType.equals(boolean.class)) {
            return Boolean.parseBoolean(value);  // Boolean'a çevir
        } else if (fieldType.isEnum()) {
            // Tek bir enum değeri için
            return value!=null ? Enum.valueOf((Class<Enum>) fieldType, value) : null;
        } else if (List.class.isAssignableFrom(fieldType)) {
            // Eğer alan bir Listeyse
            ParameterizedType listType = (ParameterizedType) field.getGenericType();
            Class<?> listElementType = (Class<?>) listType.getActualTypeArguments()[0];

            if (listElementType.isEnum()) {
                // Liste bir enum listesi ise, değeri ; ile ayırıyoruz ve her birini enum'a çeviriyoruz
                return Arrays.stream(value.split(";"))
                        .map(enumValue -> Enum.valueOf((Class<Enum>) listElementType, enumValue))
                        .collect(Collectors.toList());
            }
        }
        return null;
    }
}