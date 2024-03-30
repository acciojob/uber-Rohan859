package com.driver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.driver.model.Admin;
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>
{
    @Query(nativeQuery = true,value = "delete from admin where admin_id = :id")
    public void deleteFromRepoById(Integer id);
}
