package com.mvcjava.ecomm.service;

import com.mvcjava.ecomm.roles.Users.UserModel;
import com.mvcjava.ecomm.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService
{
    @Autowired
    private UsersRepository usersRepository;


    public UserModel registerUser(String login,String password,String email,String role)
    {
        System.out.println("setting values "+login+" "+password+" "+email+" "+role);
        if (login == null || password == null)
        {
            System.out.println("something is null!!!!");
            System.out.println("login = "+login+"password = "+password);
            return null;
        }
        else
        {
                UserModel userModel = new UserModel();
                userModel.setLogin(login);
                userModel.setPassword(password);
                userModel.setMailid(email);
                userModel.setRole(role);
                return usersRepository.save(userModel);

        }
    }
    public UserModel authentication(String login,String password)
    {
        return usersRepository.findByLoginAndPassword(login,password).orElse(null);
    }
}
