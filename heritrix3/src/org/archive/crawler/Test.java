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
		Elements elements;
		String image = "";
		try {
			Document page = Jsoup.connect("http://www.suning.com/emall/cprd_10052_10051_0070071501_121616077_.html").timeout(10000).get();
			String html = page.toString();
			int categoryName1 = html.indexOf("categoryName1");
			
			int categoryName2 = html.indexOf("categoryName2");
			
			int categoryName3 = html.indexOf("categoryName3");
			
			System.out.println(html.substring(categoryName1, html.indexOf(",",categoryName1)).split(":")[1].replace("\"", "")+
					">"+html.substring(categoryName2, html.indexOf(",",categoryName2)).split(":")[1].replace("\"", "")+
					">"+html.substring(categoryName3, html.indexOf(",",categoryName3)).split(":")[1].replace("\"", ""));
			elements = page.getElementsByClass("proinfo-title");
			System.out.println(elements.get(0).child(0).text());
			element = page.getElementById("curShopName");
			System.out.println(element.text());
//		    int start = html.indexOf("/*分类名*/");
//			int end = html.indexOf(",", start);
//			
//			System.out.println(html.substring(start+10, end-1));
//			
//			//start = html.indexOf("/*品牌名*/",start);
//		    end = html.indexOf(",", start);
//		    System.out.println(html.substring(start+9, end-1));
//		    
//	       // start = html.indexOf("/*商品库存状态1或是0*/",start);
//		    end = html.indexOf(",", start);
//		    System.out.println(html.substring(start+16, end-1));
//		    elements = page.getElementsByClass("goodsname");
//		    System.out.println(elements.get(0).text());
//		    elements = page.getElementsByClass("goods_bn");
//		    System.out.println(elements.get(0).ownText());
//		    elements = page.getElementsByClass("price1");
//		    System.out.println(elements.get(1).text().replace("￥", ""));
//		    elements = page.getElementsByClass("mktprice1");
//		    System.out.println(elements.get(0).text().replace("￥", ""));
//		    elements = page.getElementsByClass("pics").get(0).getElementsByTag("img");
//		    for(Element ele : elements){
//		    	//System.out.println(ele.attr("src"));
//		    }
//		    elements = page.getElementsByClass("detail_specification");
//		    for(Element td : elements.get(0).getElementsByTag("td"))
//		    {
//		    	System.out.println(td.text());
//		    	
//		    }
//		    elements = page.getElementsByClass("pdtdetail");
//		    System.out.println(elements.get(1).text());
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
			/*String pageStr = page.toString();
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
		    }*/
		    
		  
		    
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
