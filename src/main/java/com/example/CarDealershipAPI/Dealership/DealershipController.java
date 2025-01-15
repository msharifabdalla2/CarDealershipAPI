package com.example.CarDealershipAPI.Dealership;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class DealershipController {

    private final DealershipService dealershipService;

    public DealershipController(DealershipService dealershipService) {
        this.dealershipService = dealershipService;
    }

}
