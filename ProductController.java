package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.exception.InvalidIdException;
import com.model.Product;
import com.service.ProductService;


public class ProductController {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		ProductService productService=new ProductService();
		while(true) {
			System.out.println("******Product DB Ops********");
			System.out.println("1. Insert Product");
			System.out.println("2. Fetch All Products");
			System.out.println("3. Incrementing prices based on category");
			System.out.println("4. Find sum of the prices of the products");
			System.out.println("5. Compare prices of two products");
			System.out.println("0. Exit");
			System.out.println("*******************\n");
			int input=sc.nextInt();
			if(input==0) {
				System.out.println("Exiting.. bye..");
				break;
			}
			switch(input) {
			case 1:
				System.out.println("Enter Product Data");
				Product product=new Product();
				System.out.println("Enter name");
				product.setName(sc.next());
				//employee.setName(sc.next());
				System.out.println("Enter price");
				product.setPrice(sc.nextDouble());
				//employee.setEmail(sc.next());
				System.out.println("Enter category");
				product.setCategory(sc.next());
				productService.insertProduct(product);
				break;
			case 2:
				List<Product> list=productService.fetchAllProducts();
				list.stream().forEach(e->System.out.println(e));
				break;
			case 3:
				System.out.println("Please enter the no. of elements");
				int n=sc.nextInt();
				System.out.println("Enter the increment percentages of mobiles and laptops and the data of the products");
				double val1,val2;
				val1=sc.nextDouble();
				val2=sc.nextDouble();
				List<Product> list1=new ArrayList<Product>();
				System.out.println("Please enter the data in the following order: id, title, price, category");
				for(int i=0;i<n;i++) {
					Product prod=new Product();
					prod.setId(sc.nextInt());
					prod.setName(sc.next());
					prod.setPrice(sc.nextDouble());
					prod.setCategory(sc.next());
					list1.add(prod);
				}
				List<Product> list2=productService.increasePriceByCategory(val1, val2,list1);
				System.out.println("The incremented values are as follows");
				for(int i=0;i<list2.size();i++) {
					System.out.println("ID: "+list2.get(i).getId()+" Incremented Price: "+list2.get(i).getPrice());
				}
				break;
			case 4:
				String total_prize=productService.sumPrices();
				System.out.println(total_prize);
				break;
			case 5:
				System.out.println("Enter the IDs of the two products");
				int id1=sc.nextInt();
				int id2=sc.nextInt();
				boolean x=productService.computeGreaterPrice(id1,id2);
				if(x) {
					System.out.println("The price of the first product is greater than that of the second product");
				}
				else {
					System.out.println("The price of the second product is greater than that of the first product");
				}
				break;
			case 6:
				break;
			default:
				System.out.println("invalid input, try again");
			}

	}

}
}
