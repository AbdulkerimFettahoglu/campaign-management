package com.kerimfettahoglu.campaignmanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kerimfettahoglu.campaignmanagement.dto.CreateCampaignDto;
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
	
	@PostMapping
	public Campaign createCampaign(@RequestBody CreateCampaignDto campaignDto) {
		return campaignService.create(campaignDto);
	}
	
	@GetMapping("/activate")
	public Boolean activateCampaign(@RequestParam Integer campaignId) {
		return campaignService.activateCampaign(campaignId);
	}
	
	@GetMapping("/deactivate")
	public Boolean deactivateCampaign(@RequestParam Integer campaignId) {
		return campaignService.deactivateCampaign(campaignId);
	}
}