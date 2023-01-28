package com.kerimfettahoglu.campaignmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kerimfettahoglu.campaignmanagement.dto.CreateCampaignDto;
import com.kerimfettahoglu.campaignmanagement.entity.Campaign;
import com.kerimfettahoglu.campaignmanagement.enumaration.CategoryEnum;
import com.kerimfettahoglu.campaignmanagement.enumaration.StatusEnum;
import com.kerimfettahoglu.campaignmanagement.repository.CampaignRepository;
import com.kerimfettahoglu.campaignmanagement.repository.StatusRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CampaignService {
	
	private final CampaignRepository campaignRepostiory;
	private final StatusRepository statusRepository;

	public Campaign get(Integer campaignId) {
		Optional<Campaign> campaign = campaignRepostiory.findById(campaignId);
		if (campaign.isPresent())
			return campaign.get();
		else
			throw new RuntimeException("Kampanya bulunamadı.");
	}

	public Campaign create(CreateCampaignDto createCampaignDto) {
		CategoryEnum cat = CategoryEnum.getCategory(createCampaignDto.getCategory());
		Campaign campaign = CreateCampaignDto.mapDtoToObj(createCampaignDto);
		specifyStatus(campaign);
		campaignRepostiory.save(campaign);
		return campaign;
	}
	
	private void specifyStatus(Campaign campaign) {
		if (isMukerrer(campaign)) {
			campaign.setStatus(statusRepository.findById(StatusEnum.MUKERRER.getId()).get());
		} else if (campaign.getCategory().getId().equals(CategoryEnum.HAYAT.getId())) {
			campaign.setStatus(statusRepository.findById(StatusEnum.ONAY_BEKLIYOR.getId()).get());
		} else {
			campaign.setStatus(statusRepository.findById(StatusEnum.AKTIF.getId()).get());
		}
	}
	
	public Boolean isMukerrer(Campaign campaign) {
		List<Campaign> result = campaignRepostiory.findByCategoryAndTitleAndDetails(campaign.getCategory(), campaign.getTitle(), campaign.getDetails());
		if (result.isEmpty())
			return false;
		return true;
	}
}
