package com.kerimfettahoglu.campaignmanagement.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kerimfettahoglu.campaignmanagement.entity.Campaign;
import com.kerimfettahoglu.campaignmanagement.repository.CampaignRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CampaignService {
	
	private final CampaignRepository campaignRepostiory;

	public Campaign get(Integer campaignId) {
		Optional<Campaign> campaign = campaignRepostiory.findById(campaignId);
		if (campaign.isPresent())
			return campaign.get();
		else
			throw new RuntimeException("Kampanya bulunamadı.");
	}

}
