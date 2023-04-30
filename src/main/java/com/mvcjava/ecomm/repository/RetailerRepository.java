package com.mvcjava.ecomm.repository;

import com.mvcjava.ecomm.roles.Users.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RetailerRepository extends JpaRepository<UserModel,Integer>
{
    List<UserModel> findAllByRole(String role);
    @Query("select r.id from UserModel r where r.login = ?1 and r.role = 'retailer'")
    Integer findIdByName(String name);

    String findNameById(Integer id);

    void deleteById(Integer retID);

}
