package com.w951.autocode.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.w951.autocode.util.FreemarkerUtil;
import com.w951.autocode.util.JDBCReader;
import com.w951.autocode.util.NamingRuleConvert;

public class DTOTemplateHandler implements DataTemplateHandler {
	private static final Logger logger = Logger.getLogger(DTOTemplateHandler.class);

	private Map<String, Object> parameter;
	private String tableName;

	public DTOTemplateHandler(Map<String, Object> parameter) {
		this.parameter = parameter;
	}

	public void handle() {
		logger.info("开始生成数据传输对象");
		
		String[] names = parameter.get("classSimpleNames").toString().split(",");
		String[] tables = parameter.get("classSimpleTables").toString().split(",");
		
		for (int i = 0 ; i < names.length ; i++) {
			parameter.put("classSimpleName", names[i]);
			this.tableName = tables[i];
			
			setDataset();
			FreemarkerUtil.process("dto.ftl", parameter);
		}
	}

	public void setDataset() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		List<String[]> orm = JDBCReader.getTableInfo(parameter.get("c3p0_driverClass").toString(), parameter.get("c3p0_url").toString(), parameter.get("c3p0_user").toString(), parameter.get("c3p0_password").toString(), tableName);
		for (int i = 0 ; i < orm.size() ; i++) {
			Map<String, Object> tmp = new HashMap<String, Object>();
			tmp.put("propertyName", orm.get(i)[1]);
			tmp.put("javaType", orm.get(i)[0]);
			tmp.put("getMethodName", "get" + NamingRuleConvert.firstLetterToUpperCase(orm.get(i)[1]));
			tmp.put("setMethodName", "set" + NamingRuleConvert.firstLetterToUpperCase(orm.get(i)[1]));
			result.add(tmp);
		}
		this.parameter.put("propertyList", result);
	}
}