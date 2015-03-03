package org.archive.htmlanalysis;

import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.archive.hibernate.HibernateBase;
import org.archive.hibernate.SuProduct;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author silvasong E-mail:silvasong@campray.com
 * @version 2015年3月2日 下午4:07:36
 * 
 */
public class SuningProductAnalysis {
	
	private static String cityId = "9051";
	private static String clientType = "1";
    
    public static void getSuningProduct(String html,String url) throws IOException, JSONException {
	// TODO Auto-generated method stub
	JSONObject jsonObject;
	Element element;
	Elements elements;
	int index;
	String storeId="10052";
	String catalogId="10051";
	String partNumber="";
	String productName="";
	String vendor="";
	String shopName="";
	String productParam="";
	String imageLink="";
	String promotionPrice="";
	String netPrice="";
	String productCatagory="";
	
	Document productInfo = Jsoup.parse(html);
	
	if(url.contains("http://www.suning.com/emall/cprd_")){
		productInfo = Jsoup.connect(url).timeout(10000).get();
		html = productInfo.toString();
		vendor=url.split("_")[3];
		partNumber="000000000"+url.split("_")[4];
	       //产品名字
		    elements = productInfo.getElementsByClass("proinfo-title");
			productName=elements.get(0).child(0).text();
		   //vendor
			element = productInfo.getElementById("curShopName");
			shopName = element.text();
			//产品参数
			element=productInfo.getElementById("itemParameter");
			if (element != null) {
			    elements=element.getElementsByTag("table");
				Iterator<Element> iterator = elements.iterator();
				while (iterator.hasNext()) {
					element = iterator.next();
					Elements table = element.getElementsByTag("tr");
					for(int i=0;i<table.size();i++){
						element = table.get(i);
						Elements td = element.getElementsByTag("td");
						if(td.size() != 0){
							productParam += td.get(0).getElementsByTag("span").text()+"："+td.get(1).text()+"#*#";
						}
					}
					
				}
				productParam = productParam.substring(0,productParam.length()-3);
			}
					
			//产品分类
            int categoryName1 = html.indexOf("categoryName1");
            int categoryName2 = html.indexOf("categoryName2");
            int categoryName3 = html.indexOf("categoryName3");
			productCatagory=html.substring(categoryName1, html.indexOf(",",categoryName1)).split(":")[1].replace("\"", "")+
					">"+html.substring(categoryName2, html.indexOf(",",categoryName2)).split(":")[1].replace("\"", "")+
					">"+html.substring(categoryName3, html.indexOf(",",categoryName3)).split(":")[1].replace("\"", "");
					
			//产品图片
			elements=productInfo.getElementsByClass("imgzoom-thumb-main");  
			for(int i=0;i<elements.select("img").size();i++){
				imageLink += elements.select("img").get(i).attr("src-medium")+"#";
			}
		
		}else if(Pattern.compile("http\\://product\\.suning\\.com/\\d{10}/\\d{9}.html.*").matcher(url).find()){
		index=0;
		for(int i=0;i<3;i++)
		{
			index=url.indexOf("/", index);
			index++;
		}
		vendor=url.substring(index, index+10);
		partNumber="000000000"+url.substring(index+11,index+20);
		Document Vender = Jsoup.connect("http://product.suning.com/emall/csl_10052_10051_00000000_"+partNumber+"_9051_.html").timeout(10000).get();
		if(Vender.text().startsWith("{")){
			jsonObject=new JSONObject(Vender.text());
			shopName=jsonObject.getJSONArray("shopList").getJSONObject(0).getString("shopName");
		}
		//产品名字
	 elements = productInfo.getElementsByClass("proinfo-title");
		productName=elements.get(0).child(0).text();
				
		//产品参数
		element=productInfo.getElementById("itemParameter");
		if (element != null) {
			elements=element.getElementsByTag("table");
			Iterator<Element> iterator = elements.iterator();
			while (iterator.hasNext()) {
				element = iterator.next();
				Elements table = element.getElementsByTag("tr");
				for(int i=0;i<table.size();i++){
					element = table.get(i);
					Elements td = element.getElementsByTag("td");
					if(td.size() != 0){
						productParam += td.get(0).getElementsByTag("span").text()+"："+td.get(1).text()+"#*#";
					}
				}
				
			}
			productParam = productParam.substring(0,productParam.length()-3);
		}
				
		//产品分类
		elements = productInfo.getElementsByClass("breadcrumb");
		productCatagory=elements.get(0).children().get(2).text()+">"+elements.get(0).children().get(4).text()+">"+elements.get(0).children().get(6).children().get(0).text();
				
		//产品图片
		elements=productInfo.getElementsByClass("imgzoom-thumb-main");  
		for(int i=0;i<elements.select("img").size();i++){
			imageLink += elements.select("img").get(i).attr("src-medium")+"#";
		}
		
	}else{
		index=0;
		for(int i=0;i<3;i++)
		{
			index=url.indexOf("/", index);
			index++;
		}
		partNumber="000000000"+url.substring(index,index+9);
		Document Vender = Jsoup.connect("http://product.suning.com/emall/csl_10052_10051_00000000_"+partNumber+"_9051_.html").timeout(10000).get();
		if(Vender.text().startsWith("{")){
			jsonObject=new JSONObject(Vender.text());
			vendor=jsonObject.getJSONArray("shopList").getJSONObject(0).getString("shopCode");
			if(vendor.equals("SN_001")){
				vendor="0000000000";
			}
			shopName=jsonObject.getJSONArray("shopList").getJSONObject(0).getString("shopName");
		}
		//产品名称
	    elements = productInfo.getElementsByClass("proinfo-title");
		productName=elements.get(0).child(0).text();
		
		
		//产品参数
		element=productInfo.getElementById("itemParameter");
		if (element != null) {
			elements=element.getElementsByTag("table");
			Iterator<Element> iterator = elements.iterator();
			while (iterator.hasNext()) {
				element = iterator.next();
				Elements table = element.getElementsByTag("tr");
				for(int i=0;i<table.size();i++){
					element = table.get(i);
					Elements td = element.getElementsByTag("td");
					if(td.size() != 0){
						productParam += td.get(0).getElementsByTag("span").text()+"："+td.get(1).text()+"#*#";
					}
				}
				
			}
			productParam = productParam.substring(0,productParam.length()-3);
		}else{
			return;
		}
		
		//产品分类
		 elements = productInfo.getElementsByClass("breadcrumb");
		 productCatagory=elements.get(0).children().get(2).text()+">"+elements.get(0).children().get(4).text()+">"+elements.get(0).children().get(6).children().get(0).text();
		
		  //产品图片
		elements=productInfo.getElementsByClass("imgzoom-thumb-main");  
		for(int i=0;i<elements.select("img").size();i++){
			imageLink += elements.select("img").get(i).attr("src-medium")+"#";
		}
		
	}
	if(productCatagory.equals(">>邮费")){
		return;
	}
	if(productParam.equals("")){
		return;
	}
	
	
	// 产品价格
	String param;
	if (vendor.equals("0000000000")) {
		param = "http://www.suning.com/emall/SNProductStatusView?"
				+ "storeId=" + storeId + "&catalogId=" + catalogId
				+ "&partNumber=" + partNumber + "&vendor=0000000000"
				+ "&cityId=9051&clientType=1";
		
	} else {
		
		param = "http://www.suning.com/emall/SNProductStatusView?"
				+ "storeId=" + storeId + "&catalogId=" + catalogId
				+ "&partNumber=" + partNumber + "&vendorCode=" + vendor
				+ "&cityId=9051&clientType=1";
	}
	Document  Price= Jsoup.connect(param).timeout(10000).get();
	if(Price.text().startsWith("{")){
		jsonObject = new JSONObject(Price.text());
	    if(jsonObject.getString("promotionPrice").equals("")&&jsonObject.getString("netPrice").equals("")){
		  return;
	    }
	    promotionPrice = jsonObject.getString("promotionPrice");
	    
	    netPrice=jsonObject.getString("refPrice");
	    if(netPrice.isEmpty()||netPrice.equals("")){
	    	netPrice=jsonObject.getString("netPrice");
	    }
	    
	}
	else{
		return;
	}
	
	String hql="from SuProduct as sup where sup.partNumber='"+partNumber+"'";
	boolean flag = true;
    SuProduct product=(SuProduct)HibernateBase.getById(hql);
    if(product == null){
       product = new SuProduct();
       flag = false; 
    }
    product.setProductCatagory(productCatagory);
    product.setPartNumber(partNumber);
    product.setStoreId(storeId);
    product.setCatalogId(catalogId);
    product.setProductLink(url);
    product.setPromotionPrice(Float.parseFloat(promotionPrice));
    product.setNetPrice(Float.parseFloat(netPrice));
    product.setProductImage(imageLink);
    product.setVendor(vendor);
	product.setVendorName(shopName);
	product.setProductName(productName);
	product.setProductParam(productParam);
	product.setCreatetime(System.currentTimeMillis());
	product.setDisPrice(Float.parseFloat(netPrice)-Float.parseFloat(promotionPrice));
	if(flag){
		HibernateBase.update(product);
	}else{
		HibernateBase.save(product);
	}
}

}
