package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.CabRepository;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository2;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository2;

//	@Autowired
//	CabRepository cabRepository;

	@Override
	public void register(Customer customer)
	{
		//Save the customer in database
		customer=customerRepository2.save(customer);
	}

	@Override
	public void deleteCustomer(Integer customerId)
	{
		// Delete customer without using deleteById function
		//customerRepository2.deleteFromRepoByIdFromCustomers(customerId);
		//customerRepository2.deleteById(customerId);
		Customer customer=customerRepository2.findById(customerId).get();
		customerRepository2.delete(customer);
	}

	@Override
	public TripBooking bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception
	{
		//Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE). If no driver is available, throw "No cab available!" exception
		//Avoid using SQL query

		//1. get all drivers
		List<Driver>driverList=driverRepository2.findAll();

		//2. find the lowest driverId who is free
		//so sort the driverList in ascending order based on their ids

		Collections.sort(driverList,(Driver a,Driver b)->{
			return Integer.compare(a.getDriverId(), b.getDriverId());
		});

		System.out.println("sorted list is -> "+driverList.toString());

		//now check if any driver cab is free or not
		boolean flag=true;

		Driver driver=null;

		for(Driver driver1:driverList)
		{
			if(driver1!=null && driver1.getCab().getAvailable()==Boolean.TRUE)
			{
				driver=driver1;
				flag=false;
				break;
			}
		}

		//if flag is true then no driver cab is available
		if(flag)
		{
			throw new Exception("No cab available!");
		}

		//get the customer from the db with id
		Customer customer=customerRepository2.findById(customerId).get();


		//make tripBooking
		//TripBooking tripBooking=new TripBooking(fromLocation,toLocation,distanceInKm,TripStatus.CONFIRMED,driver,customer);


		TripBooking tripBooking=new TripBooking();
		tripBooking.setFromLocation(fromLocation);
		tripBooking.setToLocation(toLocation);
		tripBooking.setDistanceInKm(distanceInKm);

		tripBooking.setStatus(TripStatus.CONFIRMED);

		Cab cab=driver.getCab();
		cab.setAvailable(Boolean.FALSE);

		tripBooking.setBill(cab.getPerKmRate()*distanceInKm);
		tripBooking.setDriver(driver);
		tripBooking.setCustomer(customer);

		customer.getTripBookingList().add(tripBooking);



		//change the driver cab is not available
		//driver.getCab().setAvailable(Boolean.FALSE);

		//save all these into dbs
//		tripBooking=tripBookingRepository2.save(tripBooking);
//		driver=driverRepository2.save(driver);
//		customer=customerRepository2.save(customer);


		tripBooking=tripBookingRepository2.save(tripBooking);
		customer=customerRepository2.save(customer);
		driver=driverRepository2.save(driver);

		//return tripBooking;
		return tripBooking;
	}

	@Override
	public void cancelTrip(Integer tripId)
	{
		//Cancel the trip having given trip Id and update TripBooking attributes accordingly
		TripBooking tripBooking=tripBookingRepository2.findById(tripId).get();

		if(tripBooking==null || tripBooking.getDriver()==null || tripBooking.getCustomer()==null)
		{
			return;
		}

		//get driver and customer tripBookingList
		List<TripBooking>tripBookingListFromDriver=tripBooking.getDriver().getTripBookingList();
		List<TripBooking>tripBookingListFromCustomer=tripBooking.getCustomer().getTripBookingList();

		//delete the tripBooking from these list
		tripBookingListFromDriver.remove(tripBooking);
		tripBookingListFromCustomer.remove(tripBooking);

		//driver and cab
		Driver driver=tripBooking.getDriver();
		Cab cab=driver.getCab();

		cab.setPerKmRate(10);
		cab.setAvailable(Boolean.TRUE);

		//set attributes in bookTrip
		tripBooking.setCustomer(null);
		tripBooking.setDriver(null);
		tripBooking.setStatus(TripStatus.CANCELED);

		driver=driverRepository2.save(driver);
		tripBooking=tripBookingRepository2.save(tripBooking);

	}

	@Override
	public void completeTrip(Integer tripId)
	{
		//Complete the trip having given trip Id and update TripBooking attributes accordingly
		TripBooking tripBooking=tripBookingRepository2.findById(tripId).get();

		if(tripBooking==null || tripBooking.getDriver()==null || tripBooking.getCustomer()==null)
		{
			return;
		}


		//get driver and customer tripBookingList
		List<TripBooking>tripBookingListFromDriver=tripBooking.getDriver().getTripBookingList();
		List<TripBooking>tripBookingListFromCustomer=tripBooking.getCustomer().getTripBookingList();

		//delete the tripBooking from these list
		tripBookingListFromDriver.remove(tripBooking);
		tripBookingListFromCustomer.remove(tripBooking);

		//driver and cab
		Driver driver=tripBooking.getDriver();
		Cab cab=driver.getCab();

		cab.setPerKmRate(10);
		cab.setAvailable(Boolean.TRUE);

		//set attributes in bookTrip
		tripBooking.setCustomer(null);
		tripBooking.setDriver(null);
		tripBooking.setStatus(TripStatus.COMPLETED);

		driver=driverRepository2.save(driver);
		tripBooking=tripBookingRepository2.save(tripBooking);
	}

}
