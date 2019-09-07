package lf.blank.framework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action通用接口
 * 
 * @author blank
 */
public interface Action {
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
}
