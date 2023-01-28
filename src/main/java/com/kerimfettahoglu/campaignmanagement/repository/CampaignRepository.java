package com.kerimfettahoglu.campaignmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kerimfettahoglu.campaignmanagement.entity.Campaign;

public interface CampaignRepository extends JpaRepository<Campaign, Integer>{

}