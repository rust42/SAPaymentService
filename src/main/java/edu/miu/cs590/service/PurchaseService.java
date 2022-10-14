package edu.miu.cs590.service;

import edu.miu.cs590.dto.PurchaseRequest;
import edu.miu.cs590.dto.PurchaseResponse;

public interface PurchaseService {
    PurchaseResponse purchaseFailurePlaceholder(PurchaseRequest request);
    PurchaseResponse purchase(PurchaseRequest request);
}
