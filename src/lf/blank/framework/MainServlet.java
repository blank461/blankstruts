package lf.blank.framework;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lf.blank.framework.bean.ActionMapping;
import lf.blank.framework.bean.ConfigurationManager;
import lf.blank.framework.bean.Result;

/**
 * 框架入口Servlet 对所有后缀为.action的访问进行处理
 * 
 * @author blank
 * @Param manager 配置文件管理者对象
 */
@WebServlet("*.action")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConfigurationManager manager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
//		super.init();
		// 实例化管理者 仅创建一次
		manager = new ConfigurationManager();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("deprecation")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			// 1) 获得配置文件管理者提供的配置文件信息
			Map<String, ActionMapping> actionMappings = manager.getActionMappings();
			// 2) 获得请求uri并截取
			String uri = request.getRequestURI();
			String path = uri.substring(uri.lastIndexOf('/') + 1, uri.lastIndexOf('.'));
			// 3) 获取访问的ActionMapping对象
			if (!actionMappings.containsKey(path)) {
				throw new RuntimeException("未找到" + path + "Action");
			}
			ActionMapping actionMapping = actionMappings.get(path);
			// 4) 实例化具体的Action类
			Class<?> classObj = Class.forName(actionMapping.getClassName());
			Object actionObj = classObj.newInstance();
			// 5) 利用反射调用actionObj的方法并获得结果视图名称
			String methodName = actionMapping.getMethod();
			Method method = classObj.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			String resultStr = (String) method.invoke(actionObj, request, response);

			// 6) 处理结果视图
			Map<String, Result> results = actionMapping.getResults();
			if (!results.containsKey(resultStr)) {
				throw new RuntimeException("未找到" + resultStr + "视图");
			}

			Result result = results.get(resultStr);
			String type = result.getType();
			String page = result.getPage();
			if (type.equals("dispatcher")) {
				request.getRequestDispatcher(page).forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + page);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
