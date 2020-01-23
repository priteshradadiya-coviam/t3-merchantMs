package com.coviam.team3bookstorebackend.merchantMS.entity;


import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@ToString
@Table(name = "MerchantProduct")
@lombok.Getter
@lombok.Setter
public class ProductMerchant
{
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",

            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String productMerchantId;
    @Column(name = "productId", length = 1024)
    private String productId;
    @Column(name = "merchantId", length = 1024)
    private String merchantId;

    @Column(name = "price", length = 1024)
    private String price;
    @Column(name = "quantity", length = 1024)
    private String quantity;

//
//    @Id
//
//    @Id
//
}
