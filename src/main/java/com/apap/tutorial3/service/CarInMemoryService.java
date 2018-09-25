package com.apap.tutorial3.service;

import java.util.ArrayList;
import java.util.List;
import com.apap.tutorial3.model.CarModel;
import org.springframework.stereotype.Service;

/**
 * 
 * CarInMemoryService
 *
 */
@Service
public class CarInMemoryService implements CarService {
	private List<CarModel> archiveCar;
	
	public CarInMemoryService() {
		archiveCar = new ArrayList<>();
	}
	
	@Override
	public void addCar(CarModel car) {
		archiveCar.add(car);
	}
	
	@Override 
	public List<CarModel> getCarList(){
		return archiveCar;
	}

	@Override
	public CarModel getCarDetail(String id) {
		CarModel bmw = null;
		for(CarModel bmww : archiveCar) {
			if (bmww.getId().equals(id)) {
				bmw = bmww;
			} else {
				bmw = null;
			}
		}
		return bmw;
/*		return null;
*/	}

	@Override
	public void deleteCarDetail(String idne) {
		// TODO Auto-generated method stub
		archiveCar.removeIf(car -> car.getId().equals(idne));
	}
	
}
