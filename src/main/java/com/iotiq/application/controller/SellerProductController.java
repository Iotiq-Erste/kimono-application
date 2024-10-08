package com.iotiq.application.controller;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Product;
import com.iotiq.application.domain.Seller;
import com.iotiq.application.exception.auth.UnauthorizedException;
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
@PreAuthorize("hasRole('ROLE_COMPANY')")
@RequestMapping("/api/v1/company/{companyId}/products")
public class SellerProductController {

    private ProductService productService;
    private SellerService sellerService;

    @GetMapping
    @PreAuthorize("hasAuthority(@ProductManagementAuth.VIEW)")
    public PagedResponse<ProductResponse> getAll(@PathVariable UUID companyId, ProductFilter filter, Sort sort) {
        checkScope(companyId);

        filter.setSellerIds(List.of(companyId));
        Page<Product> page = productService.getAll(filter, sort);
        List<ProductResponse> responseList = ModelMapperUtil.map(page.getContent(), ProductResponse.class);

        return PagedResponseBuilder.createResponse(page, responseList);
    }

    @GetMapping("/csv-export")
    @PreAuthorize("hasAuthority(@ProductManagementAuth.VIEW)")
    public ResponseEntity<byte[]> export(@PathVariable UUID companyId) throws IOException {
        checkScope(companyId);

        byte[] csvBytes = productService.exportCSVFile();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "products.csv");

        return ResponseEntity.ok()
                .headers(headers)
                .body(csvBytes);
    }

    @PostMapping("/csv-upload")
    public ResponseEntity<ProductCSVUploadResponse> uploadFile(@PathVariable UUID companyId, @RequestParam("file") MultipartFile file) {
        checkScope(companyId);
        return productService.importCSVFile(file);
    }

    @PostMapping
    @PreAuthorize("hasAuthority(@ProductManagementAuth.CREATE)")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductCreateResponse createProduct(@PathVariable UUID companyId, @RequestBody @Valid ProductCreateRequest request) {
        checkScope(companyId);
        return new ProductCreateResponse(productService.createProduct(request).getId());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority(@ProductManagementAuth.DELETE)")
    public void delete(@PathVariable UUID companyId, @PathVariable("id") UUID id) {
        checkScope(companyId);
        productService.delete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority(@ProductManagementAuth.UPDATE)")
    public void update(@PathVariable UUID companyId, @PathVariable("id") UUID id, @RequestBody @Valid ProductUpdateRequest request) {
        checkScope(companyId);
        productService.update(id, request);
    }

    private void checkScope(UUID companyId) {
        Seller currentSeller = sellerService.getCurrentSeller();
        if(currentSeller == null || !Objects.equals(currentSeller.getId(), companyId)) {
            throw new UnauthorizedException(Seller.ENTITY_NAME, companyId);
        }
    }

}
