package com.ifox.rcs.dbc.factory;

import com.ifox.rcs.dbc.DatabaseConnection;
import com.ifox.rcs.dbc.impl.MySQLDatabaseConnection;

public class DatabaseConnectionFactory {
	
	public static DatabaseConnection getInstance(){
		return new MySQLDatabaseConnection();
	}

}
