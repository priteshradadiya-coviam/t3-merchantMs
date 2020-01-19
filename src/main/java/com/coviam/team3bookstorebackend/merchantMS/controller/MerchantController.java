package com.coviam.team3bookstorebackend.merchantMS.controller;


import com.coviam.team3bookstorebackend.merchantMS.dto.MerchantDTO;
import com.coviam.team3bookstorebackend.merchantMS.dto.MerchantRatingDTO;
import com.coviam.team3bookstorebackend.merchantMS.dto.ProductMerchantDTO;
import com.coviam.team3bookstorebackend.merchantMS.entity.Merchant;
import com.coviam.team3bookstorebackend.merchantMS.entity.ProductMerchant;
import com.coviam.team3bookstorebackend.merchantMS.repositery.MerchantRepositery;
import com.coviam.team3bookstorebackend.merchantMS.service.MerchantService;
import com.coviam.team3bookstorebackend.merchantMS.service.ProductMerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Merchant")
public class MerchantController
{



    @Autowired
    MerchantService merchantService;

    @Autowired
    ProductMerchantService productMerchantService;

    @PostMapping(value = "/addMerchant")
    public ResponseEntity<Merchant> addMerchant(@RequestBody MerchantDTO merchantDTO)
    {
        Merchant merchant=new Merchant();
        BeanUtils.copyProperties(merchantDTO,merchant);
        Merchant merchantCreated = merchantService.save(merchant);
        return new ResponseEntity<Merchant>(merchantCreated,HttpStatus.CREATED);


    }

    @PostMapping(value = "/addProductQuantity")
    public List<ProductMerchant> addProductQuantity(@RequestBody ProductMerchantDTO productMerchantDTO)
    {
        ProductMerchant productMerchant=new ProductMerchant();
        BeanUtils.copyProperties(productMerchantDTO,productMerchant);
        return productMerchantService.save(productMerchant);
        //return new ResponseEntity<ProductMerchant>(productMerchantCreated,HttpStatus.CREATED);
    }

    @GetMapping(value="/getMerchant/{Id}")
    public Merchant getMerchantById(@PathVariable("Id") String merchant_id)
    {
        Optional<Merchant> optionalCustomer=merchantService.getMerchant(merchant_id);
        Merchant merchant=optionalCustomer.get();
        return  merchant;
    }

    @PostMapping(value = "/addMerchantRating")
    public String addRating(@RequestBody MerchantRatingDTO merchantRatingDTO)
    {
        return merchantService.addRating(merchantRatingDTO);

    }


    @GetMapping("/getProductMerchant")
    public List<ProductMerchant> getAllProductMerchant()
    {
        return productMerchantService.findAll();
    }



   // @PostMapping(value = "/addProductRating")



}
