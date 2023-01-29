package com.kerimfettahoglu.campaignmanagement.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import com.kerimfettahoglu.campaignmanagement.dto.CampaignHistoryItemDto;
import com.kerimfettahoglu.campaignmanagement.dto.CreateCampaignDto;
import com.kerimfettahoglu.campaignmanagement.entity.Campaign;
import com.kerimfettahoglu.campaignmanagement.enumaration.CategoryEnum;
import com.kerimfettahoglu.campaignmanagement.enumaration.StatusEnum;
import com.kerimfettahoglu.campaignmanagement.service.exception.CampaignNotFoundException;
import com.kerimfettahoglu.campaignmanagement.service.exception.StatusNotApplicableException;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class CampaignServiceIntegrationTest {

	@Autowired
	private CampaignService campaignService;
	
	@Autowired
	private CampaignLogService campaignLogService;
	
	@Autowired
	private DashboardService dashboardService;
	
	@Test
	@Order(1)
	public void createCampaignAttemptLowerThanExpectedCharacterCounts() {
		CreateCampaignDto dto = new CreateCampaignDto();
		dto.setCategory(1);
		dto.setTitle("tit");
		dto.setDetails("desc");
		org.junit.jupiter.api.Assertions.assertThrows(TransactionSystemException.class, () -> campaignService.create(dto));
	}
	
	@Test
	@Order(2)
	public void createCampaignAttemptFirstCharacterOfTitleIsSpecial() {
		CreateCampaignDto dto = new CreateCampaignDto();
		dto.setCategory(1);
		dto.setTitle("_itle12345");
		dto.setDetails("description_________");
		org.junit.jupiter.api.Assertions.assertThrows(TransactionSystemException.class, () -> campaignService.create(dto));
	}
	
	@Test
	@Order(3)
	public void createFirstCampaign() {
		CreateCampaignDto dto = new CreateCampaignDto();
		dto.setCategory(CategoryEnum.TSS.getId());
		dto.setTitle("title12345");
		dto.setDetails("description_________");
		Campaign camp = campaignService.create(dto);
		assertThat(camp.getCategory().getName()).isEqualTo(CategoryEnum.TSS.getDescription());
		assertThat(camp.getDetails()).isEqualTo("description_________");
		assertThat(camp.getStatus().getName()).isEqualTo(StatusEnum.ONAY_BEKLIYOR.getDescription());
		assertThat(camp.getTitle()).isEqualTo("title12345");
	}
	
	@Test
	@Order(4)
	public void createHealthInsuranceCampaign() {
		CreateCampaignDto dto = new CreateCampaignDto();
		dto.setCategory(CategoryEnum.HAYAT.getId());
		dto.setTitle("title12345");
		dto.setDetails("description_________");
		Campaign camp = campaignService.create(dto);
		assertThat(camp.getCategory().getName()).isEqualTo(CategoryEnum.HAYAT.getDescription());
		assertThat(camp.getDetails()).isEqualTo("description_________");
		assertThat(camp.getStatus().getName()).isEqualTo(StatusEnum.AKTIF.getDescription());
		assertThat(camp.getTitle()).isEqualTo("title12345");
	}
	
	@Test
	@Order(5)
	public void getHealthInsuranceCampaign() {
		Campaign camp = campaignService.get(4);
		assertThat(camp.getCategory().getName()).isEqualTo(CategoryEnum.HAYAT.getDescription());
		assertThat(camp.getDetails()).isEqualTo("description_________");
		assertThat(camp.getStatus().getName()).isEqualTo(StatusEnum.AKTIF.getDescription());
		assertThat(camp.getTitle()).isEqualTo("title12345");
	}
	
	@Test
	@Order(6)
	public void activateCampaign() {
		Campaign camp = campaignService.get(3);
		assertThat(camp.getStatus().getName()).isEqualTo(StatusEnum.ONAY_BEKLIYOR.getDescription());
		campaignService.activateCampaign(3);
		camp = campaignService.get(3);
		assertThat(camp.getStatus().getName()).isEqualTo(StatusEnum.AKTIF.getDescription());
	}
	
	@Test
	@Order(7)
	public void re_activateCampaign() {
		Campaign camp = campaignService.get(3);
		assertThat(camp.getStatus().getName()).isEqualTo(StatusEnum.AKTIF.getDescription());
		org.junit.jupiter.api.Assertions.assertThrows(StatusNotApplicableException.class, () -> campaignService.activateCampaign(3));		
	}
	
	@Test
	@Order(8)
	public void deactivateCampaign() {
		Campaign camp = campaignService.get(3);
		assertThat(camp.getStatus().getName()).isEqualTo(StatusEnum.AKTIF.getDescription());
		campaignService.deactivateCampaign(3);
		camp = campaignService.get(3);
		assertThat(camp.getStatus().getName()).isEqualTo(StatusEnum.DEAKTIF.getDescription());
	}
	
	@Test
	@Order(9)
	public void re_deactivateCampaign() {
		Campaign camp = campaignService.get(3);
		assertThat(camp.getStatus().getName()).isEqualTo(StatusEnum.DEAKTIF.getDescription());
		org.junit.jupiter.api.Assertions.assertThrows(StatusNotApplicableException.class, () -> campaignService.deactivateCampaign(3));		
	}
	
	@Test
	@Order(10)
	public void re_activateCampaign2() {
		Campaign camp = campaignService.get(3);
		assertThat(camp.getStatus().getName()).isEqualTo(StatusEnum.DEAKTIF.getDescription());
		org.junit.jupiter.api.Assertions.assertThrows(StatusNotApplicableException.class, () -> campaignService.activateCampaign(3));		
	}
	
	@Test
	@Order(11)
	public void historyOfCampaign() {
		List<CampaignHistoryItemDto> camps = campaignLogService.getHistory(3);
		CampaignHistoryItemDto initCamp = camps.get(0);
		assertThat(initCamp.getStatus()).isEqualTo(StatusEnum.ONAY_BEKLIYOR.getDescription());
		CampaignHistoryItemDto activatedCamp = camps.get(1);
		assertThat(activatedCamp.getStatus()).isEqualTo(StatusEnum.AKTIF.getDescription());
		CampaignHistoryItemDto deactivatedCamp = camps.get(2);
		assertThat(deactivatedCamp .getStatus()).isEqualTo(StatusEnum.DEAKTIF.getDescription());
	}

	@Test
	@Order(12)
	public void createMukerrerHealthInsuranceCampaign() {
		CreateCampaignDto dto = new CreateCampaignDto();
		dto.setCategory(CategoryEnum.HAYAT.getId());
		dto.setTitle("title12345");
		dto.setDetails("description_________");
		Campaign camp = campaignService.create(dto);
		assertThat(camp.getCategory().getName()).isEqualTo(CategoryEnum.HAYAT.getDescription());
		assertThat(camp.getDetails()).isEqualTo("description_________");
		assertThat(camp.getStatus().getName()).isEqualTo(StatusEnum.MUKERRER.getDescription());
		assertThat(camp.getTitle()).isEqualTo("title12345");
	}
	
	@Test
	@Order(13)
	public void attemptToChangeStatusOfMukerrerCampaign() {
		Campaign camp = campaignService.get(5);
		assertThat(camp.getStatus().getName()).isEqualTo(StatusEnum.MUKERRER.getDescription());
		org.junit.jupiter.api.Assertions.assertThrows(StatusNotApplicableException.class, () -> campaignService.activateCampaign(5));
		org.junit.jupiter.api.Assertions.assertThrows(StatusNotApplicableException.class, () -> campaignService.deactivateCampaign(5));
	}
	
	@Test
	@Order(14)
	public void dashboardServiceCheck() {
		Map<String, String> counts = dashboardService.statistics();
		Integer mukerrerCount = Integer.valueOf(counts.get(StatusEnum.MUKERRER.getDescription()));
		Integer aktifCount = Integer.valueOf(counts.get(StatusEnum.AKTIF.getDescription()));
		Integer deaktifCount = Integer.valueOf(counts.get(StatusEnum.DEAKTIF.getDescription()));
		assertThat(mukerrerCount).isEqualTo(1);
		assertThat(aktifCount).isEqualTo(1);
		assertThat(deaktifCount).isEqualTo(1);
	}
}
