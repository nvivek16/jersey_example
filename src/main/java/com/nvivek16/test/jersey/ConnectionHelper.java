package com.nvivek16.test.jersey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
  private String url;
  private static ConnectionHelper instance;
  
  private ConnectionHelper() {
    try{
      Class.forName("com.mysql.jdbc.Driver");
    }
    catch(ClassNotFoundException e){
      e.printStackTrace();
      return;
    }

    url = "jdbc:mysql://localhost:3306/jersey_example";
  }
  
  public static Connection getConnection() throws SQLException {
    if(instance == null){
      instance = new ConnectionHelper();
    }
    try {
      return DriverManager.getConnection(instance.url, "root", "");
    }
    catch (SQLException e){
      throw e;
    }
  }

  public static void close(Connection connection){
    try{
      if(connection != null){
        connection.close();
      }
    }
    catch(SQLException e){
      e.printStackTrace();
    }
  }
}