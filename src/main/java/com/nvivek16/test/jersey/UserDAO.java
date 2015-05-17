package com.nvivek16.test.jersey;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class UserDAO {
  public UserDAO(){
    // Create table
    Connection c = null;
    String sql = "CREATE TABLE IF NOT EXISTS USERS (id SERIAL not NULL, firstName VARCHAR(255), lastName VARCHAR(255), email VARCHAR(255),PRIMARY KEY (id))";
    try{
      c = ConnectionHelper.getConnection();
      Statement s = c.createStatement();
      s.executeUpdate(sql); 
    }
    catch(SQLException se){
      se.printStackTrace();
    }
    finally{
      ConnectionHelper.close(c);
    }
  }
  
  public User save(User user){
    return user.getId() > 0 ? update(user) : create(user);
  }
  
  public void remove(int id){
    Connection c = null;
    PreparedStatement ps = null;
    try{
      c = ConnectionHelper.getConnection();
      ps = c.prepareStatement("DELETE from users where id = ?");
      ps.setInt(1, id);
      ps.executeUpdate();
    }
    catch(SQLException e){
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    finally{
      ConnectionHelper.close(c);
    }
  }

  public List<User> findAll(){
    List<User> list = new ArrayList<User>();
    Connection c = null;
    String sql = "SELECT * from users";
    try{
      c = ConnectionHelper.getConnection();
      Statement s = c.createStatement();
      ResultSet rs = s.executeQuery(sql);
      while (rs.next()) {
        list.add(processSummaryRow(rs));
      }
    }
    catch(SQLException e){
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    finally{
      ConnectionHelper.close(c);
    }
    return list;
  }

  private User create(User user){
    Connection c = null;
    PreparedStatement ps = null;
    try{
      c = ConnectionHelper.getConnection();
      ps = c.prepareStatement("INSERT INTO users (firstName, lastName, email) values (?, ?, ?)", new String[] {"id"});
      ps.setString(1, user.getFirstName());
      ps.setString(2, user.getLastName());
      ps.setString(3, user.getEmail());
      ps.executeUpdate();
      ResultSet rs = ps.getGeneratedKeys();
      rs.next();
      int id = rs.getInt(1);
      user.setId(id);
    }
    catch(SQLException e){
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    finally{
      ConnectionHelper.close(c);
    }
    return user;
  }
  
  private User update(User user){
    Connection c = null;
    PreparedStatement ps = null;
    try{
      c = ConnectionHelper.getConnection();
      ps = c.prepareStatement("UPDATE users SET firstName = ?, lastName = ?, email = ? where id = ?");
      ps.setString(1, user.getFirstName());
      ps.setString(2, user.getLastName());
      ps.setString(3, user.getEmail());
      ps.setInt(4, user.getId());
      ps.executeUpdate();
    }
    catch(SQLException e){
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    finally{
      ConnectionHelper.close(c);
    }
    return user;
  }

  protected User processSummaryRow(ResultSet rs) throws SQLException {
    User user = new User();
    user.setId(rs.getInt("id"));
    user.setFirstName(rs.getString("firstName"));
    user.setLastName(rs.getString("lastName"));
    user.setEmail(rs.getString("email"));
    return user;
  }
}
