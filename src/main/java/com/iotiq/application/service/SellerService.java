package com.iotiq.application.service;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Seller;
import com.iotiq.application.messages.orderedproduct.OrderedProductDto;
import com.iotiq.application.messages.seller.SellerDto;
import com.iotiq.application.messages.seller.SellerUpdateRequest;
import com.iotiq.application.repository.SellerRepository;
import com.iotiq.user.domain.User;
import com.iotiq.user.internal.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;
    private final UserService userService;
    private final OrderedProductService orderedProductService;

    @Transactional
    public Seller getCurrentSellerOrCreate() {
        User currentUser = userService.getCurrentUser();
        return sellerRepository.findByUser(currentUser).orElseGet(() -> createDefaultSeller(currentUser));
    }

    @Transactional
    public SellerDto getSeller() {
        SellerDto sellerDto = ModelMapperUtil.map(getCurrentSellerOrCreate(), SellerDto.class);
        sellerDto.setOrderedProducts(getLastTwoOrder());
        return sellerDto;
    }

    @Transactional
    public Seller createDefaultSeller(User currentUser) {
        Seller seller = new Seller();
        seller.setUser(currentUser);
        seller.setActive(true);
        return sellerRepository.save(seller);
    }

    @Transactional
    public void update(SellerUpdateRequest request) {
        Seller currentSeller = getCurrentSellerOrCreate();
        if(currentSeller.getApplicationAreas() != null) currentSeller.getApplicationAreas().clear();
        if(currentSeller.getSkills() != null) currentSeller.getSkills().clear();
        ModelMapperUtil.map(request, currentSeller);

        sellerRepository.save(currentSeller);
    }

    @Transactional
    public List<OrderedProductDto> getLastTwoOrder() {
        return orderedProductService.getOrderedProducts(getCurrentSellerOrCreate()).stream()
                .sorted(Comparator.comparing(OrderedProductDto::getOrderDate).reversed())
                .limit(2)
                .collect(Collectors.toList());
    }
}
