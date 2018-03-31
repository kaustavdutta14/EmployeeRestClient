package com.java.client;

import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestClient {
	public static void main(String[] args) {

		try {
			Client client = ClientBuilder.newClient();

			System.out.println("Employee LIST");
			Response response = client.target("http://localhost:8088/EmployeeRestProvider/").request()
					.get(Response.class);
			List<Employee> lst = response.readEntity(new GenericType<List<Employee>>() {
			});
			System.out.println(lst);

			System.out.println("ADD Employee");
			Employee emp = new Employee(4, "Siddhesh", "Missori");
			response = client.target("http://localhost:8088/EmployeeRestProvider/").request().post(Entity.json(emp));
			Employee result = response.readEntity(Employee.class);
			System.out.println("Employee ADDED " + result);

			System.out.println("Search Employee by ID:");
			response = client.target("http://localhost:8088/EmployeeRestProvider/2").request().get(Response.class);
			emp = response.readEntity(Employee.class);
			System.out.println(emp);

			System.out.println("Search Employee by Name:");
			response = client.target("http://localhost:8088/EmployeeRestProvider/name/Smith").request()
					.get(Response.class);
			lst = response.readEntity(new GenericType<List<Employee>>() {
			});
			System.out.println(lst);

			System.out.println("UPDATE Employee by ID:");
			emp = new Employee(4, "Kaustav", "PLano");
			response = client.target("http://localhost:8088/EmployeeRestProvider/").request().put(Entity.json(emp));
			result = response.readEntity(Employee.class);
			System.out.println("Employee UPDATED " + result);

			System.out.println("Employee LIST");
			response = client.target("http://localhost:8088/EmployeeRestProvider/").request().get(Response.class);
			lst = response.readEntity(new GenericType<List<Employee>>() {
			});
			System.out.println(lst);

			System.out.println("DELETE Employee by ID:");
			response = client.target("http://localhost:8088/EmployeeRestProvider/2").request().delete(Response.class);
			result = response.readEntity(Employee.class);
			System.out.println("Employee DELETED ");

			System.out.println("Employee LIST");
			response = client.target("http://localhost:8088/EmployeeRestProvider/").request().get(Response.class);
			lst = response.readEntity(new GenericType<List<Employee>>() {
			});
			System.out.println(lst);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
