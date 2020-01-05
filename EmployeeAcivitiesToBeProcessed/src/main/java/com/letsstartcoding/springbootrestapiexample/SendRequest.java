package com.letsstartcoding.springbootrestapiexample;

import java.net.HttpURLConnection;

import java.net.URL;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.DataOutputStream;

public class SendRequest {
	//sending post request take input as a json file in folder InputData you can choose the input file as we have like data1.json,data2.json etc
	public String SendHttpsPostRequestToURL() throws IOException {
		String Resp = "";
			URL url = new URL("http://localhost:8080/company/employees/");
			
			HttpURLConnection con=(HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

			BufferedReader reader = new BufferedReader(new FileReader("InputData//data10.json"));
			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			String ls = System.getProperty("line.separator");
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
			// delete the last new line separator
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			reader.close();

			String inputJson = stringBuilder.toString();

			con.setDoInput(true);
			con.setDoOutput(true);
			sendDataToHttp(con, inputJson);
			InputStream input = con.getInputStream();
			BufferedReader in = null;
			String inputLine;
			StringBuilder body;
			try {
				in = new BufferedReader(new InputStreamReader(input));
				body = new StringBuilder();
				while ((inputLine = in.readLine()) != null) {
					body.append(inputLine);
				}
				Resp=body.toString(); 
				in.close();
				return Resp;
			} catch (IOException ioe) {
return "exception";
			
			}
				
			
		
	}

	static void sendDataToHttp(HttpURLConnection con, String data) throws IOException {
		DataOutputStream wr = null;
		try {
			wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(data);
			wr.flush();
			wr.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

//sendeng get request pass the url for get request 	
	public String SendHttpsGetRequestToURL(String urlforreq) throws IOException {
		 StringBuilder result = new StringBuilder();
	      URL url = new URL(urlforreq);//"http://localhost:8080/company/employees/act/"
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      String line;
	      while ((line = rd.readLine()) != null) {
	         result.append(line);
	      }
	      rd.close();
	       String output=result.toString();
	       return output;
		
	}


}
