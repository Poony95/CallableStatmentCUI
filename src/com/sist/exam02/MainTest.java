package com.sist.exam02;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;

import db.ConnectionProvider;
import oracle.jdbc.OracleTypes;

public class MainTest {
	public static void main(String[] args) {
		int bookid;
		Scanner sc = new Scanner(System.in);
		System.out.print("도서번호 입력:");
		bookid = sc.nextInt();
		try {
			Connection conn = ConnectionProvider.getConnection();
			CallableStatement cstmt = 
					conn.prepareCall("{call getCustomer(?, ?)}");
			cstmt.setInt(1, bookid);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			cstmt.execute();
			
			ResultSet rs =(ResultSet) cstmt.getObject(2);
			while(rs.next()) {
				int custid = rs.getInt(1);
				String name = rs.getString(2);
				String addr = rs.getString(3);
				String phone = rs.getString(4);
				System.out.println(custid+","+name+","+addr+","+phone);				
			}
			ConnectionProvider.close(rs, cstmt, conn);
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}

	}

}
