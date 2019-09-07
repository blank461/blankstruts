package lf.blank.framework.bean;

/**
 * 封装结果视图
 * 
 * @author blank
 * @Param name 视图名称
 * @Param type 视图类型
 * @param page 视图地址
 */
public class Result {
	private String name;
	private String type;
	private String page;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

}
