package com.example.demo.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface IAdmin {
	
	void deleteFromApplications(Connection con, String query)throws SQLException;
}
