package com.coviam.team3bookstorebackend.merchantMS.service;

import com.coviam.team3bookstorebackend.merchantMS.entity.ProductMerchant;

import java.util.List;

public interface ProductMerchantService {
    List<ProductMerchant> save(ProductMerchant productMerchant);

    List<ProductMerchant> findAll();
}
