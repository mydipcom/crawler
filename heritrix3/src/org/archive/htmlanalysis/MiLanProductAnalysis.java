package org.archive.htmlanalysis;

import org.archive.hibernate.HibernateBase;
import org.archive.hibernate.MiLanProduct;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author silvasong E-mail:silvasong@campray.com
 * @version 2015年3月2日 下午4:10:58
 * 
 */
public class MiLanProductAnalysis {
	
	public static void getMiLanProduct(String html,String url){
	    
		 String pid;
		
		 String pname;
		
		 float price;
		
		 float mktprice;
		
		 String brand;
		
		 String category;
		
		 String image="";
		
		 int stock=0;
		 
		 int grade = 1;
		 
		 String details="";
		
		 String introduction;
		 
	   
		 Elements elements;
		 Document page =Jsoup.parse(html);
		
		int start = html.indexOf("/*分类名*/");
		int end = html.indexOf(",", start);
		category=html.substring(start+10, end-1);
		
		start = html.indexOf("/*品牌名*/",start);
	    end = html.indexOf(",", start);
	    brand=html.substring(start+9, end-1);
	    
	    start = html.indexOf("/*商品库存状态1或是0*/",start);
	    end = html.indexOf(",", start);
	    stock=Integer.parseInt(html.substring(start+16, end-1));
	    
	    elements = page.getElementsByClass("goodsname");
	    pname = elements.get(0).text();
	    
	    elements = page.getElementsByClass("goods_bn");
	    pid = elements.get(0).ownText();
	    
	    elements = page.getElementsByClass("price1");
	    mktprice = Float.parseFloat(elements.get(1).text().replace("￥", ""));
	    
	    elements = page.getElementsByClass("mktprice1");
	    price = Float.parseFloat(elements.get(0).text().replace("￥", ""));
	    
	    elements = page.getElementsByClass("pics").get(0).getElementsByTag("img");
	    for(Element ele : elements){
	    	image += ele.attr("src")+"#";
	    }
	    image = image.substring(0,image.length()-1);
	    
	    elements = page.getElementsByClass("detail_specification");
	    for(Element td : elements.get(0).getElementsByTag("td"))
	    {
	    	details += td.text()+"#*#";
	    	if(td.text().contains("新旧程度")){
	    		grade = 0;
	    	}
	    }
	    details = details.substring(0, details.length()-3);
	    
	    elements = page.getElementsByClass("pdtdetail");
	    introduction = elements.get(1).text();
	    
	    String hql="from MiLanProduct as milan where milan.pid='"+pid+"'";
	   	boolean flag = true;
	   	MiLanProduct miLanProduct = (MiLanProduct) HibernateBase.getById(hql);
	   	if(miLanProduct == null){
	   		miLanProduct = new MiLanProduct();
	   		flag = false;
	   	}
	   	miLanProduct.setPid(pid);
	   	miLanProduct.setPname(pname);
	   	miLanProduct.setCategory(category);
	   	miLanProduct.setBrand(brand);
	   	miLanProduct.setMktprice(mktprice);
	   	miLanProduct.setPrice(price);
	   	miLanProduct.setDiscount(price-mktprice);
	   	miLanProduct.setStock(stock);
	   	miLanProduct.setGrade(grade);
	   	miLanProduct.setImage(image);
	   	miLanProduct.setUrl(url);
	   	miLanProduct.setDetails(details);
	   	miLanProduct.setIntroduction(introduction);
	   	miLanProduct.setCreatetime(System.currentTimeMillis());
	   	
	   	if(flag){
	   		HibernateBase.update(miLanProduct);
	   	}else{
	   		HibernateBase.save(miLanProduct);
	   	}
	   	
	    
	    
	}


}
