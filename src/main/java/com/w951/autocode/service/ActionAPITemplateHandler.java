package com.w951.autocode.service;

import java.util.Map;

import org.apache.log4j.Logger;

import com.w951.autocode.util.FreemarkerUtil;

public class ActionAPITemplateHandler implements DataTemplateHandler {
	private static final Logger logger = Logger.getLogger(ActionAPITemplateHandler.class);

	private Map<String, Object> parameter;

	public ActionAPITemplateHandler(Map<String, Object> parameter) {
		this.parameter = parameter;
	}

	public void handle() {
		logger.info("开始生成ActionAPI文件");
		
		String[] names = parameter.get("classSimpleNames").toString().split(",");
		
		for (int i = 0 ; i < names.length ; i++) {
			parameter.put("classSimpleName", names[i]);
			
			setDataset();
			FreemarkerUtil.process("actionAPI.ftl", parameter);
		}
	}

	public void setDataset() {
		
	}
}