package org.archive.crawler;


import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author silvasong E-mail:silvasong@campray.com
 * @version 2:28:58
 * 
 */
public class Test {
	public static void main(String arg[]){
		Element element;
		String image = "";
		try {
			Document page = Jsoup.connect("http://www.mrporter.com/en-cn/mens/gucci/lightly-padded-poplin-bomber-jacket/396321").timeout(10000).get();
			/*element = page.getElementById("product-details");
			element.attr("data-pid");
			element.attr("data-name");
			element.attr("data-price");
			element.attr("data-brand");
			element.attr("data-category");
			System.out.println(element.attr("data-pid"));
			System.out.println(element.attr("data-name"));
			System.out.println(element.attr("data-price"));
			System.out.println(element.attr("data-brand"));
			System.out.println(element.attr("data-category"));
			
			element = page.getElementById("editors-notes-content");
			System.out.println(element.text());
			
			element = page.getElementById("thumbnails-container");
			Elements images = element.getElementsByTag("meta");
			
			for(int i=0 ; i<images.size();i++){
				image+=images.get(i).attr("content")+"#";
			}
			System.out.println(image);
			
			System.out.println("http://www.net-a-porter.com/product/517379?Toga/".split("/")[4].substring(0, 6));*/
			//element = page.getElementById("itemParameter");
			/*Elements elements=element.getElementsByTag("table");
			Iterator<Element> iterator = elements.iterator();
			String param = "";
			while (iterator.hasNext()) {
				element = iterator.next();
				Elements table = element.getElementsByTag("tr");
				for(int i=0;i<table.size();i++){
					element = table.get(i);
					Elements td = element.getElementsByTag("td");
					if(td.size() != 0){
						param += td.get(0).text()+":"+td.get(1).text()+"#";
					}
				}
				
			}*/
			
			//System.out.println(element.ownText());
			String pageStr = page.toString();
			int epcat = pageStr.indexOf("ecomm_pcat");
			int epid = pageStr.indexOf("ecomm_prodid");
			int epva = pageStr.indexOf("ecomm_pvalue");
			System.out.println(pageStr.substring(pageStr.indexOf("['",epcat)+2,pageStr.indexOf("']",epcat)));
			System.out.println(pageStr.substring(pageStr.indexOf("[",epid)+1,pageStr.indexOf("]",epid)));
			System.out.println(pageStr.substring(pageStr.indexOf("[",epva)+1,pageStr.indexOf("]",epva)));
			element = page.getElementById("product-carousel");
		    for(Element ele : element.getElementsByTag("img")){
		    	image += "http:"+ele.attr("src")+"#";
		    }
		    System.out.println(image.substring(0, image.length()-1).replaceAll("xs", "l"));
		    Elements elements = page.getElementsByClass("productDescription");
            
		    for(Element ele:elements){
		    	System.out.println(ele.children().get(0).text());
		    }
		    
		  
		    
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
