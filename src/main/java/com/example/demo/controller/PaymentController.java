package com.example.demo.controller;


import com.example.demo.dto.PaymentInfos;
import com.example.demo.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/payment")
@AllArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/add")
    public ResponseEntity<String> addPayment(@RequestBody PaymentInfos paymentInfos){
        paymentService.addPayment(paymentInfos);
        return new ResponseEntity<>("Payment added successfully", OK);
    }
}
