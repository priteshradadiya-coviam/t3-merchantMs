package com.coviam.team3bookstorebackend.merchantMS.service.serviceimpl;

import com.coviam.team3bookstorebackend.merchantMS.dto.RemoveProductDTO;
import com.coviam.team3bookstorebackend.merchantMS.entity.ProductMerchant;
import com.coviam.team3bookstorebackend.merchantMS.repositery.ProductMerchantRepositery;
import com.coviam.team3bookstorebackend.merchantMS.service.ProductMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class ProductMerchantServiceImpl implements ProductMerchantService
{
    @Autowired
    ProductMerchantRepositery productMerchantRepositery;

    @Override
    public List<ProductMerchant> save(ProductMerchant productMerchant) {
        String m_id = productMerchant.getMerchant_id();
        productMerchantRepositery.save(productMerchant);
        ArrayList<String> productMerchants=new ArrayList<String>(Arrays.asList("m_id"));

        

        return (ArrayList<ProductMerchant>) productMerchantRepositery.findAllById(productMerchants);

    }

    @Override
    public List<ProductMerchant> findAll() {
        return (ArrayList<ProductMerchant>)productMerchantRepositery.findAll();
    }


    @Override
    public List<ProductMerchant> removeProduct(RemoveProductDTO removeProductDTO) {
        productMerchantRepositery.deleteById(removeProductDTO.getProduct_id());
        ArrayList<String> productMerchants=new ArrayList<String>(Arrays.asList(removeProductDTO.getMerchant_id()));
        return (List<ProductMerchant>) productMerchantRepositery.findAllById(productMerchants);
    }
}
