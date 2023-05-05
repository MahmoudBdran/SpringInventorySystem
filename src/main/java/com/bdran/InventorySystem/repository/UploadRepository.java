package com.bdran.InventorySystem.repository;


import com.bdran.InventorySystem.model.Images;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UploadRepository extends JpaRepository<Images, Integer> {

}