package com.bdran.InventorySystem.service;

import com.bdran.InventorySystem.model.Vendor;
import com.bdran.InventorySystem.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorService{

	@Autowired
	private VendorRepository vendorRepository;

	public Vendor getVendorById(long id) {
		Optional<Vendor> optional = vendorRepository.findById(id);
		Vendor vendor = null;
		if (optional.isPresent()) {
			vendor = optional.get();
		} else {
			// Exception
		}
		return vendor;
	}


	public String validateVendorId(long id) {
		String errorMessage = "";
		Vendor vendor = getVendorById(id);
		if (vendor == null) {
			errorMessage = "Vendor ID does not exist";
		}

		return errorMessage;
	}

	public Vendor getVendorByName(String name) {
		List<Vendor> vendorList = vendorRepository.findAll();
		Vendor vendor = null;
		for (Vendor v : vendorList) {
			if (v.getName().equalsIgnoreCase(name)) {
				vendor = v;
				break;
			}
		}
		return vendor;
	}


	public String validateVendorName(String vendorName) {
		String errorMessage = "";
		Vendor vendor = getVendorByName(vendorName);
		if (vendor == null) {
			errorMessage = "Vendor with name: " + vendorName + " does not exist.";
		}
		return errorMessage;
	}
}
