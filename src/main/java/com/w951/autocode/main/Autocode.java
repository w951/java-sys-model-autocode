package com.w951.autocode.main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.w951.autocode.service.ActionAPITemplateHandler;
import com.w951.autocode.service.ActionMainTemplateHandler;
import com.w951.autocode.service.ActionTemplateHandler;
import com.w951.autocode.service.DTOTemplateHandler;
import com.w951.autocode.service.JSPTemplateHandler;
import com.w951.autocode.service.ProjectTemplateHandler;
import com.w951.autocode.service.ServiceTemplateHandler;

public class Autocode {
	public static Map<String, Object> getMapParameter() throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();

		URL url = ClassLoader.getSystemResource("");
		String path = url.getPath();
		
		InputStream in = new BufferedInputStream(new FileInputStream(new File(path + "project.properties")));
		Properties pro = new Properties();
		pro.load(in);
		
		// Maven
		map.put("groupId", pro.get("groupId"));
		map.put("artifactId", pro.get("artifactId"));
		map.put("name", pro.get("name"));
		map.put("logName", pro.get("logName"));
		
		// 项目信息
		map.put("projectRoot", pro.get("projectRoot"));
		map.put("packageName", pro.get("packageName"));
		map.put("packagePath", pro.get("packagePath"));
		map.put("modelName", pro.get("modelName"));
		map.put("classSimpleNames", pro.get("classSimpleNames"));
		map.put("classSimpleTables", pro.get("classSimpleTables"));
		
		// 系统信息
		map.put("systemName", pro.get("systemName"));
		map.put("systemPath", pro.get("systemPath"));
		
		// c3p0
		map.put("c3p0_url", pro.get("c3p0_url"));
		map.put("c3p0_user", pro.get("c3p0_user"));
		map.put("c3p0_password", pro.get("c3p0_password"));
		map.put("c3p0_driverClass", pro.get("c3p0_driverClass"));
		
		// 注释信息
		map.put("author", pro.get("author"));
		map.put("date", new Date());
		map.put("time", new Date());

		return map;
	}

	public static void main(String[] args) throws IOException {
		// 项目配置文件
		ProjectTemplateHandler project = new ProjectTemplateHandler(getMapParameter());
		project.handle();
		
		// 数据传输对象
		DTOTemplateHandler dto = new DTOTemplateHandler(getMapParameter());
		dto.handle();
		
		// 业务逻辑文件
		ServiceTemplateHandler service = new ServiceTemplateHandler(getMapParameter());
		service.handle();
		
		// Action文件
		ActionTemplateHandler action = new ActionTemplateHandler(getMapParameter());
		action.handle();
		
		// ActionMain文件
		ActionMainTemplateHandler actionMain = new ActionMainTemplateHandler(getMapParameter());
		actionMain.handle();
		
		// ActionAPI文件
		ActionAPITemplateHandler actionAPI = new ActionAPITemplateHandler(getMapParameter());
		actionAPI.handle();
		
		// JSP文件
		JSPTemplateHandler jsp = new JSPTemplateHandler(getMapParameter());
		jsp.handle();
	}
}
