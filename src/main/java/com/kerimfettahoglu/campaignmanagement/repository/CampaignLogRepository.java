package com.kerimfettahoglu.campaignmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kerimfettahoglu.campaignmanagement.entity.CampaignLog;

public interface CampaignLogRepository extends JpaRepository<CampaignLog, Integer> {

}
