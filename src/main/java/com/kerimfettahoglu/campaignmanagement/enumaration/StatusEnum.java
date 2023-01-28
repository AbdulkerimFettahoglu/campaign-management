package com.kerimfettahoglu.campaignmanagement.enumaration;

public enum StatusEnum {
	ONAY_BEKLIYOR(1, "Onay Bekliyor"), AKTIF(2, "Aktif"), MUKERRER(3, "Mükerrer"), DEAKTIF(4, "Deaktif");
	Integer id;
	String description;
	

	StatusEnum(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return this.id;
	}
	
	public String getDescription() {
		return this.description;
	}

	public static StatusEnum getStatus(Integer id) {
		switch(id) {
			case 1: {
				return ONAY_BEKLIYOR;
			} case 2: {
				return AKTIF;
			} case 3: {
				return MUKERRER;
			} case 4: {
				return DEAKTIF;
			} default:
				return null;
		}
	}
}