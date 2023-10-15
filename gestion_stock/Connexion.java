package gestion_stock;

import java.sql.*;

public class Connexion {
	
	Connection conn;
	
	public Connexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdd_stock", "root", "");
			System.out.println("Connexion etablie!");
		} catch (SQLException e) {
			System.out.println("Connexion echouee!");
		}
	}
	
	public Connection connexion() {
		return conn;
	}
}
