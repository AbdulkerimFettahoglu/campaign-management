package com.kerimfettahoglu.campaignmanagement.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.kerimfettahoglu.campaignmanagement.dto.CreateCampaignDto;
import com.kerimfettahoglu.campaignmanagement.entity.Campaign;
import com.kerimfettahoglu.campaignmanagement.entity.Category;
import com.kerimfettahoglu.campaignmanagement.enumaration.CategoryEnum;
import com.kerimfettahoglu.campaignmanagement.enumaration.StatusEnum;
import com.kerimfettahoglu.campaignmanagement.repository.CampaignRepository;
import com.kerimfettahoglu.campaignmanagement.repository.CategoryRepository;
import com.kerimfettahoglu.campaignmanagement.repository.StatusRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CampaignService {
	
	private final CampaignRepository campaignRepository;
	private final StatusRepository statusRepository;
	private final CategoryRepository categoryRepostiory;
	private final CampaignLogService campaignLogService;

	public Campaign get(Integer campaignId) {
		Optional<Campaign> campaign = campaignRepository.findById(campaignId);
		if (campaign.isPresent())
			return campaign.get();
		else
			throw new RuntimeException("Kampanya bulunamadı.");
	}

	@Transactional
	public Campaign create(CreateCampaignDto createCampaignDto) {
		Campaign campaign = CreateCampaignDto.mapDtoToObj(createCampaignDto);
		specifyCategory(campaign, createCampaignDto);
		specifyStatus(campaign);
		campaignRepository.save(campaign);
		campaignLogService.createLogRecord(campaign);
		return campaign;
	}
	
	private void specifyCategory(Campaign campaign, CreateCampaignDto dto) {
		Optional<Category> cat = categoryRepostiory.findById(dto.getCategory());
		if (cat.isEmpty())
			throw new RuntimeException("Böyle bir kategori bulunamadı.");
		campaign.setCategory(cat.get());
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
		List<Campaign> result = campaignRepository.findByCategoryAndTitleAndDetails(campaign.getCategory(), campaign.getTitle(), campaign.getDetails());
		if (result.isEmpty())
			return false;
		return true;
	}
	
	@Transactional
	public Boolean activateCampaign(Integer campaignId) {
		Optional<Campaign> optCamp = campaignRepository.findById(campaignId);
		if (optCamp.isPresent() && (StatusEnum.ONAY_BEKLIYOR.getId().equals(optCamp.get().getStatus().getId()))) {
			optCamp.get().setStatus(statusRepository.findById(StatusEnum.AKTIF.getId()).get());
			campaignRepository.save(optCamp.get());
			campaignLogService.createLogRecord(optCamp.get());
			return true;
		} else {
			throw new RuntimeException("Kampanya bulunamadı veya durumu " + StatusEnum.ONAY_BEKLIYOR.getDescription() + " değil.");
		}
	}

	@Transactional
	public Boolean deactivateCampaign(Integer campaignId) {
		Optional<Campaign> optCamp = campaignRepository.findById(campaignId);
		if (optCamp.isPresent() && (StatusEnum.ONAY_BEKLIYOR.getId().equals(optCamp.get().getStatus().getId()) || StatusEnum.AKTIF.getId().equals(optCamp.get().getStatus().getId()))) {
			optCamp.get().setStatus(statusRepository.findById(StatusEnum.DEAKTIF.getId()).get());
			campaignRepository.save(optCamp.get());
			campaignLogService.createLogRecord(optCamp.get());
			return true;
		} else {
			throw new RuntimeException("Kampanya bulunamadı veya durumu " + StatusEnum.ONAY_BEKLIYOR.getDescription() + " ," + StatusEnum.AKTIF.getDescription() + " değil.");
		}
	}
}
