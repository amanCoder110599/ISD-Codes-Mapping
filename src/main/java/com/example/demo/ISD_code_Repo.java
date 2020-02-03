package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ISD_code_Repo extends JpaRepository<ISD_code, String>{
	List<ISD_code> findByisd(String isd);
}
