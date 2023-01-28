package com.kerimfettahoglu.campaignmanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kerimfettahoglu.campaignmanagement.entity.Campaign;
import com.kerimfettahoglu.campaignmanagement.service.CampaignService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/campaign")
public class CampaignController {
	
	private final CampaignService campaignService;

	@GetMapping
	public Campaign getCampaign(@RequestParam Integer campaignId) {
		return campaignService.get(campaignId);
	}
}