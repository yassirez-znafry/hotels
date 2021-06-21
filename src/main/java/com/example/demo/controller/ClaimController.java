package com.example.demo.controller;


import com.example.demo.dto.ClaimInfos;
import com.example.demo.model.Claim;
import com.example.demo.service.ClaimService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/claim")
@AllArgsConstructor
public class ClaimController {
    private final ClaimService claimService;


    @GetMapping(path = "/")
    public List<ClaimInfos> getAllClaims(){
        List<Claim> allClaims = claimService.getAllClaims();
        List<ClaimInfos> allClaimsInfos = new ArrayList<ClaimInfos>();
        for(Claim claim : allClaims){
            ClaimInfos claimInfos = new ClaimInfos();
            claimInfos.setId(claim.getId());
            claimInfos.setContent(claim.getContent());
            claimInfos.setUserId(claim.getUser().getUserId());
            claimInfos.setProcessed(claim.isProcessed());

            allClaimsInfos.add(claimInfos);
        }
        return allClaimsInfos;
    }

    @PostMapping(path = "/")
    public ResponseEntity<String> addClaim(@RequestBody ClaimInfos claimInfos) {
        claimService.addClaim(claimInfos);
        return new ResponseEntity<>("Claim added successfully", OK);
    }

    @GetMapping(path = "/{claim_id}")
    public ClaimInfos getClaim(@PathVariable Long claim_id){
        return claimService.getClaim(claim_id);
    }

    @DeleteMapping(path = "/{claim_id}")
    public void deleteClaim(@PathVariable Long claim_id){
         claimService.deleteClaim(claim_id);
    }

    @PutMapping(path = "/{claim_id}")
    public ResponseEntity<String> updateClaim(@PathVariable Long claim_id, @RequestBody ClaimInfos claimInfos){
        claimService.updateClaim(claim_id, claimInfos);
        return new ResponseEntity<>("Reservation deleted successfully", OK);
    }
}
