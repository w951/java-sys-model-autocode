package com.w951.autocode.service;

import java.util.Map;

import org.apache.log4j.Logger;

import com.w951.autocode.util.FreemarkerUtil;


public class ProjectTemplateHandler implements DataTemplateHandler {
	private static final Logger logger = Logger.getLogger(ProjectTemplateHandler.class);
	
	private Map<String, Object> parameter;
	
	public ProjectTemplateHandler(Map<String, Object> parameter) {
		this.parameter = parameter;
	}
	
	@Override
	public void handle() {
		setDataset();
		logger.info("开始生成项目配置文件");
		FreemarkerUtil.process("pomWar.ftl", parameter);
		FreemarkerUtil.process("ehcache.ftl", parameter);
		FreemarkerUtil.process("log4j.ftl", parameter);
		FreemarkerUtil.process("system.ftl", parameter);
		FreemarkerUtil.process("beans.ftl", parameter);
		FreemarkerUtil.process("c3p0.ftl", parameter);
		FreemarkerUtil.process("struts.ftl", parameter);
		FreemarkerUtil.process("strutsAPI.ftl", parameter);
		FreemarkerUtil.process("servlet.ftl", parameter);
		FreemarkerUtil.process("web.ftl", parameter);
	}

	@Override
	public void setDataset() {
		
	}

}
