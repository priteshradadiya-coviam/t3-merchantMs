package com.coviam.team3bookstorebackend.merchantMS.service.serviceimpl;

import com.coviam.team3bookstorebackend.merchantMS.dto.MerchantRatingDTO;
import com.coviam.team3bookstorebackend.merchantMS.entity.Merchant;
import com.coviam.team3bookstorebackend.merchantMS.repositery.MerchantRepositery;
import com.coviam.team3bookstorebackend.merchantMS.service.MerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MerchantServiceImpl implements MerchantService
{

    public int count=0;
    @Autowired
    MerchantRepositery merchantRepositery;

    @Override
    public Merchant save(Merchant merchant) {
        return merchantRepositery.save(merchant);
    }

    @Override
    public Optional<Merchant> getMerchant(String merchant_id) {
        return merchantRepositery.findById(merchant_id);
    }

    @Override
    public String addRating(MerchantRatingDTO merchantRatingDTO) {
        String m_id=merchantRatingDTO.getMerchantId();
        Optional<Merchant> merchantOptional=merchantRepositery.findById(m_id);
        Merchant merchant=merchantOptional.get();
        count++;
        double averageRating=Double.parseDouble(merchant.getMerchantRating());

        double newRating = Double.parseDouble(merchantRatingDTO.getMerchantRating());

        double finalRating=(((averageRating*(count-1))+newRating)/count);

        Merchant merchantCreated = new Merchant();

        BeanUtils.copyProperties(merchant,merchantCreated);

        merchantCreated.setMerchantRating(String.valueOf(finalRating));
        merchantRepositery.save(merchantCreated);

        return String.valueOf(finalRating);


    }
}
