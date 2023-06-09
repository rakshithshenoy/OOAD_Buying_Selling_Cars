package com.mvcjava.ecomm.repository;

import com.mvcjava.ecomm.roles.Users.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsersRepository extends JpaRepository<UserModel,Integer>
{
    Optional<UserModel> findByLoginAndPassword(String login,String password);
    UserModel findByLoginAndRole(String login,String role);
}
