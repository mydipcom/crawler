package org.archive.htmlanalysis;

import java.io.IOException;

import org.archive.hibernate.HibernateBase;
import org.archive.hibernate.NapProduct;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author silvasong E-mail:silvasong@campray.com
 * @version 2015年3月2日 下午4:09:25
 * 
 */
public class NapProductAnalysis {
	
	public static void getNapProduct(String html,String url) throws IOException{
		
	     String pid;
		
		 String name;
		
		 String price;
		
		 String brand;
		
		 String category;
		
		 String image="";
		
		 String description;
		
		 Element element;
	     
		   // Document page = Jsoup.connect(Url).timeout(10000).get();
		    Document page =Jsoup.parse(html);
		    element = page.getElementById("product-details");
			pid=element.attr("data-pid");
			name=element.attr("data-name");
			price=element.attr("data-price");
			brand=element.attr("data-brand");
			category=element.attr("data-category");
		    element = page.getElementById("editors-notes-content");
		    description = element.text();
			element = page.getElementById("thumbnails-container");
			Elements images = element.getElementsByTag("meta");
			for(int i=0 ; i<images.size();i++){
				image+=images.get(i).attr("content")+"#";
			}
			
			String hql="from NapProduct as nap where nap.pid="+pid;
			boolean flag = true;
			NapProduct nPro = (NapProduct)HibernateBase.getById(hql);
			if(nPro == null){
				nPro = new NapProduct();
				flag=false;
			}
			nPro.setBrand(brand);
			nPro.setPid(pid);
			nPro.setName(name);
			nPro.setPrice(Float.parseFloat(price.replace("USD", "")));
			nPro.setImage(image);
			nPro.setDescription(description);
			nPro.setUrl(url);
			nPro.setCategory(category);
			nPro.setCreatetime(System.currentTimeMillis());
			if(flag){
				HibernateBase.update(nPro);
			}else{
				HibernateBase.save(nPro);
			}
	}

}
