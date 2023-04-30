package com.mvcjava.ecomm.roles.Admin;


public class AdminAccount{
  private static AdminAccount instance = null;
  private String username;
  private String password;

  private AdminAccount() {
      // Private constructor to prevent instantiation from outside
  }

  public static AdminAccount getInstance() {
      if (instance == null) {
          instance = new AdminAccount();
      }
      return instance;
  }

  public void setUsername(String username) {
      this.username = username;
  }

  public void setPassword(String password) {
      this.password = password;
  }

  public String getUsername() {
      return this.username;
  }

  public String getPassword() {
      return this.password;
  }
}
