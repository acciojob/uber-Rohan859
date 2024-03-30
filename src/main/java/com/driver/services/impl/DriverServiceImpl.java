package com.driver.services.impl;

import com.driver.model.Cab;
import com.driver.repository.CabRepository;
import com.driver.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Driver;
import com.driver.repository.DriverRepository;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	DriverRepository driverRepository3;

	@Autowired
	CabRepository cabRepository3;

	@Override
	public void register(String mobile, String password)
	{
		//Save a driver in the database having given details and a cab with ratePerKm as 10 and availability as True by default.

		//1. create new driver
		Driver driver=new Driver(mobile,password);

		//2. create new cab
		Cab cab=new Cab(Boolean.TRUE,10);

		//3. set deiver's cab is cab
		driver.setCab(cab);

		//4. save into db driver and cab
		driver=driverRepository3.save(driver);
		cab=cabRepository3.save(cab);

	}

	@Override
	public void removeDriver(int driverId)
	{
		// Delete driver without using deleteById function

		//1. get driver
		Driver driver=driverRepository3.findById(driverId).get();

		//2. set cab is null
		driver.setCab(null);

		//3. delete the driver from DB
		driverRepository3.deleteTheDriverFromDbByDriverId(driverId);
	}

	@Override
	public void updateStatus(int driverId)
	{
		//Set the status of respective car to unavailable
		Cab cab=driverRepository3.findById(driverId).get().getCab();
		cab.setAvailable(Boolean.FALSE);
	}
}
