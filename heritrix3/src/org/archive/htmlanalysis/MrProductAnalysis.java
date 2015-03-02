package org.archive.htmlanalysis;

import org.archive.hibernate.HibernateBase;
import org.archive.hibernate.MrProduct;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author silvasong E-mail:silvasong@campray.com
 * @version 2015年3月2日 下午4:09:57
 * 
 */
public class MrProductAnalysis {
	
	public static void getMrProduct(String html,String url){
		
	    String prodid;
		
		String pname;
		
		String pbrand;
		
		String pcat;

		float price;
		
	    String image="";
		
	    String editor_note="";
	    
	    String size_fit="";
		
		String details_care="";
		
		Element element;
		
		Elements elements;
		
		Document page =Jsoup.parse(html);
		elements = page.getElementsByClass("productDescription");
		for(Element pd : elements){
			if("Editors' Notes".equals(pd.child(0).text())){
				editor_note = pd.child(1).text();
			}else if("Size & Fit".equals(pd.child(0).text())){
				for(Element sf : pd.child(1).getElementsByTag("li")){
					size_fit += sf.text()+"#";
				}
				if(!size_fit.isEmpty()){
					size_fit=size_fit.substring(0, size_fit.length()-1);
				}
			}else if("Details & Care".equals(pd.child(0).text())){
				for(Element dc : pd.child(1).getElementsByTag("li")){
					details_care += dc.text()+"#";
				}
				if(!details_care.isEmpty()){
					details_care=details_care.substring(0, details_care.length()-1);
				}
			}
		}
		if(editor_note.isEmpty()){
			return;
		}
		
		element = page.getElementById("product-carousel");
	    for(Element ele : element.getElementsByTag("img")){
	    	if(ele.attr("src").endsWith(".jpg")){
	    		image += "http:"+ele.attr("src")+"#";
	    	}
	    }
	    image = image.substring(0, image.length()-1).replaceAll("xs", "l");
	    String pageStr = page.toString();
	    int epcat = pageStr.indexOf("ecomm_pcat");
		int epid = pageStr.indexOf("ecomm_prodid");
		int epva = pageStr.indexOf("ecomm_pvalue");
		pcat = pageStr.substring(pageStr.indexOf("['",epcat)+2,pageStr.indexOf("']",epcat));
		prodid = pageStr.substring(pageStr.indexOf("[",epid)+1,pageStr.indexOf("]",epid));
		price = Float.parseFloat(pageStr.substring(pageStr.indexOf("[",epva)+1,pageStr.indexOf("]",epva)));
		element = page.getElementById("product-details");
		pbrand = element.getElementsByTag("h1").get(0).text();
		pname = element.getElementsByTag("h4").get(0).text();
	    String hql="from MrProduct as mr where mr.prodid="+prodid;
		boolean flag = true;
		MrProduct mrProduct = (MrProduct) HibernateBase.getById(hql);
		if(mrProduct == null){
			mrProduct = new MrProduct();
			flag = false;
		}
		mrProduct.setProdid(prodid);
		mrProduct.setPname(pname);
		mrProduct.setPbrand(pbrand);
		mrProduct.setPcat(pcat);
		mrProduct.setPrice(price);
		mrProduct.setImage(image);
		mrProduct.setUrl(url);
		mrProduct.setEditor_note(editor_note);
		mrProduct.setSize_fit(size_fit);
		mrProduct.setDetails_care(details_care);
		mrProduct.setCreatetime(System.currentTimeMillis());
		if(flag){
			HibernateBase.update(mrProduct);
		}else{
			HibernateBase.save(mrProduct);
		}
		
	}

}
