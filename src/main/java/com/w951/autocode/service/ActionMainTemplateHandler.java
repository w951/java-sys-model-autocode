package com.w951.autocode.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.w951.autocode.util.FreemarkerUtil;
import com.w951.autocode.util.NamingRuleConvert;

public class ActionMainTemplateHandler implements DataTemplateHandler {
	private static final Logger logger = Logger.getLogger(ActionMainTemplateHandler.class);

	private Map<String, Object> parameter;

	public ActionMainTemplateHandler(Map<String, Object> parameter) {
		this.parameter = parameter;
	}

	public void handle() {
		logger.info("开始生成ActionMain");
		
		setDataset();
		FreemarkerUtil.process("actionMain.ftl", parameter);
	}

	public void setDataset() {
		String[] names = parameter.get("classSimpleNames").toString().split(",");
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		for (int i = 0 ; i < names.length ; i++) {
			Map<String, Object> tmp = new HashMap<String, Object>();
			tmp.put("classLowerName", NamingRuleConvert.firstLetterToLowerCase(names[i]));
			result.add(tmp);
		}
		this.parameter.put("classList", result);
	}
}