package org.archive.hibernate;

/**
 * @author silvasong E-mail:silvasong@campray.com
 * @version 2015年2月10日 下午2:41:05
 * 
 */
public class MiLanProduct {
	
    private int id;
	
	private String pid;
	
	private String pname;
	
	private float price;
	
	private float mktprice;
	
	private float discount;
	
	private String brand;
	
	private String category;
	
	private String url;
	
	private String image;
	
	private int stock;
	
	private int grade;
	
	private String details;
	
	private String introduction;
	
	private long createtime;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getMktprice() {
		return mktprice;
	}

	public void setMktprice(float mktprice) {
		this.mktprice = mktprice;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getStock() {
		return stock;
	}
    
	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
    
	public long getCreatetime() {
			return createtime;
	}

	public void setCreatetime(long createtime) {
			this.createtime = createtime;
	}

}
