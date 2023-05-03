package com.sist.exam01;

import java.sql.CallableStatement;
import java.sql.Connection;

import db.ConnectionProvider;

public class MainTest {
	public static void main(String[] args) {
		try {
			int total;
			Connection conn = ConnectionProvider.getConnection();
			CallableStatement cstmt = 
					conn.prepareCall("{call printAllProfit(?)}");
			cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
			cstmt.execute();
			total= cstmt.getInt(1);
			System.out.println("총판매이익금 : "+ total);
			ConnectionProvider.close(cstmt, conn);
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}

}






