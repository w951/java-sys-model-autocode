package com.w951.autocode.service;

import java.util.Map;

import org.apache.log4j.Logger;

import com.w951.autocode.util.FreemarkerUtil;


public class ServiceTemplateHandler implements DataTemplateHandler {
	private static final Logger logger = Logger.getLogger(ServiceTemplateHandler.class);
	
	private Map<String, Object> parameter;
	
	public ServiceTemplateHandler(Map<String, Object> parameter) {
		this.parameter = parameter;
	}
	
	@Override
	public void handle() {
		logger.info("开始生成业务逻辑文件");
		
		String[] names = parameter.get("classSimpleNames").toString().split(",");
		
		for (int i = 0 ; i < names.length ; i++) {
			parameter.put("classSimpleName", names[i]);
			
			setDataset();
			FreemarkerUtil.process("service.ftl", parameter);
			FreemarkerUtil.process("serviceImpl.ftl", parameter);
		}
	}

	@Override
	public void setDataset() {
		
	}

}
