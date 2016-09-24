package Repositories;

import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import models.User;

@Repository
public class UserRepository {

	public UserRepository() {}
	
	public void insert(DataSource dataSource,User user){
	 	try {
	 		Statement pr = dataSource.getConnection().createStatement();
	 		
	 		String sql = "insert into users values ('"+user.getUsername()+"','"+user.getPassword()+"',1)";
	 		System.out.println("sql ::: "+sql);
			pr.executeUpdate(sql);
			
			sql = "insert into authorities values ('"+user.getUsername()+"','ROLE_USER')";
			System.out.println("sql ::: "+sql);
			pr.executeUpdate(sql);
			
			pr.close();
			
		} catch (SQLException e) {
			System.out.println("exceprion");
		}
	}
	
}
