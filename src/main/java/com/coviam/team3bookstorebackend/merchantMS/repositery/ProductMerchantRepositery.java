package com.coviam.team3bookstorebackend.merchantMS.repositery;

import com.coviam.team3bookstorebackend.merchantMS.entity.ProductMerchant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMerchantRepositery extends CrudRepository<ProductMerchant,String>
{
}
