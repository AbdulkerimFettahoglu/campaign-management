package com.kerimfettahoglu.campaignmanagement;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kerimfettahoglu.campaignmanagement.entity.Category;
import com.kerimfettahoglu.campaignmanagement.entity.Status;
import com.kerimfettahoglu.campaignmanagement.enumaration.CategoryEnum;
import com.kerimfettahoglu.campaignmanagement.enumaration.StatusEnum;
import com.kerimfettahoglu.campaignmanagement.repository.CategoryRepository;
import com.kerimfettahoglu.campaignmanagement.repository.StatusRepository;

@SpringBootApplication
public class CampaignManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampaignManagementApplication.class, args);
	}
	
	@Autowired
	private StatusRepository statusRepo;
	@Autowired
	private CategoryRepository categoryRepo;

	@PostConstruct
	public void init() {
		StatusEnum sa1 = StatusEnum.ONAY_BEKLIYOR; 
		Status statu1 = new Status();
		statu1.setId(sa1.getId());
		statu1.setName(sa1.getDescription());
		Status statu2 = new Status();
		
		StatusEnum sa2 = StatusEnum.AKTIF;
		statu2.setId(sa2.getId());
		statu2.setName(sa2.getDescription());
		
		StatusEnum sa3 = StatusEnum.MUKERRER;
		Status statu3 = new Status();
		statu3.setId(sa3.getId());
		statu3.setName(sa3.getDescription());
		
		StatusEnum sa4 = StatusEnum.DEAKTIF;
		Status statu4 = new Status();
		statu4.setId(sa4.getId());
		statu4.setName(sa4.getDescription());
		
		statusRepo.save(statu1);
		statusRepo.save(statu2);
		statusRepo.save(statu3);
		statusRepo.save(statu4);
		
		CategoryEnum tss = CategoryEnum.TSS;
		Category c1 = new Category();
		c1.setId(tss.getId());
		c1.setName(tss.getDescription());
		
		CategoryEnum oss = CategoryEnum.OSS;
		Category c2 = new Category();
		c2.setId(oss.getId());
		c2.setName(oss.getDescription());
		
		CategoryEnum hayat = CategoryEnum.HAYAT;
		Category c3 = new Category();
		c3.setId(hayat.getId());
		c3.setName(hayat.getDescription());
		
		CategoryEnum diger = CategoryEnum.DIGER;
		Category c4 = new Category();
		c4.setId(diger.getId());
		c4.setName(diger.getDescription());

		categoryRepo.save(c1);
		categoryRepo.save(c2);
		categoryRepo.save(c3);
		categoryRepo.save(c4);
	}
}
