package com.coviam.team3bookstorebackend.merchantMS.service;

import com.coviam.team3bookstorebackend.merchantMS.dto.MerchantRatingDTO;
import com.coviam.team3bookstorebackend.merchantMS.entity.Merchant;

import java.util.Optional;

public interface MerchantService
{

    Merchant save(Merchant merchant);

    Optional<Merchant> getMerchant(String merchant_id);

    String addRating(MerchantRatingDTO merchantRatingDTO);
}
