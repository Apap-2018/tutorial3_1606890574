package com.apap.tutorial3.controller;
import java.util.List;
import java.util.Optional;

import com.apap.tutorial3.model.PilotModel;
import com.apap.tutorial3.service.PilotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/pilot/add")
	public String add(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "licenseNumber", required = true) String licenseNumber,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "flyHour", required = true) int flyHour) {
		PilotModel pilot = new PilotModel(id, licenseNumber, name, flyHour);
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping("/pilot/view")
	public String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("pilot", archive);
		return "view-pilot";
	}
	
	
	
	@RequestMapping(value= {"/pilot/view/license-number/{licenseNumber}", "/pilot/view/license-number/"})
	public String challengePath(@PathVariable Optional<String> licenseNumber, Model model) {
		if (licenseNumber.isPresent()) {
			PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber.get());
			
			if (archive != null) {
				model.addAttribute("pilot", archive);
				return "view-pilot";
			} else {
				model.addAttribute("notmatch", licenseNumber.get());
				return "errorNoAvailable";
			}
			
		}else {
			return "errorNoNum";
		}
	} 	
	
	@RequestMapping(value = {"/pilot/update/license-number/{LN}/fly-hour/{FH}", "/pilot/update/license-number"})
	public String updateFlyHour(@PathVariable Optional<String> LN, @PathVariable Optional<String> FH, Model model) {	
		//asumsi ID yang dimaksud pada soal adalah license number 
		if (LN.isPresent()) {
			PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(LN.get());
			if (pilot!= null ) { //ada pilotnya, update
				pilot.setFlyHour(Integer.parseInt(FH.get()));
				model.addAttribute("LN", LN.get());
				model.addAttribute("FH", FH.get());
				return "update";
			} else { //pilot yang mau diupdate tidak ada
				model.addAttribute("notmatch", LN.get());
				return "errorNoAvailable";
			} 
		} else {
			return "errorNoNum";
		}
	}
	
	@RequestMapping(value = {"/pilot/delete/id/{id}", "/pilot/delete/id/"})
	public String deletePilot(@PathVariable Optional<String> id, Model model) {
		if (id.isPresent()) {
			PilotModel pilot = pilotService.getPilotDetailByID(id.get());
			if (pilot!= null ) { //ada pilotnya, delete
				model.addAttribute("id", id.get());
				pilotService.deletePilot(pilot);
				return "delete";
			} else { //pilot yang mau didelete tidak ada
				model.addAttribute("notmatch", id.get());
				return "errorNoAvailable";
			}
		} else {
			return "errorNoID";
		}
	}
	
	@RequestMapping("/pilot/viewall")
	public String viewall(Model model) {
		List<PilotModel> archive = pilotService.getPilotList();
		model.addAttribute("listPilot", archive);
		return "viewall-pilot";
	}
	
}
