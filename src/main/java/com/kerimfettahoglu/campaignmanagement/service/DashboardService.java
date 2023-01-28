package com.kerimfettahoglu.campaignmanagement.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kerimfettahoglu.campaignmanagement.enumaration.StatusEnum;
import com.kerimfettahoglu.campaignmanagement.repository.CampaignRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DashboardService {

	private final CampaignRepository campaignRepository;

	public Map<String, String> statistics() {
		Map<String, String> statsDb = new HashMap<>();
		Arrays.stream(StatusEnum.values()).forEach(status -> statsDb.put(status.getDescription(), "0"));;
		campaignRepository.countByStatus().stream().forEach(pair -> {
			statsDb.put(StatusEnum.getStatus((int)pair[0]).getDescription(), pair[1].toString());
		});
		return statsDb;
	}
}
