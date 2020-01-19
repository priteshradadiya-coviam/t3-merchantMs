package com.coviam.team3bookstorebackend.merchantMS.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;


@Getter
@Setter
public class ProductMerchantDTO
{

    String Product_id;
    String merchant_id;
    String price;
    String quantity;

}
