package com.iotiq.application.service;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Order;
import com.iotiq.application.domain.Seller;
import com.iotiq.application.messages.seller.SellerDto;
import com.iotiq.application.messages.seller.SellerUpdateRequest;
import com.iotiq.application.repository.SellerRepository;
import com.iotiq.user.domain.User;
import com.iotiq.user.internal.UserService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;
    private final UserService userService;
    private final OrderService orderService;

    public SellerService(SellerRepository sellerRepository, UserService userService,@Lazy OrderService orderService) {
        this.sellerRepository = sellerRepository;
        this.userService = userService;
        this.orderService = orderService;
    }

    @Transactional
    public Seller getCurrentSeller() {
        User currentUser = userService.getCurrentUser();
        return sellerRepository.findByUser(currentUser).orElseGet(() -> createDefaultSeller(currentUser));
    }

    @Transactional
    public SellerDto getSeller(){
       SellerDto sellerDto = ModelMapperUtil.map(getCurrentSeller(), SellerDto.class);
        sellerDto.setOrders(getLastTwoOrder());
        return sellerDto;
    }

    public Seller createDefaultSeller(User currentUser) {
        Seller seller = new Seller();
        seller.setUser(currentUser);
        seller.setActive(true);
        return sellerRepository.save(seller);
    }

    @Transactional
    public void update(SellerUpdateRequest request){
        Seller currentSeller = getCurrentSeller();
        ModelMapperUtil.map(request,currentSeller);

        sellerRepository.save(currentSeller);
    }

    public List<Order> getLastTwoOrder() {
        return orderService.getOrdersBySeller().stream().sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(2)
                .collect(Collectors.toList());
    }
}
