package com.apap.tutorial3.service;
import com.apap.tutorial3.model.PilotModel;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
/**
 * PilotInMemoryService
 */
@Service
public class PilotInMemoryService implements PilotService {
	private List<PilotModel> archivePilot;
	
	public PilotInMemoryService() {
		archivePilot = new ArrayList<>();
	}
	@Override
	public void addPilot(PilotModel pilot) {
		archivePilot.add(pilot);
	}
	
	public void deletePilot(PilotModel pilot) {
		archivePilot.remove(pilot);
	}

	@Override
	public List<PilotModel> getPilotList() {
		return archivePilot;
	}

	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		for (int i = 0 ; i < archivePilot.size() ; i++) {
			if(archivePilot.get(i).getLicenseNumber().equals(licenseNumber)) {
				return archivePilot.get(i);
			}
		}
		return null;
	}
	
	public PilotModel getPilotDetailByID(String id) {
		for (int i = 0 ; i < archivePilot.size() ; i++) {
			if(archivePilot.get(i).getId().equals(id)) {
				return archivePilot.get(i);
			}
		}
		return null;
	}

}
