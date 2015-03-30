package org.archive.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.json.JSONObject;



/**
 * @author silvasong E-mail:silvasong@campray.com
 * @version 2015年3月30日 下午2:11:22
 * 
 */
public class UswopAPI {
	
	public static void goodsManagement(String pid,int status){
		try {
			URL url = new URL("http://192.168.10.3:8888/mpos/api/getProduct/1");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-Type","application/json");
			connection.setRequestProperty("Authorization", "21218cca77804d2ba1922c33e0151105");
			connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			connection.connect();
			
			//post 请求发送数据
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			JSONObject obj = new JSONObject();
			obj.put("pid", pid);
			obj.put("status", status);
			out.writeBytes(obj.toString());
			out.flush();
			out.close();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuffer sb = new StringBuffer("");
			while((line = in.readLine()) != null){
				sb.append(line);
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
