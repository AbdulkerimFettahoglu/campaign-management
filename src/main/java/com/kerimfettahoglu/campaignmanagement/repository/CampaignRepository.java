package com.kerimfettahoglu.campaignmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kerimfettahoglu.campaignmanagement.entity.Campaign;
import com.kerimfettahoglu.campaignmanagement.entity.Category;

public interface CampaignRepository extends JpaRepository<Campaign, Integer>{
	
	public List<Campaign> findByCategoryAndTitleAndDetails(Category category, String title, String description);
	
	@Query("select a.status.id, count(a) from Campaign a group by a.status.id")
	public List<Object[]> countByStatus();
}