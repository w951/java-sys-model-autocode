package com.w951.autocode.service;

import java.util.Map;

import org.apache.log4j.Logger;

import com.w951.autocode.util.FreemarkerUtil;
import com.w951.autocode.util.NamingRuleConvert;


public class ActionTemplateHandler implements DataTemplateHandler {
	private static final Logger logger = Logger.getLogger(ActionTemplateHandler.class);
	
	private Map<String, Object> parameter;
	
	public ActionTemplateHandler(Map<String, Object> parameter) {
		this.parameter = parameter;
	}
	
	@Override
	public void handle() {
		logger.info("开始生成Action文件");
		
		String[] names = parameter.get("classSimpleNames").toString().split(",");
		
		for (int i = 0 ; i < names.length ; i++) {
			parameter.put("classSimpleName", names[i]);
			parameter.put("classLowerName", NamingRuleConvert.firstLetterToLowerCase(names[i]));
			
			setDataset();
			FreemarkerUtil.process("action.ftl", parameter);
		}
	}

	@Override
	public void setDataset() {
		
	}

}
