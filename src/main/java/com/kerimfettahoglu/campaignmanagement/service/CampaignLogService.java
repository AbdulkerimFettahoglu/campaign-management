package com.kerimfettahoglu.campaignmanagement.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kerimfettahoglu.campaignmanagement.dto.CampaignHistoryItemDto;
import com.kerimfettahoglu.campaignmanagement.entity.Campaign;
import com.kerimfettahoglu.campaignmanagement.entity.CampaignLog;
import com.kerimfettahoglu.campaignmanagement.enumaration.CategoryEnum;
import com.kerimfettahoglu.campaignmanagement.enumaration.StatusEnum;
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
	
	public List<CampaignHistoryItemDto> getHistory(Integer id) {
		List<CampaignLog> result = campaignLogRepository.findByIdOrderByLogDate(id);
		return result.stream().map(logItem -> {
			CampaignHistoryItemDto dto = CampaignHistoryItemDto.builder()
					.category(CategoryEnum.getCategory(logItem.getCategory()).getDescription())
					.details(logItem.getDetails())
					.id(logItem.getId())
					.log_id(logItem.getLog_id())
					.logDate(logItem.getLogDate())
					.status(StatusEnum.getStatus(logItem.getStatus()).getDescription())
					.title(logItem.getTitle())
					.build();
			return dto;
		}).collect(Collectors.toList());
	}
}
