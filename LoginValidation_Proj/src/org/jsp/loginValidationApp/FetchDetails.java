package org.jsp.loginValidationApp;
import java.sql.*;
import java.util.Scanner;
public class FetchDetails 
{
	public static void main(String[] args) 
	{
		Connection con=null;
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		String qry="select UserName from btm.user where Name=? and Password=?";
		Scanner sc= new Scanner(System.in);
		System.out.println("----------------------------------------------------------------");
		System.out.println("\t\t | Enter Name | ");
		System.out.println("----------------------------------------------------------------");
		String name=sc.next();
		System.out.println("Enter Password??");
		String password=sc.next();
		sc.close();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=Admin");
			pstmt=con.prepareStatement(qry);
		//SET THE VALUE FOR THE PLACEHOLDER BEFORE EXECUTION
			pstmt.setString(1, name);
			pstmt.setString(2, password);
		//EXECUTE SQL QUERY
			rs=pstmt.executeQuery();
		//CHECK FOR RECORDS IN CURSOR OR BUFFER MEMORY
				if(rs.next())
				{
					String username=rs.getString(1);
					System.out.println("_________________________________________________________________\n");
					System.out.println("\t\t |     Welcome "+username +"\t  |");
					System.out.println("_________________________________________________________________");
				}
				else
				{
					System.err.println("Invalid Name/Password");
					System.err.println("Please Enter the Valid Name/Password");
				}
			} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(rs!=null)
			{
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null)
			{
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
