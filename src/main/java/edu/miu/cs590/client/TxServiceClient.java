package edu.miu.cs590.client;

import edu.miu.cs590.dto.TxRequestDto;
import edu.miu.cs590.dto.Transaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@FeignClient(name = "${tx.service.name}", url = "${tx.service.url}")

public interface TxServiceClient {
    @PostMapping("/transactions")
    ResponseEntity<Transaction>createTransaction(@RequestHeader("Authorization") String authorization, @RequestBody TxRequestDto txRequest);

    @PutMapping("/transactions/{transactionId}/update-status")
    ResponseEntity<Transaction>updateStatus(@RequestHeader("Authorization") String authorization, @PathVariable String transactionId, @RequestBody Map<String, String> status);
}
