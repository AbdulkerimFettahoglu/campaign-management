package com.kerimfettahoglu.campaignmanagement.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.kerimfettahoglu.campaignmanagement.entity.Campaign;
import com.kerimfettahoglu.campaignmanagement.entity.CampaignLog;
import com.kerimfettahoglu.campaignmanagement.repository.CampaignLogRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CampaignLogService {
	
	private final CampaignLogRepository campaignLogRepository;
	
	public void createLogRecord(Campaign campaign) {
		CampaignLog adLog = CampaignLog.builder().id(campaign.getId()).title(campaign.getTitle()).details(campaign.getDetails())
				.status(campaign.getStatus().getId()).category(campaign.getCategory().getId())
				.logDate(new Date(System.currentTimeMillis())).build();
		campaignLogRepository.save(adLog);
	}
}
