package com.kerimfettahoglu.campaignmanagement.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kerimfettahoglu.campaignmanagement.service.DashboardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

	private final DashboardService dashboardService;

	@GetMapping("/classifieds/statistics")
	public Map<String, String> statistics() {
		return dashboardService.statistics();
	}
}