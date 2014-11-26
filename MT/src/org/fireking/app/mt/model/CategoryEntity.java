package org.fireking.app.mt.model;

import java.io.Serializable;

/**
 * 分类实体类
 * 
 * @author join
 *
 */
public class CategoryEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	public int id;

	/**
	 * 名称
	 */
	public String name;

	/**
	 * 图片
	 */
	public String images;

	/**
	 * 本地资源图
	 */
	public int resImage;

	public CategoryEntity(int id, String name, int resImage) {
		super();
		this.id = id;
		this.name = name;
		this.resImage = resImage;
	}

}
