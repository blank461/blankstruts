package lf.blank.framework.bean;

import java.util.Map;

/**
 * 封装Action
 * 
 * @author blank
 * @Param name Action名
 * @Param className Action类全称
 * @Param method 要执行的方法
 * @Param results 结果视图Map
 */
public class ActionMapping {
	private String name;
	private String className;
	private String method;

	private Map<String, Result> results;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Map<String, Result> getResults() {
		return results;
	}

	public void setResults(Map<String, Result> results) {
		this.results = results;
	}

}
