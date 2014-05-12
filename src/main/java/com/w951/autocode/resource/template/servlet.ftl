<#ftl strip_whitespace=true/>
package ${packageName}.servlet;

import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.w951.util.bean.PropertyUtil;

public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = -1L;

	public void init() throws ServletException {
		PropertyUtil property = PropertyUtil.getInstance();
		Properties pro = property.loadProperties("system");
		
		String systemName = pro.getProperty("system.name");
		String systemPath = pro.getProperty("system.path");
		String systemProj = pro.getProperty("system.proj");
		
		System.setProperty("systemName", systemName);
		System.setProperty("systemPath", systemPath);
		System.setProperty("systemProj", systemProj);
	}

}
