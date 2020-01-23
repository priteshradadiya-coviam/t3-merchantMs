package com.coviam.team3bookstorebackend.merchantMS.service.serviceimpl;

import com.coviam.team3bookstorebackend.merchantMS.dto.ProductDetailsDTO;
import com.coviam.team3bookstorebackend.merchantMS.dto.ProductMerchantDTO;
import com.coviam.team3bookstorebackend.merchantMS.dto.QuantityUpdateDTO;
import com.coviam.team3bookstorebackend.merchantMS.dto.RemoveProductDTO;
import com.coviam.team3bookstorebackend.merchantMS.entity.ProductMerchant;
import com.coviam.team3bookstorebackend.merchantMS.repositery.ProductMerchantRepositery;
import com.coviam.team3bookstorebackend.merchantMS.service.ProductMerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class ProductMerchantServiceImpl implements ProductMerchantService
{
    @Autowired
    ProductMerchantRepositery productMerchantRepositery;


    @Override
    public void save(ProductMerchant productMerchant) {


        ArrayList<ProductMerchant> productMerchantArrayList=(ArrayList<ProductMerchant>)productMerchantRepositery.findAll();
        productMerchantArrayList=(ArrayList<ProductMerchant>)productMerchantArrayList.stream().filter(productMerchant1 -> {
            return (productMerchant1.getProductId().equals(productMerchant.getProductId())
                    && productMerchant1.getMerchantId().equals(productMerchant.getMerchantId()));})
                .collect(Collectors.toList());

        if(productMerchantArrayList.size()==0)
        {
            System.out.println("new product added------------");
            productMerchantRepositery.save(productMerchant);
        }
        else
        {
            ProductMerchant productMerchant1=new ProductMerchant();

            BeanUtils.copyProperties(productMerchant,productMerchant1);
            productMerchant1.setProductMerchantId(productMerchantArrayList.get(0).getProductMerchantId());
            System.out.println("-----------_Updating-------"+productMerchant1);
            System.out.println("ProductMerchantId:----"+productMerchantArrayList.get(0).getProductMerchantId());
            productMerchantRepositery.save(productMerchant1);
        }

    }

    @Override
    public List<ProductMerchant> findAll() {
        return (ArrayList<ProductMerchant>)productMerchantRepositery.findAll();
    }


    @Override
    public void removeProduct(RemoveProductDTO removeProductDTO) {


        productMerchantRepositery.deleteByProductId(removeProductDTO.getProductId());

//        ArrayList<ProductMerchant> productMerchantArrayList=(ArrayList<ProductMerchant>)productMerchantRepositery.findAll();
//        productMerchantArrayList.stream().filter(productMerchant1 -> removeProductDTO.getMerchantId().equals(productMerchant1.getMerchantId()))
//                //.map(productMerchant1 -> productMerchant1.getProductId() )
//                .collect(Collectors.toList());


        //return (List<ProductMerchant>) productMerchantRepositery.findAllById(productMerchants);


//        ArrayList<String> productMerchants=new ArrayList<String>(Arrays.asList(removeProductDTO.getMerchantId()));
    }

    @Override
    public List<ProductMerchant> findProductMerchant(String merchant_id) {
        ArrayList<ProductMerchant> productMerchantArrayList=(ArrayList<ProductMerchant>)productMerchantRepositery.findAll();
        return productMerchantArrayList.stream().filter(productMerchant1 -> merchant_id.equals(productMerchant1.getMerchantId()))
                //.map(productMerchant1 -> productMerchant1.getProductId() )
                .collect(Collectors.toList());

    }


    @Override
    public boolean checkQuantity(QuantityUpdateDTO quantityUpdateDTO)
    {
        ArrayList<ProductMerchant> productMerchantArrayList=(ArrayList<ProductMerchant>)productMerchantRepositery.findAll();

        ArrayList<ProductMerchant> arrayList=(ArrayList<ProductMerchant>)productMerchantArrayList.stream().filter(productMerchant ->
        {return (productMerchant.getProductId().equals(quantityUpdateDTO.getProductId())
                && productMerchant.getMerchantId().equals(quantityUpdateDTO.getMerchantId()));}).collect(Collectors.toList());

        if(Integer.parseInt(arrayList.get(0).getQuantity())>=Integer.parseInt(quantityUpdateDTO.getQuantity()))
            return true;
        else
            return false;
    }


    @Override
    public void updateQuantity(QuantityUpdateDTO quantityUpdateDTO)
    {

        ArrayList<ProductMerchant> productMerchantArrayList=(ArrayList<ProductMerchant>)productMerchantRepositery.findAll();

        ArrayList<ProductMerchant> arrayList=(ArrayList<ProductMerchant>)productMerchantArrayList.stream().filter(productMerchant ->
        {return (productMerchant.getProductId().equals(quantityUpdateDTO.getProductId())
                && productMerchant.getMerchantId().equals(quantityUpdateDTO.getMerchantId()));}).collect(Collectors.toList());


        ProductMerchant productMerchant=arrayList.get(0);
        int quantity=Integer.parseInt(productMerchant.getQuantity())-Integer.parseInt(quantityUpdateDTO.getQuantity());

        productMerchant.setQuantity(String.valueOf(quantity));
        productMerchantRepositery.save(productMerchant);


    }


    @Override
    public List<String> getMerchantByProductId(String product_id)
    {
        ArrayList<ProductMerchant> productMerchantArrayList=(ArrayList<ProductMerchant>)productMerchantRepositery.findAll();

        return (ArrayList<String>)productMerchantArrayList.stream().filter(productMerchant ->
        {return (productMerchant.getProductId().equals(product_id)
               );}).map(productMerchant -> productMerchant.getMerchantId()).collect(Collectors.toList());
    }



}
