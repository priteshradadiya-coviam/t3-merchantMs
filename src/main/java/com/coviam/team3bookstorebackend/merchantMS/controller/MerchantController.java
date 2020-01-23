package com.coviam.team3bookstorebackend.merchantMS.controller;


import com.coviam.team3bookstorebackend.merchantMS.dto.*;
import com.coviam.team3bookstorebackend.merchantMS.entity.Merchant;
import com.coviam.team3bookstorebackend.merchantMS.entity.ProductMerchant;
import com.coviam.team3bookstorebackend.merchantMS.service.MerchantService;
import com.coviam.team3bookstorebackend.merchantMS.service.ProductMerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Merchant")
@CrossOrigin(origins = "*")
public class MerchantController
{



    @Autowired
    MerchantService merchantService;

    @Autowired
    ProductMerchantService productMerchantService;

    @PostMapping(value = "/addMerchant")
    public String addMerchant(@RequestBody MerchantDTO merchantDTO)
    {

        Merchant merchant=new Merchant();
        BeanUtils.copyProperties(merchantDTO,merchant);
        Merchant merchantCreated = merchantService.save(merchant);
        return merchantCreated.getMerchantId();

    }

    @PostMapping(value = "/addProductMerchant")
    public String addProductMerchant(@RequestBody ProductMerchantDTO productMerchantDTO)
    {


        ProductMerchant productMerchant=new ProductMerchant();
        System.out.println("COntroller---__>"+productMerchant);
        BeanUtils.copyProperties(productMerchantDTO,productMerchant);
        productMerchantService.save(productMerchant);
        return "success";
        //return new ResponseEntity<ProductMerchant>(productMerchantCreated,HttpStatus.CREATED);
    }

    @GetMapping(value="/getMerchantById/{Id}")
    public Merchant getMerchantById(@PathVariable("Id") String merchant_id)
   {
       Optional<Merchant> optionalCustomer=merchantService.getMerchant(merchant_id);
       Merchant merchant=optionalCustomer.get();
        return  merchant;
    }

    @GetMapping (value = "/getMerchantByProductId/{Id}")
    public ArrayList<Merchant> getMerchantByProductId(@PathVariable("Id") String product_id)
    {
     ArrayList<Merchant> merchantArrayList=new ArrayList<>();
     List<String> merchantIdList= productMerchantService.getMerchantByProductId(product_id);
     for(String merchant_id:merchantIdList)
     {
         merchantArrayList.add(merchantService.getMerchant(merchant_id).get());
     }

     return merchantArrayList;
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

    @GetMapping("/getProductMerchantByMerchantId/{id}")
    public List<ProductMerchant> getProductMerchantByMerchantId(@PathVariable("id") String merchant_id)
    {
        return productMerchantService.findProductMerchant(merchant_id);
    }


    @DeleteMapping("/removeProduct")
    public String removeProduct(@RequestBody RemoveProductDTO removeProductDTO)
    {

        productMerchantService.removeProduct(removeProductDTO);
        return "success";
    }


    @PostMapping("/checkQuantity")
    public boolean checkQuantity(@RequestBody QuantityUpdateDTO quantityUpdateDTO)
    {
        return productMerchantService.checkQuantity(quantityUpdateDTO);
    }

    @PostMapping("/updateQuantity")
    public String updateQuantity(@RequestBody QuantityUpdateDTO quantityUpdateDTO)
    {
        productMerchantService.updateQuantity(quantityUpdateDTO);
        return "updateQuantity";

    }

   // @PostMapping(value = "/addProductRating")



}
