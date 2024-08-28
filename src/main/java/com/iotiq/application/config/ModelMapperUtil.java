package com.iotiq.application.config;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ModelMapperUtil {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static <D> D map(Object source, Class<D> destinationType) {
        return modelMapper.map(source, destinationType);
    }

    public static void map(Object source, Object destination) {
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true);
        modelMapper.map(source, destination);
    }

    public static <S, T> List<T> map(List<S> source, Class<T> targetClass){
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }


}
