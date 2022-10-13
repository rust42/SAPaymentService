package edu.miu.cs590.controller;

import edu.miu.cs590.dto.PurchaseRequest;
import edu.miu.cs590.dto.PurchaseResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {

    @PostMapping
    PurchaseResponse purchase(@RequestBody PurchaseRequest request) {
        return new PurchaseResponse();
    }
}
