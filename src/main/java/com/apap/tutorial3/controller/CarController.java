package com.apap.tutorial3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial3.model.CarModel;
import com.apap.tutorial3.service.CarService;

@Controller
public class CarController {
	@Autowired
	private CarService mobilService;
		
	@RequestMapping("/car/add")
	public String add (@RequestParam(value="id", required = true) String id,
					@RequestParam(value="brand", required = true) String brand,
					@RequestParam(value="type", required = true) String type,
					@RequestParam(value="price", required = true) Long price,
					@RequestParam(value="amount", required = true) Integer amount) {
		CarModel car = new CarModel(id, brand, type, price, amount);
		mobilService.addCar(car);
		return "add";
	}
	
/*	@RequestMapping("/car/view")
	public String view(@RequestParam("id")String id, Model model) {
		CarModel archive = mobilService.getCarDetail(id);
		if(archive == null) {
			return "view-zonk";				
		}
		model.addAttribute("car", archive);
		return "view-car";
	}
	*/
	@RequestMapping("/car/viewall")
	public String viewall(Model model) {
		List<CarModel> archive = mobilService.getCarList();
		model.addAttribute("listCar", archive);
		return "viewall-car";
	}
	
	@RequestMapping(value = {"/car/view","/car/view/{id}"})
	public String viewByPath(@PathVariable Optional<String> id, Model model) {
		if (id.isPresent()) {
			CarModel archive = mobilService.getCarDetail(id.get());
			if(archive == null) {
				return "view-zonk";				
			}
			model.addAttribute("car", archive);
			//model.addAttribute("id", id.get());
			return "view-car";
		} else { 			
			return "view-zonk";
		}
	}
	
	@RequestMapping(value = {"/car/update","/car/update/{id}/amount/{jumlah}"}) 
	public String update(@PathVariable Optional<String> id, @PathVariable Optional<String> jumlah, Model model) {
		if (id.isPresent()) {
			CarModel modelMobil = mobilService.getCarDetail(id.get());
			if (modelMobil == null) {
				return "view-zonk";
			} else {
				String harapan = "Semoga dapet A";
				modelMobil.setAmount(Integer.parseInt(jumlah.get()));
				model.addAttribute("car", modelMobil);
				return "view-car";
			}
		} 
		return "view-zonk";	
	}
	
	@RequestMapping("/car/delete/{idne}")
	public String delete(@PathVariable String idne, Model model) {
		
		if (mobilService.getCarDetail(idne) == null) {
			return "view-zonk";
		} 
		String harapan = "Semoga dapet A";		
		mobilService.deleteCarDetail(idne);
		return "delete-success";
		
	}
	
}
