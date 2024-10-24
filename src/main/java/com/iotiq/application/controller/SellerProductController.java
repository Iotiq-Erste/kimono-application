package com.iotiq.application.controller;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Product;
import com.iotiq.application.domain.Seller;
import com.iotiq.application.messages.product.ProductCSVUploadResponse;
import com.iotiq.application.messages.product.ProductCreateRequest;
import com.iotiq.application.messages.product.ProductCreateResponse;
import com.iotiq.application.messages.product.ProductFilter;
import com.iotiq.application.messages.product.ProductResponse;
import com.iotiq.application.messages.product.ProductUpdateRequest;
import com.iotiq.application.service.ProductService;
import com.iotiq.application.service.SellerService;
import com.iotiq.commons.message.response.PagedResponse;
import com.iotiq.commons.message.response.PagedResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/seller/products")
public class SellerProductController {

    private final ProductService productService;
    private final SellerService sellerService;

    @GetMapping
    @PreAuthorize("hasAuthority(@ProductManagementAuth.VIEW) and hasRole('ROLE_COMPANY')")
    public PagedResponse<ProductResponse> getAll(ProductFilter filter, Sort sort) {
        filter.setSellerIds(List.of(Objects.requireNonNull(sellerService.getCurrentSellerOrCreate().getId())));
        Page<Product> page = productService.getAll(filter, sort);
        List<ProductResponse> responseList = ModelMapperUtil.map(page.getContent(), ProductResponse.class);

        return PagedResponseBuilder.createResponse(page, responseList);
    }

    @GetMapping("/csv-export")
    @PreAuthorize("hasAuthority(@ProductManagementAuth.VIEW) and hasRole('ROLE_COMPANY')")
    public ResponseEntity<byte[]> export() throws IOException {

        byte[] csvBytes = productService.exportCSVFile(sellerService.getCurrentSellerOrCreate().getId());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "products.csv");

        return ResponseEntity.ok()
                .headers(headers)
                .body(csvBytes);
    }

    @PostMapping("/csv-upload")
    @PreAuthorize("hasAuthority(@ProductManagementAuth.CREATE)")
    public ResponseEntity<ProductCSVUploadResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        return productService.importCSVFile(sellerService.getCurrentSellerOrCreate().getId(), file);
    }

    @PostMapping
    @PreAuthorize("hasAuthority(@ProductManagementAuth.CREATE)")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductCreateResponse createProduct(@RequestBody @Valid ProductCreateRequest request) {
        Seller currentSeller = sellerService.getCurrentSellerOrCreate();
        return new ProductCreateResponse(productService.createProductForSeller(request, currentSeller).getId());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority(@ProductManagementAuth.DELETE)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority(@ProductManagementAuth.UPDATE)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") UUID id, @RequestBody @Valid ProductUpdateRequest request) {
        productService.update(id, request);
    }
}
