package com.iotiq.application.service;

import com.iotiq.application.config.CSVFieldConverter;
import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Product;
import com.iotiq.application.exception.productexceptions.ProductNotFoundException;
import com.iotiq.application.messages.product.ProductCreateRequest;
import com.iotiq.application.messages.product.ProductDto;
import com.iotiq.application.messages.product.ProductFilter;
import com.iotiq.application.messages.product.ProductUpdateRequest;
import com.iotiq.application.repository.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final SellerService sellerService;

    public ProductService(ProductRepository productRepository, SellerService sellerService) {
        this.productRepository = productRepository;
        this.sellerService = sellerService;
    }


    public Page<Product> getAll(@RequestParam ProductFilter filter, Sort sort) {
        return productRepository.findAll(filter.buildSpecification(), filter.buildPageable(sort));
    }

   public Product getOne(UUID id){
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
    }

    @Transactional
    public ProductDto createProduct (@Valid ProductCreateRequest request){

        Product product =ModelMapperUtil.map(request, Product.class);
        product.setSeller(sellerService.getCurrentSeller());
        product = productRepository.save(product);
        return ModelMapperUtil.map(product, ProductDto.class);
    }

    public void delete (UUID id){
        productRepository.deleteById(id);
    }

    @Transactional
    public void update(UUID id, ProductUpdateRequest request){
        Product product = productRepository.findById(id).
                orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));

        ModelMapperUtil.map(request,product);

        productRepository.save(product);
    }

    public void importCSVFile(MultipartFile file) throws IOException, IllegalAccessException {
       List<ProductCreateRequest> productCreateRequestList = CSVFieldConverter.parseCsvToObjectList(file);
       List<Product> productList = ModelMapperUtil.map(productCreateRequestList, Product.class);
       productRepository.saveAll(productList);
    }

    public byte[] exportCSVFile() throws IOException {
        Page<Product> products = getAll(new ProductFilter(),Sort.unsorted());



        String[] HEADERS = CSVFieldConverter.getFieldNames(ProductCreateRequest.class);

        StringWriter sw = new StringWriter();

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .build();

        try (final CSVPrinter printer = new CSVPrinter(sw, csvFormat)) {
            products.getContent().forEach(product -> {
                try {
                    printer.printRecord(CSVFieldConverter.getFieldValues(ModelMapperUtil.map(product, ProductCreateRequest.class)));
                } catch (IOException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        }
        String sww = sw.toString();

       return sww.getBytes();
    }
}
