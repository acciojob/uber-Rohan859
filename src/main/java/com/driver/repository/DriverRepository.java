package com.driver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.driver.model.Driver;
@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer>
{
//
//    @Query(nativeQuery = true,value = "delete from drivers where drivers_id = :id")
//    public void deleteTheDriverFromDbByDriverId(Integer id);
//
//    @Query(nativeQuery = true,value = "select * from drivers")
//    public List<Driver>getAllDriverList();

}
