package edu.miu.cs590.controller;

import edu.miu.cs590.dto.PurchaseRequest;
import edu.miu.cs590.dto.PurchaseResponse;
import edu.miu.cs590.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/failed_purchase")
    PurchaseResponse dummyFailingPurchase(@RequestBody PurchaseRequest request) {
        return purchaseService.purchaseFailurePlaceholder(request);
    }

    @PostMapping
    PurchaseResponse purchase(@RequestBody PurchaseRequest request) {
        return purchaseService.purchase(request);
    }
}
