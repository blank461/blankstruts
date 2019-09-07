package lf.blank.framework.bean;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * blankstruts.xml配置文件管理者 读取配置文件
 * 
 * @author blank
 * @Param actionMappings 所有配置的Action
 */
public class ConfigurationManager {
	private Map<String, ActionMapping> actionMappings;

	public ConfigurationManager() {
		// TODO Auto-generated constructor stub
		actionMappings = new HashMap<String, ActionMapping>();
		this.init();
	}

	/**
	 * 初始化函数，读取xml文件
	 */
	private void init() {
		try {
			// 1) 获得配置文件输入流
			InputStream inputStream = ConfigurationManager.class.getResourceAsStream("/blankstruts.xml");
			// 2) 创建SAXReader对象
			SAXReader saxReader = new SAXReader();
			// 3)获得文档信息
			Document doc = saxReader.read(inputStream);
			// 4)获得action标签列表
			List<Node> actionNodes = doc.selectNodes("//action");
			// 5)遍历action标签将信息加入actionMappings
			for (Node node : actionNodes) {
				Element actionElement = (Element) node;
				// 新建一个ActionMapping对象
				ActionMapping actionMapping = new ActionMapping();
				actionMapping.setName(actionElement.attributeValue("name"));
				actionMapping.setClassName(actionElement.attributeValue("class"));
				if (actionElement.attributeValue("method") == null) {
					// 设置默认方法
					actionMapping.setMethod("execute");
				} else {
					actionMapping.setMethod(actionElement.attributeValue("method"));
				}
				// 获取action内配置的结果视图
				List<Element> resultList = actionElement.elements("result");

				Map<String, Result> results = new HashMap<String, Result>();
				for (Element resultElement : resultList) {
					// 新建结果视图对象进行封装
					Result result = new Result();
					result.setName(resultElement.attributeValue("name"));
					if (resultElement.attributeValue("type") == null) {
						// 设置默认视图类型为 转发
						result.setType("dispatcher");
					} else {
						result.setType(resultElement.attributeValue("type"));
					}

					result.setPage(resultElement.getText().trim());
					// 将result放入results中
					results.put(result.getName(), result);
				}

				actionMapping.setResults(results);

				actionMappings.put(actionMapping.getName(), actionMapping);
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Map<String, ActionMapping> getActionMappings() {
		return actionMappings;
	}

}
