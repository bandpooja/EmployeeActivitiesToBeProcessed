package com.letsstartcoding.springbootrestapiexample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.http.client.HttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EmployeeApplication {
	
	public static void main(String[] args) throws IOException {
		SpringApplication.run(EmployeeApplication.class, args);
		String reqtype=null;
		String res;
		reqtype="get";
		SendRequest send=new SendRequest();
		if (reqtype.equalsIgnoreCase("POST"))
		{
		res=send.SendHttpsPostRequestToURL();
		}
		else
		{
			//if wanted to get allemployees the use url=http://localhost:8080/company/employees/
			//if wanted to see & days stastic and activities of employee hit url=http://localhost:8080/company/employees/act/
			 res=send.SendHttpsGetRequestToURL("http://localhost:8080/company/employees/act/");
		}
		BufferedWriter out = new BufferedWriter(new FileWriter("OutputData//Response.json"));

		//Add this to write a string to a file
		//
		try {

		    out.write(res); 
		    out.close();
		}
		catch (IOException e)
		{
		    System.out.println("Exception ");
		    out.close();

		}
	}

}
