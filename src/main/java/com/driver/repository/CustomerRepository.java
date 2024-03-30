package com.driver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.driver.model.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>
{
    @Query(nativeQuery = true,value = "delete from customers where customers_id = :id")
    public void deleteFromRepoByIdFromCustomers(Integer id);


    @Query(nativeQuery = true,value = "select * from customers")
    public List<Customer>getAllCustomerList();
}
