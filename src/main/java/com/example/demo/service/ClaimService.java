package com.example.demo.service;


import com.example.demo.dto.ClaimInfos;
import com.example.demo.model.*;
import com.example.demo.repository.ClaimRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ClaimService {
    private final ClaimRepository claimRepository;
    private final AuthService authService;

    public List<Claim> getAllClaims(){
        List<Claim> allClaims = claimRepository.findAll();
        return allClaims;
    }


    public Claim addClaim(ClaimInfos claimInfos){
        Claim claim = new Claim();
        claim.setContent(claimInfos.getContent());
        claim.setProcessed(claimInfos.isProcessed());
        claim.setUser(authService.getCurrentUser());

        System.out.println("-------- ATTENTION ---------------" + claim.getId() + " - " + claim.getContent() + " - " + claim.getUser().getUserId());

        return claimRepository.save(claim);
    }

    public ClaimInfos getClaim(Long claim_id){
        Claim claim = claimRepository.findById(claim_id).get();
        ClaimInfos claimInfos = new ClaimInfos();
        claimInfos.setContent(claim.getContent());
        claimInfos.setProcessed(claim.isProcessed());
        claimInfos.setUserId(claim.getUser().getUserId());
        claimInfos.setId(claim.getId());
        return claimInfos;
    }

    public void deleteClaim(Long claim_id){
         claimRepository.deleteById(claim_id);
    }

    public Claim updateClaim(Long claim_id, ClaimInfos claimInfos){
        Claim claim = new Claim();
        claim.setContent(claimInfos.getContent());
        claim.setProcessed(claimInfos.isProcessed());
        claim.setUser(authService.getCurrentUser());
        claim.setId(claimInfos.getId());

        return claimRepository.save(claim);
    }
}
