package com.coviam.team3bookstorebackend.merchantMS.service;

import com.coviam.team3bookstorebackend.merchantMS.dto.ProductMerchantDTO;
import com.coviam.team3bookstorebackend.merchantMS.dto.QuantityUpdateDTO;
import com.coviam.team3bookstorebackend.merchantMS.dto.RemoveProductDTO;
import com.coviam.team3bookstorebackend.merchantMS.entity.ProductMerchant;

import java.util.List;

public interface ProductMerchantService {
    void save(ProductMerchant productMerchant);

    List<ProductMerchant> findAll();

    void removeProduct(RemoveProductDTO removeProductDTO);

    List<ProductMerchant> findProductMerchant(String merchant_id);

    boolean checkQuantity(QuantityUpdateDTO quantityUpdateDTO);

    void updateQuantity(QuantityUpdateDTO quantityUpdateDTO);

    List<String> getMerchantByProductId(String product_id);

}
