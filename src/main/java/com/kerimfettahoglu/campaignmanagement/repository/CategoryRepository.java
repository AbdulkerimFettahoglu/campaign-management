package com.kerimfettahoglu.campaignmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kerimfettahoglu.campaignmanagement.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}