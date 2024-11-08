package com.iotiq.application.service;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Seller;
import com.iotiq.application.messages.orderedproduct.OrderedProductDto;
import com.iotiq.application.messages.seller.SellerDto;
import com.iotiq.application.messages.seller.SellerUpdateRequest;
import com.iotiq.application.repository.SellerRepository;
import com.iotiq.commons.exceptions.EntityNotFoundException;
import com.iotiq.commons.message.request.PageableRequest;
import com.iotiq.user.domain.User;
import com.iotiq.user.internal.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;
    private final UserService userService;
    private final OrderedProductService orderedProductService;

    public Seller getCurrentSeller() {
        User currentUser = userService.getCurrentUser();
        return sellerRepository.findByUser(currentUser).orElseThrow(() -> new EntityNotFoundException(Seller.ENTITY_NAME));
    }

    @Transactional
    public SellerDto getSeller() {
        SellerDto sellerDto = ModelMapperUtil.map(getCurrentSeller(), SellerDto.class);
        sellerDto.setOrderedProducts(getLastTwoOrder());
        return sellerDto;
    }

    @Transactional
    public void createIfNotExists(User currentUser) {
        boolean exists = sellerRepository.existsByUser(currentUser);

        if(!exists) {
            createSeller(currentUser);
        }
    }

    @Transactional
    public void createSeller(User currentUser) {
        Seller seller = new Seller();
        seller.setUser(currentUser);
        seller.setActive(true);
        sellerRepository.save(seller);
    }

    @Transactional
    public void update(SellerUpdateRequest request) {
        Seller currentSeller = getCurrentSeller();
        if (request.getApplicationAreas() != null && currentSeller.getApplicationAreas() != null)
            currentSeller.getApplicationAreas().clear();
        if (request.getSkills() != null && currentSeller.getSkills() != null)
            currentSeller.getSkills().clear();

        ModelMapperUtil.map(request, currentSeller);

        sellerRepository.save(currentSeller);
    }

    public List<OrderedProductDto> getLastTwoOrder() {

        PageableRequest pageableRequest = new PageableRequest();
        Sort sort = Sort.by(Sort.Direction.DESC, "order.orderDate");

        return orderedProductService.getOrderedProducts(pageableRequest, sort, getCurrentSeller()).stream()
                .collect(Collectors.toList()).reversed();
    }
}
