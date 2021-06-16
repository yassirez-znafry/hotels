package com.example.demo.service;


import com.example.demo.repository.ClaimRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class ClaimService {
    private final ClaimRepository claimRepository;
}
