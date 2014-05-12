<#ftl strip_whitespace=true/>
package ${packageName}.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.w951.util.action.CommonAction;
import com.w951.util.bean.BeanUtil;
import com.w951.util.date.DateUtil;
import ${packageName}.dto.${classSimpleName}DTO;
import ${packageName}.entity.${classSimpleName};
import ${packageName}.service.${classSimpleName}Service;

public class ${classSimpleName}Action extends CommonAction {
	private static final long serialVersionUID = -1L;
	private JSONObject result;
	private JSONArray resultArray;
	private Map<String, Object> request;
	private Map<String, Object> session;

	@Resource
	private ${classSimpleName}Service ${classLowerName}Service;
	
	// 参数

	private ${classSimpleName} ${classLowerName};
	private int page;
	private int rows;
	
	// Action
	
	@Override
	public String insert() throws Exception {
		User admin = (User) session.get("admin");
		
		${classLowerName}.set${classSimpleName}Createname(admin.getUserName());
		${classLowerName}.set${classSimpleName}Createdate(DateUtil.getDateTime());
		String message = ${classLowerName}Service.insert(${classLowerName});

		if (message != null) {
			jsonData.put("message", message);
		}
		
		result = JSONObject.fromObject(jsonData);

		return SUCCESS;
	}

	@Override
	public String delete() throws Exception {
		String message = ${classLowerName}Service.delete(${classLowerName});

		if (message != null) {
			jsonData.put("message", message);
		}
		
		result = JSONObject.fromObject(jsonData);

		return SUCCESS;
	}

	@Override
	public String update() throws Exception {
		String message = ${classLowerName}Service.update(${classLowerName});

		if (message != null) {
			jsonData.put("message", message);
		}
		
		result = JSONObject.fromObject(jsonData);

		return SUCCESS;
	}

	@Override
	public String query() throws Exception {
		List<${classSimpleName}> list = ${classLowerName}Service.queryPageList(page, rows);

		${classSimpleName}DTO dto = null;
		List<${classSimpleName}DTO> dtos = new ArrayList<${classSimpleName}DTO>();
		if (list != null && list.size() > 0) {
			for (${classSimpleName} obj : list) {
				dto = new ${classSimpleName}DTO();
				BeanUtil.beanToBean(dto, obj);
				dtos.add(dto);
			}
		}

		jsonData.put("total", ${classLowerName}Service.getCount());
		jsonData.put("rows", dtos);
		result = JSONObject.fromObject(jsonData);

		return SUCCESS;
	}

	// getter setter

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public ${classSimpleName} get${classSimpleName}() {
		return ${classLowerName};
	}

	public void set${classSimpleName}(${classSimpleName} ${classLowerName}) {
		this.${classLowerName} = ${classLowerName};
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public JSONArray getResultArray() {
		return resultArray;
	}

	public void setResultArray(JSONArray resultArray) {
		this.resultArray = resultArray;
	}

}
