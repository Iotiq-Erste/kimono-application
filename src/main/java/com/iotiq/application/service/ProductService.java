package com.iotiq.application.service;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.AgeGroup;
import com.iotiq.application.domain.ApplicationAreaGroup;
import com.iotiq.application.domain.Composition;
import com.iotiq.application.domain.Design;
import com.iotiq.application.domain.Haptics;
import com.iotiq.application.domain.MaterialBehavior;
import com.iotiq.application.domain.MaterialParameter;
import com.iotiq.application.domain.Price;
import com.iotiq.application.domain.Product;
import com.iotiq.application.domain.Seller;
import com.iotiq.application.domain.Sustainability;
import com.iotiq.application.domain.enums.AbrassionResistant;
import com.iotiq.application.domain.enums.Absorbency;
import com.iotiq.application.domain.enums.ActiveSubstance;
import com.iotiq.application.domain.enums.ActiveSubstanceArea;
import com.iotiq.application.domain.enums.ActiveSubstancePlacement;
import com.iotiq.application.domain.enums.ActiveSubstanceRelease;
import com.iotiq.application.domain.enums.AdultAgeGroup;
import com.iotiq.application.domain.enums.Antistatic;
import com.iotiq.application.domain.enums.ApplicationArea;
import com.iotiq.application.domain.enums.Breathable;
import com.iotiq.application.domain.enums.Category;
import com.iotiq.application.domain.enums.Certification;
import com.iotiq.application.domain.enums.ChildrenAgeGroup;
import com.iotiq.application.domain.enums.Color;
import com.iotiq.application.domain.enums.Colorfast;
import com.iotiq.application.domain.enums.Currency;
import com.iotiq.application.domain.enums.DesignAppearance;
import com.iotiq.application.domain.enums.DesignBodyPart;
import com.iotiq.application.domain.enums.DesignColor;
import com.iotiq.application.domain.enums.Elasticity;
import com.iotiq.application.domain.enums.EnvironmentalCompatibility;
import com.iotiq.application.domain.enums.FiberType;
import com.iotiq.application.domain.enums.Fineness;
import com.iotiq.application.domain.enums.Gender;
import com.iotiq.application.domain.enums.LifeCycle;
import com.iotiq.application.domain.enums.Lightweight;
import com.iotiq.application.domain.enums.LintFree;
import com.iotiq.application.domain.enums.MoistureTransporting;
import com.iotiq.application.domain.enums.Motif;
import com.iotiq.application.domain.enums.Neurodermatitis;
import com.iotiq.application.domain.enums.OdorNeutralizing;
import com.iotiq.application.domain.enums.OekotexStandard;
import com.iotiq.application.domain.enums.PriceRange;
import com.iotiq.application.domain.enums.ProductCSVHeader;
import com.iotiq.application.domain.enums.Rating;
import com.iotiq.application.domain.enums.Regionality;
import com.iotiq.application.domain.enums.ResourceConsumption;
import com.iotiq.application.domain.enums.ScratchResistant;
import com.iotiq.application.domain.enums.Scratchy;
import com.iotiq.application.domain.enums.SeamFeelable;
import com.iotiq.application.domain.enums.Size;
import com.iotiq.application.domain.enums.Skill;
import com.iotiq.application.domain.enums.SocialEthics;
import com.iotiq.application.domain.enums.Softness;
import com.iotiq.application.domain.enums.SpecificBodyPart;
import com.iotiq.application.domain.enums.SpecificFunctionality;
import com.iotiq.application.domain.enums.Staggering;
import com.iotiq.application.domain.enums.SustainabilityComposition;
import com.iotiq.application.domain.enums.SustainabilityLightweight;
import com.iotiq.application.domain.enums.SweatWicking;
import com.iotiq.application.domain.enums.Uniform;
import com.iotiq.application.domain.enums.UsageCycle;
import com.iotiq.application.domain.enums.Washable;
import com.iotiq.application.exception.CSVWriteException;
import com.iotiq.application.messages.brand.BrandProjection;
import com.iotiq.application.messages.product.ProductCSVUploadResponse;
import com.iotiq.application.messages.product.ProductCreateRequest;
import com.iotiq.application.messages.product.ProductDto;
import com.iotiq.application.messages.product.ProductFilter;
import com.iotiq.application.messages.product.ProductUpdateRequest;
import com.iotiq.application.repository.ProductRepository;
import com.iotiq.application.repository.SellerRepository;
import com.iotiq.commons.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final Validator validator;

    public Page<Product> getAll(@RequestParam ProductFilter filter, Sort sort) {
        return productRepository.findAll(filter.buildSpecification(), filter.buildPageable(sort));
    }

    public Product getOne(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Product.ENTITY_NAME, id));
    }

    @Transactional
    public ProductDto createProductForSeller(@Valid ProductCreateRequest request, Seller seller) {
        Product product = ModelMapperUtil.map(request, Product.class);
        product.setSeller(seller);
        validate(product);

        product = productRepository.save(product);
        return ModelMapperUtil.map(product, ProductDto.class);
    }

    public void delete(UUID id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void update(UUID id, ProductUpdateRequest request) {
        Product product = productRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException(Product.ENTITY_NAME, id));

        ModelMapperUtil.map(request, product);
        validate(product);

        productRepository.save(product);
    }

    public List<BrandProjection> getBrands() {
        return productRepository.findDistinctByBrandIsNotNull();
    }

    public byte[] exportCSVFile(UUID sellerId) throws IOException {
        Page<Product> products = getAll(new ProductFilter().withSellerId(sellerId), Sort.unsorted());

        StringWriter sw = new StringWriter();

        String[] HEADERS = ProductCreateRequest.getCSVHeaders();

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .build();

        try (final CSVPrinter printer = new CSVPrinter(sw, csvFormat)) {
            products.forEach(product -> printProductRecord(printer, product));
        }
        return sw.toString().getBytes();
    }

    private void printProductRecord(CSVPrinter printer, Product product) {
        try {
            printer.printRecord(
                    product.getTitle(),
                    product.getDescription(),
                    product.getPrice().getAmount(),
                    product.getPrice().getCurrency(),
                    product.getImageUrl(),
                    product.getAgeGroup() != null ? product.getAgeGroup().getAdultAgeGroup() : "",
                    product.getAgeGroup() != null ? product.getAgeGroup().getChildrenAgeGroup() : "",
                    product.getApplicationAreaGroup() != null ? product.getApplicationAreaGroup().getApplicationArea() : "",
                    product.getApplicationAreaGroup() != null ? product.getApplicationAreaGroup().getUsageCycle() : "",
                    product.getBrand(),
                    product.getCategory(),
                    separateWithSemicolon(product.getCertifications()),
                    product.getColor(),
                    separateWithSemicolon(product.getComposition() != null ? product.getComposition().getActiveSubstanceAreas() : List.of()),
                    separateWithSemicolon(product.getComposition() != null ? product.getComposition().getActiveSubstances() : List.of()),
                    separateWithSemicolon(product.getComposition() != null ? product.getComposition().getActiveSubstancePlacements() : List.of()),
                    separateWithSemicolon(product.getComposition() != null ? product.getComposition().getActiveSubstanceReleases() : List.of()),
                    separateWithSemicolon(product.getComposition() != null ? product.getComposition().getCompositions() : List.of()),
                    separateWithSemicolon(product.getComposition() != null ? product.getComposition().getStaggerings() : List.of()),
                    product.getDesign() != null ? product.getDesign().getDesignAppearance() : "",
                    product.getDesign() != null ? product.getDesign().getDesignColor() : "",
                    separateWithSemicolon(product.getDesignBodyParts()),
                    separateWithSemicolon((product.getFiberTypes())),
                    product.getGender(),
                    product.getHaptics() != null ? product.getHaptics().getElasticity() : "",
                    product.getHaptics() != null ? product.getHaptics().getFineness() : "",
                    product.getHaptics() != null ? product.getHaptics().getLightweight() : "",
                    product.getHaptics() != null ? product.getHaptics().getLintFree() : "",
                    product.getHaptics() != null ? product.getHaptics().getScratchy() : "",
                    product.getHaptics() != null ? product.getHaptics().getSeamFeelable() : "",
                    product.getHaptics() != null ? product.getHaptics().getSoftness() : "",
                    product.getHaptics() != null ? product.getHaptics().getUniform() : "",
                    product.getMaterialBehavior() != null ? product.getMaterialBehavior().getAbrasionResistant() : "",
                    product.getMaterialBehavior() != null ? product.getMaterialBehavior().getAbsorbency() : "",
                    product.getMaterialBehavior() != null ? product.getMaterialBehavior().getAntistatic() : "",
                    product.getMaterialBehavior() != null ? product.getMaterialBehavior().getBreathable() : "",
                    product.getMaterialBehavior() != null ? product.getMaterialBehavior().getColorfast() : "",
                    product.getMaterialBehavior() != null ? product.getMaterialBehavior().getMoistureTransporting() : "",
                    product.getMaterialBehavior() != null ? product.getMaterialBehavior().getOdorNeutralizing() : "",
                    product.getMaterialBehavior() != null ? product.getMaterialBehavior().getScratchResistant() : "",
                    product.getMaterialBehavior() != null ? product.getMaterialBehavior().getSweatWicking() : "",
                    product.getMaterialBehavior() != null ? product.getMaterialBehavior().getWashable() : "",
                    product.getMaterialParameter() != null ? product.getMaterialParameter().getThickness() : "",
                    product.getMaterialParameter() != null ? product.getMaterialParameter().getWeightPerUnitArea() : "",
                    product.getMaterialParameter() != null ? product.getMaterialParameter().getBreathability() : "",
                    product.getMaterialParameter() != null ? product.getMaterialParameter().getMoistureWicking() : "",
                    product.getMotif(),
                    product.getNeurodermatitis(),
                    product.getOekotexStandard(),
                    product.getPriceRange(),
                    product.getRating(),
                    separateWithSemicolon(product.getSizes()),
                    separateWithSemicolon((product.getSpecificBodyParts())),
                    separateWithSemicolon(product.getSpecificFunctionalities()),
                    separateWithSemicolon(product.getSustainability() != null ? product.getSustainability().getEnvironmentalCompatibilities() : List.of()),
                    separateWithSemicolon(product.getSustainability() != null ? product.getSustainability().getLifeCycles() : List.of()),
                    separateWithSemicolon(product.getSustainability() != null ? product.getSustainability().getRegionalityList() : List.of()),
                    separateWithSemicolon(product.getSustainability() != null ? product.getSustainability().getResourceConsumptions() : List.of()),
                    separateWithSemicolon(product.getSustainability() != null ? product.getSustainability().getSocialEthics() : List.of()),
                    separateWithSemicolon(product.getSustainability() != null ? product.getSustainability().getSustainabilityCompositions() : List.of()),
                    separateWithSemicolon(product.getSustainability() != null ? product.getSustainability().getSustainabilityLightweights() : List.of()),
                    separateWithSemicolon(product.getSustainability() != null ? product.getSustainability().getSkills() : List.of()));
        } catch (IOException e) {
            throw new CSVWriteException(e);
        }
    }

    @Transactional
    public ResponseEntity<ProductCSVUploadResponse> importCSVFile(UUID sellerId, MultipartFile file) {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new EntityNotFoundException(Seller.ENTITY_NAME));

        String[] HEADERS = ProductCreateRequest.getCSVHeaders();
        List<ProductCreateRequest> createRequestList = new ArrayList<>();
        ProductCSVUploadResponse productCSVUploadResponse = new ProductCSVUploadResponse();
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        Set<ConstraintViolation<ProductCreateRequest>> violations = Set.of();
        List<Product> productList = new ArrayList<>();

        try (
                Reader reader = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8);
                CSVParser csvParser = new CSVParser(reader, csvFormat)) {

            Iterable<CSVRecord> records = csvParser.getRecords();

            for (CSVRecord record : records) {
                ProductCreateRequest productRequest = new ProductCreateRequest();
                productRequest.setTitle(parse(String.class, record.get(ProductCSVHeader.TITLE.value())));
                productRequest.setDescription(parse(String.class, record.get(ProductCSVHeader.DESCRIPTION.value())));
                Price price = new Price(parse(BigDecimal.class, record.get(ProductCSVHeader.PRICE_AMOUNT.value())),
                        parse(Currency.class, record.get(ProductCSVHeader.PRICE_CURRENCY.value())));
                productRequest.setPrice(price);
                productRequest.setImageUrl(parse(String.class, record.get(ProductCSVHeader.IMAGE_URL.value())));
                AgeGroup ageGroup = new AgeGroup(parse(AdultAgeGroup.class, record.get(ProductCSVHeader.AGE_GROUP_ADULT_AGE_GROUP.value())),
                        parse(ChildrenAgeGroup.class, record.get(ProductCSVHeader.AGE_GROUP_CHILDREN_AGE_GROUP.value())));
                productRequest.setAgeGroup(ageGroup);
                ApplicationAreaGroup applicationAreaGroup = new ApplicationAreaGroup(parse(ApplicationArea.class, record.get(ProductCSVHeader.APPLICATION_AREA_GROUP_APPLICATION_AREA.value())),
                        parse(UsageCycle.class, record.get(ProductCSVHeader.APPLICATION_AREA_GROUP_USAGE_CYCLE.value())));
                productRequest.setApplicationAreaGroup(applicationAreaGroup);
                productRequest.setBrand(parse(String.class, record.get(ProductCSVHeader.BRAND.value())));
                productRequest.setCategory(parse(Category.class, record.get(ProductCSVHeader.CATEGORY.value())));
                productRequest.setCertifications(parseEnumList(Certification.class, record.get(ProductCSVHeader.CERTIFICATIONS.value())));
                productRequest.setColor(parse(Color.class, record.get(ProductCSVHeader.COLOR.value())));
                Composition composition = new Composition(
                        parseEnumList(ActiveSubstanceArea.class, record.get(ProductCSVHeader.COMPOSITION_ACTIVE_SUBSTANCE_AREAS.value())),
                        parseEnumList(ActiveSubstance.class, record.get(ProductCSVHeader.COMPOSITION_ACTIVE_SUBSTANCES.value())),
                        parseEnumList(ActiveSubstancePlacement.class, record.get(ProductCSVHeader.COMPOSITION_ACTIVE_SUBSTANCE_PLACEMENTS.value())),
                        parseEnumList(ActiveSubstanceRelease.class, record.get(ProductCSVHeader.COMPOSITION_ACTIVE_SUBSTANCE_RELEASES.value())),
                        parseEnumList(com.iotiq.application.domain.enums.Composition.class, record.get(ProductCSVHeader.COMPOSITION_COMPOSITIONS.value())),
                        parseEnumList(Staggering.class, record.get(ProductCSVHeader.COMPOSITION_STAGGERINGS.value())));
                productRequest.setComposition(composition);
                Design design = new Design(
                        parse(DesignAppearance.class, record.get(ProductCSVHeader.DESIGN_DESIGN_APPEARANCE.value())),
                        parse(DesignColor.class, record.get(ProductCSVHeader.DESIGN_DESIGN_COLOR.value())));
                productRequest.setDesign(design);
                productRequest.setDesignBodyParts(parseEnumList(DesignBodyPart.class, record.get(ProductCSVHeader.DESIGN_BODY_PARTS.value())));
                productRequest.setFiberTypes(parseEnumList(FiberType.class, record.get(ProductCSVHeader.FIBER_TYPES.value())));
                productRequest.setGender(parse(Gender.class, record.get(ProductCSVHeader.GENDER.value())));
                Haptics haptics = new Haptics(
                        parse(Elasticity.class, record.get(ProductCSVHeader.HAPTICS_ELASTICITY.value())),
                        parse(Fineness.class, record.get(ProductCSVHeader.HAPTICS_FINENESS.value())),
                        parse(Lightweight.class, record.get(ProductCSVHeader.HAPTICS_LIGHTWEIGHT.value())),
                        parse(LintFree.class, record.get(ProductCSVHeader.HAPTICS_LINT_FREE.value())),
                        parse(Scratchy.class, record.get(ProductCSVHeader.HAPTICS_SCRATCHY.value())),
                        parse(SeamFeelable.class, record.get(ProductCSVHeader.HAPTICS_SEAM_FEELABLE.value())),
                        parse(Softness.class, record.get(ProductCSVHeader.HAPTICS_SOFTNESS.value())),
                        parse(Uniform.class, record.get(ProductCSVHeader.HAPTICS_UNIFORM.value())));
                productRequest.setHaptics(haptics);
                MaterialBehavior materialBehavior = new MaterialBehavior(
                        parse(AbrassionResistant.class, record.get(ProductCSVHeader.MATERIAL_BEHAVIOR_ABRASSION_RESISTANT.value())),
                        parse(Absorbency.class, record.get(ProductCSVHeader.MATERIAL_BEHAVIOR_ABSORBENCY.value())),
                        parse(Antistatic.class, record.get(ProductCSVHeader.MATERIAL_BEHAVIOR_ANTISTATIC.value())),
                        parse(Breathable.class, record.get(ProductCSVHeader.MATERIAL_BEHAVIOR_BREATHABLE.value())),
                        parse(Colorfast.class, record.get(ProductCSVHeader.MATERIAL_BEHAVIOR_COLORFAST.value())),
                        parse(MoistureTransporting.class, record.get(ProductCSVHeader.MATERIAL_BEHAVIOR_MOISTURE_TRANSPORTING.value())),
                        parse(OdorNeutralizing.class, record.get(ProductCSVHeader.MATERIAL_BEHAVIOR_ODOR_NEUTRALIZING.value())),
                        parse(ScratchResistant.class, record.get(ProductCSVHeader.MATERIAL_BEHAVIOR_SCRATCH_RESISTANT.value())),
                        parse(SweatWicking.class, record.get(ProductCSVHeader.MATERIAL_BEHAVIOR_SWEAT_WICKING.value())),
                        parse(Washable.class, record.get(ProductCSVHeader.MATERIAL_BEHAVIOR_WASHABLE.value())));
                productRequest.setMaterialBehavior(materialBehavior);
                MaterialParameter materialParameter = new MaterialParameter(
                        parse(Float.class, record.get(ProductCSVHeader.MATERIAL_PARAMETER_THICKNESS.value())),
                        parse(Float.class, record.get(ProductCSVHeader.MATERIAL_PARAMETER_WEIGHT_PER_UNIT_AREA.value())),
                        parse(Integer.class, record.get(ProductCSVHeader.MATERIAL_PARAMETER_BREATHABILITY.value())),
                        parse(Integer.class, record.get(ProductCSVHeader.MATERIAL_PARAMETER_MOISTURE_WICKING.value())));
                productRequest.setMaterialParameter(materialParameter);
                productRequest.setMotif(parse(Motif.class, record.get(ProductCSVHeader.MOTIF.value())));
                productRequest.setNeurodermatitis(parse(Neurodermatitis.class, record.get(ProductCSVHeader.NEURODERMATITIS.value())));
                productRequest.setOekotexStandard(parse(OekotexStandard.class, record.get(ProductCSVHeader.OEKOTEXSTANDARD.value())));
                productRequest.setPriceRange(parse(PriceRange.class, record.get(ProductCSVHeader.PRICE_RANGE.value())));
                productRequest.setRating(parse(Rating.class, record.get(ProductCSVHeader.RATING.value())));
                productRequest.setSizes(parseEnumList(Size.class, record.get(ProductCSVHeader.SIZES.value())));
                productRequest.setSpecificBodyParts(parseEnumList(SpecificBodyPart.class, record.get(ProductCSVHeader.SPECIFIC_BODY_PARTS.value())));
                productRequest.setSpecificFunctionalities(parseEnumList(SpecificFunctionality.class, record.get(ProductCSVHeader.SPECIFIC_FUNCTIONALITIES.value())));
                Sustainability sustainability = new Sustainability(
                        parseEnumList(EnvironmentalCompatibility.class, record.get(ProductCSVHeader.SUSTAINABILITY_ENVIRONMENTAL_COMPATIBILITIES.value())),
                        parseEnumList(LifeCycle.class, record.get(ProductCSVHeader.SUSTAINABILITY_LIFE_CYCLES.value())),
                        parseEnumList(Regionality.class, record.get(ProductCSVHeader.SUSTAINABILITY_REGIONALITY_LIST.value())),
                        parseEnumList(ResourceConsumption.class, record.get(ProductCSVHeader.SUSTAINABILITY_RESOURCE_CONSUMPTIONS.value())),
                        parseEnumList(SocialEthics.class, record.get(ProductCSVHeader.SUSTAINABILITY_SOCIAL_ETHICS.value())),
                        parseEnumList(SustainabilityComposition.class, record.get(ProductCSVHeader.SUSTAINABILITY_SUSTAINABILITY_COMPOSITIONS.value())),
                        parseEnumList(SustainabilityLightweight.class, record.get(ProductCSVHeader.SUSTAINABILITY_SUSTAINABILITY_LIGHTWEIGHTS.value())),
                        parseEnumList(Skill.class, record.get(ProductCSVHeader.SKILLS.value())));
                productRequest.setSustainability(sustainability);

                createRequestList.add(productRequest);

                violations = validator.validate(productRequest);

                if (!violations.isEmpty()) {
                    String message = record.getRecordNumber() + ", ";
                    List<String> errorField = new ArrayList<>();
                    for (ConstraintViolation<ProductCreateRequest> violation : violations) {
                        errorField.add(violation.getPropertyPath().toString());
                    }
                    message += errorField.stream().sorted()
                            .map(Object::toString)
                            .collect(Collectors.joining(", "));

                    productCSVUploadResponse.getMessages().add(message);
                } else {
                    productList = ModelMapperUtil.map(createRequestList, Product.class).stream()
                            .map(product -> {
                                product.setSeller(seller);
                                return product;
                            })
                            .collect(Collectors.toList());
                }
            }
            if (!productCSVUploadResponse.getMessages().isEmpty()) {
                return ResponseEntity.badRequest().body(productCSVUploadResponse);
            } else {
                productRepository.saveAll(productList);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            productCSVUploadResponse.getMessages().add("Das von Ihnen gesendete CSV-Dateiformat ist nicht geeignet.");
            return ResponseEntity.badRequest().body(productCSVUploadResponse);
        }

    }


    public <T> T parse(Class<T> fieldType, String value) {

        if (fieldType.equals(String.class)) {
            return (T) value;
        } else if (fieldType.equals(BigDecimal.class)) {
            return value != null && !value.isEmpty() ? (T) NumberUtils.parseNumber(value, BigDecimal.class) : null;
        } else if (fieldType.equals(double.class)) {
            return (T) Double.valueOf(value);
        } else if (fieldType.isEnum()) {
            return value != null && !value.isEmpty() ? (T) Enum.valueOf((Class<Enum>) fieldType, value) : null;
        }
        return null;
    }

    private <E extends Enum<E>> List<E> parseEnumList(Class<E> enumType, String value) {
        return value.isEmpty() ? List.of() : Arrays.stream(value.split(";"))
                .map(v -> parse(enumType, v))
                .collect(Collectors.toList());
    }

    private <E extends Enum<E>> String separateWithSemicolon(List<E> enumList) {
        return enumList.stream()
                .map(Object::toString)
                .collect(Collectors.joining(";"));
    }

    private void validate(Product product) {
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
