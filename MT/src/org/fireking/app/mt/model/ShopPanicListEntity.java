package org.fireking.app.mt.model;

import java.io.Serializable;

/**
 * 名店抢购列表页面
 * 
 * @author join
 *
 */
public class ShopPanicListEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 编号
	 */
	private int id;
	/**
	 * 图片
	 */
	private String image;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 原价
	 */
	private String origin_price;
	/**
	 * 折扣价
	 */
	private String panic_price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrigin_price() {
		return origin_price;
	}

	public void setOrigin_price(String origin_price) {
		this.origin_price = origin_price;
	}

	public String getPanic_price() {
		return panic_price;
	}

	public void setPanic_price(String panic_price) {
		this.panic_price = panic_price;
	}

	@Override
	public String toString() {
		return "ShopPanicListEntity [id=" + id + ", image=" + image
				+ ", title=" + title + ", description=" + description
				+ ", origin_price=" + origin_price + ", panic_price="
				+ panic_price + "]";
	}

	public ShopPanicListEntity(int id, String image, String title,
			String description, String origin_price, String panic_price) {
		super();
		this.id = id;
		this.image = image;
		this.title = title;
		this.description = description;
		this.origin_price = origin_price;
		this.panic_price = panic_price;
	}

}
