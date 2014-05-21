package com.w951.autocode.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.ResourceUtils;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerUtil {
	private static final Logger logger = Logger.getLogger(FreemarkerUtil.class);

	public static String PROJECT_ROOT = "projectRoot";
	public static String PACKAGE_NAME = "packageName";
	public static String CLASS_SIMPLE_NAME = "classSimpleName";
	public static String MODULES_PACKAGE = "modulesPackage";
	public static String METHOD_NAME = "methodName";
	public static String SYSTEM_PATH = "systemPath";

	// 模板根目录
//	private static final String templateRootWithClasspath = "classpath*:com/w951/autocode/resource/template";
//	private static final String templateRoot = "com/w951/autocode/resource/template";

	// 工程根目录
	private static String projectRoot;
	// 包名
	private static String packageName;
	// 服务所在的包路径
//	private static String modulesPackage;
	private static String classSimpleName;
	private static String templateName;
//	private static String methodName;
	private static String systemPath;

	public static final String FILE_SERVICE_NAME_SUFFIX = "Service.java";
	public static final String FILE_SERVICEIMPL_NAME_SUFFIX = "ServiceImpl.java";
	public static final String FILE_DTO_NAME_SUFFIX = ".java";
	public static final String FILE_XML_NAME_SUFFIX = ".xml";
	public static final String FILE_PROPERTIES_NAME_SUFFIX = ".properties";
	public static final String FILE_PAGE_SUFFIX = ".jsp";

	/**
	 * 数据绑定至模板
	 * 
	 * @param tpName 模板名
	 * @param param 参数
	 * @return
	 */
	public static void process(String tpName, Map<String, Object> param) {
		init(tpName, param);
		Writer out = null;
		try {
			Template temp = getTemplate(templateName);
			String fileDir = getFileDir();
			File dir = new File(fileDir);
			if (!dir.exists()) {
				if (!dir.mkdirs()) {
					logger.info("创建目录：" + fileDir + "失败");
					return;
				}
			}
			File f = new File(fileDir + getFileName());
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "utf-8"));
			try {
				temp.setEncoding("utf-8");
				temp.process(param, out);
				logger.info("文件：" + getFileDir() + getFileName() + "生成完成！");
			} catch (TemplateException e) {
				logger.error("文件：" + getFileDir() + getFileName() + "生成失败，请检查原因！");
				logger.error(e.getMessage());
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	public static Template getTemplate(String templateName) {
		Configuration cfg = new Configuration();
		Template temp = null;
		FreemarkerUtil.class.getClassLoader();
		URL path = ClassLoader.getSystemResource("");
		File tmpRootFile = null;
		if (path == null) {
			tmpRootFile = getResourceURL("resource/template");
		} else {
			String tmpRoot = path + "com/w951/autocode/resource/template";
			tmpRoot = tmpRoot.replaceAll("file:/", "");
			tmpRootFile = new File(tmpRoot);
		}
		if (tmpRootFile == null)
			throw new RuntimeException("无法取得模板根路径！");
		try {
			cfg.setDirectoryForTemplateLoading(tmpRootFile);

			cfg.setObjectWrapper(new DefaultObjectWrapper());
			temp = cfg.getTemplate(templateName);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return temp;
	}

	public static File getResourceURL(String templatePath) {
		try {
			URL url = ResourceUtils.getURL("classpath*:com/w951/autocode/resource/template");
			String path = url.getPath();
			path = path.replace("classpath*:com/w951/autocode/resource/template", templatePath);
			return new File(path);
		} catch (FileNotFoundException e) {
		}
		throw new RuntimeException("不能获取路径：classpath*:com/w951/autocode/resource/template");
	}

	private static void init(String tpName, Map<String, Object> param) {
		projectRoot = (String) param.get(PROJECT_ROOT);
		packageName = (String) param.get(PACKAGE_NAME);
		classSimpleName = (String) param.get(CLASS_SIMPLE_NAME);
//		modulesPackage = (String) param.get(MODULES_PACKAGE);
//		methodName = (String) param.get(METHOD_NAME);
		systemPath = (String) param.get(SYSTEM_PATH);
		templateName = tpName;
	}

	private static String getFileName() {
		if ("pomWar.ftl".equals(templateName))
			return "pom.xml";
		if ("ehcache.ftl".equals(templateName))
			return "ehcache.xml";
		if ("log4j.ftl".equals(templateName))
			return "log4j.properties";
		if ("system.ftl".equals(templateName))
			return "system.properties";
		if ("beans.ftl".equals(templateName))
			return "beans.xml";
		if ("c3p0.ftl".equals(templateName))
			return "c3p0.properties";
		if ("struts.ftl".equals(templateName))
			return "struts.xml";
		if ("strutsAPI.ftl".equals(templateName))
			return "struts-api.xml";
		if ("servlet.ftl".equals(templateName))
			return "InitServlet.java";
		if ("web.ftl".equals(templateName))
			return "web.xml";
		
		if ("service.ftl".equals(templateName))
			return classSimpleName + "Service.java";
		if ("serviceImpl.ftl".equals(templateName))
			return classSimpleName + "ServiceImpl.java";
		if ("dto.ftl".equals(templateName))
			return classSimpleName + "DTO.java";
		if ("action.ftl".equals(templateName))
			return classSimpleName + "Action.java";
		if ("actionMain.ftl".equals(templateName))
			return "MainAction.java";
		if ("actionAPI.ftl".equals(templateName))
			return classSimpleName + "API.java";
		
		if ("jsp.ftl".equals(templateName))
			return NamingRuleConvert.firstLetterToLowerCase(classSimpleName) + ".jsp";
		return null;
	}

	private static String getFileDir() {
		String path = null;
		if (packageName != null)
			path = packageName.replaceAll("\\.", "/");
		if ("pomWar.ftl".equals(templateName))
			return projectRoot + "/";
		if ("ehcache.ftl".equals(templateName))
			return projectRoot + "/src/main/java/";
		if ("log4j.ftl".equals(templateName))
			return projectRoot + "/src/main/java/";
		if ("system.ftl".equals(templateName))
			return projectRoot + "/src/main/java/";
		if ("beans.ftl".equals(templateName))
			return projectRoot + "/src/main/java/" + path + "/config/";
		if ("c3p0.ftl".equals(templateName))
			return projectRoot + "/src/main/java/" + path + "/config/";
		if ("struts.ftl".equals(templateName))
			return projectRoot + "/src/main/java/" + path + "/config/";
		if ("strutsAPI.ftl".equals(templateName))
			return projectRoot + "/src/main/java/" + path + "/config/";
		if ("servlet.ftl".equals(templateName))
			return projectRoot + "/src/main/java/" + path + "/servlet/";
		if ("web.ftl".equals(templateName))
			return projectRoot + "/src/main/webapp/WEB-INF/";
		
		if ("service.ftl".equals(templateName))
			return projectRoot + "/src/main/java/" + path + "/service/";
		if ("serviceImpl.ftl".equals(templateName))
			return projectRoot + "/src/main/java/" + path + "/service/impl/";
		if ("dto.ftl".equals(templateName))
			return projectRoot + "/src/main/java/" + path + "/dto/";
		if ("action.ftl".equals(templateName))
			return projectRoot + "/src/main/java/" + path + "/action/";
		if ("actionMain.ftl".equals(templateName))
			return projectRoot + "/src/main/java/" + path + "/action/";
		if ("actionAPI.ftl".equals(templateName))
			return projectRoot + "/src/main/java/" + path + "/api/";
		
		if ("jsp.ftl".equals(templateName))
			return projectRoot + "/src/main/webapp/" + systemPath + "/";
		return null;
	}
}
