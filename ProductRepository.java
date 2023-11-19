package com.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Product;

public class ProductRepository {
	private String url="jdbc:mysql://localhost:3306/jdbcapp";
	private String userdb="springstudent";
	private String passdb="springstudent";
	private String driver="com.mysql.cj.jdbc.Driver";
	Connection con;
	
	public void dbConnect() {
		try {
			Class.forName(driver);
			//System.out.println("driver loaded...");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			con=DriverManager.getConnection(url, userdb, passdb);
			//System.out.println("conn established..");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void dbClose() {
		try {
			con.close();
			//System.out.println("db closed..");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertProduct(Product product) {
		dbConnect();
		String sql="insert into product(name,price,category) values (?,?,?)";
		try {
			PreparedStatement preparedStatement=con.prepareStatement(sql);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setDouble(2, product.getPrice());
			preparedStatement.setString(3, product.getCategory());
			//preparedStatement.setString(4, employee.getCity());
			
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Product> fetchAllProducts(){
		dbConnect();
		String sql="select * from product";
		List<Product> list=new ArrayList<>();
		try {
			PreparedStatement preparedStatement=con.prepareStatement(sql);
			ResultSet rst=preparedStatement.executeQuery();
			while(rst.next()) {
				Product product=new Product();
				product.setId(rst.getInt("id"));
				product.setName(rst.getString("name"));
				product.setPrice(rst.getDouble("price"));
				product.setCategory(rst.getString("category"));
				
				list.add(product);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return list;
	}
	
	public List<Product> increasePriceByCategory(double inc_mobile,double inc_laptop, List<Product> list) {
		List<Product> list1=new ArrayList<>();
		double val;
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getCategory().equals("mobile")) {
				Product product=new Product();
				product=list.get(i);
				val=product.getPrice();
				val*=1.02;
				product.setPrice(val);
				list1.add(product);
			}else if(list.get(i).getCategory().equals("laptop")) {
				Product product=new Product();
				product=list.get(i);
				val=product.getPrice();
				val*=1.01;
				product.setPrice(val);
				list1.add(product);
			}
		}
		return list1;
	}
	
	

	
	public String sumPrices() {
		dbConnect();
		String sql="select sum(price) as sum_prices from product";
		String sum="";
		List<Product> list=new ArrayList<>();
		try {
			PreparedStatement preparedStatement=con.prepareStatement(sql);
			ResultSet rst=preparedStatement.executeQuery();
			if(rst.next()) {
				sum=rst.getString("sum_prices");
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return sum;
	}
	
	
	public Product getOneProduct(int id) {
		// TODO Auto-generated method stub
		dbConnect();
		String sql="select price from employee where id=?";
		Product product=new Product();
		try {
			PreparedStatement preparedStatement=con.prepareStatement(sql);
			preparedStatement.setInt(1,id);
			ResultSet rst=preparedStatement.executeQuery();
			if(rst.next()) {
				
				product.setId(rst.getInt("id"));
				product.setName(rst.getString("name"));
				product.setPrice(rst.getDouble("price"));
				product.setCategory(rst.getString("category"));
				
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}dbConnect();
		dbClose();
		return product;
	}
	
	public boolean computeGreaterPrice(int id1,int id2) {
		Product p1=getOneProduct(id1);
		Product p2=getOneProduct(id2);
		if(p1.getPrice()<p2.getPrice()) {
			return false;
		}
		else {
			return true;
		}
	}
}
