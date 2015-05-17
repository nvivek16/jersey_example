package com.nvivek16.test.jersey;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
  private static String url, username, password;
  private static ConnectionHelper instance;
  
  private ConnectionHelper() throws URISyntaxException {
    System.out.print(System.getenv("DATABASE_URL"));
    URI dbUri = new URI(System.getenv("DATABASE_URL"));

    username = dbUri.getUserInfo().split(":")[0];
    password = dbUri.getUserInfo().split(":")[1];
    int port = dbUri.getPort();

    url = "jdbc:postgresql://" + dbUri.getHost() + ":" + port + dbUri.getPath();

  }
  
  public static Connection getConnection() throws SQLException {
    if(instance == null){
      try {
        instance = new ConnectionHelper();
      } catch (URISyntaxException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    try {
      return DriverManager.getConnection(url, username, password);
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