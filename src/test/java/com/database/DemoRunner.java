package com.database;

import java.sql.SQLException;

public class DemoRunner {

	public synchronized static void main(String[] args) throws SQLException {
		DatabaseManagerOLD.createConnection();
		DatabaseManagerOLD.createConnection();
		DatabaseManagerOLD.createConnection();
		DatabaseManagerOLD.createConnection();
		DatabaseManagerOLD.createConnection();

	}

}
