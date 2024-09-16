package com.iotiq.application.service;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Seller;
import com.iotiq.application.messages.seller.SellerCreateRequest;
import com.iotiq.application.messages.seller.SellerCreateResponse;
import com.iotiq.application.messages.seller.SellerUpdateRequest;
import com.iotiq.application.repository.SellerRepository;
import com.iotiq.user.domain.User;
import com.iotiq.user.internal.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;
    private final UserService userService;

    @Transactional
    public Seller getCurrentSeller() {
        User currentUser = userService.getCurrentUser();
        return sellerRepository.findByUser(currentUser).orElseGet(() -> createDefaultSeller(currentUser));
    }


    public Seller createDefaultSeller(User currentUser) {
        Seller seller = new Seller();
        seller.setUser(currentUser);
        seller.setActive(true);
        return sellerRepository.save(seller);
    }

    @Transactional
    public SellerCreateResponse createSeller(SellerCreateRequest request) {
        User user = userService.getCurrentUser();
        Seller seller = sellerRepository.save(ModelMapperUtil.map(request, Seller.class));
        seller.setActive(true);
        seller.setUser(user);
        return new SellerCreateResponse(seller.getId());
    }

    @Transactional
    public void inactiveSeller() {
        Seller currentSeller = getCurrentSeller();
        currentSeller.setActive(false);
        sellerRepository.save(currentSeller);
    }

    @Transactional
    public void update(SellerUpdateRequest request){
        Seller currentSeller = getCurrentSeller();
        ModelMapperUtil.map(request,currentSeller);

        sellerRepository.save(currentSeller);
    }
}
