<#ftl strip_whitespace=true/>
package ${packageName}.service;

import org.springframework.transaction.annotation.Transactional;

import com.w951.util.service.CommonService;
import ${packageName}.entity.${classSimpleName};

/**
 * 
 * 系统版本：v1.0<br>
 * 开发人员：${author}<br>
 * 日期：${date?string("yyyy-MM-dd")}<br>
 * 时间：${time?string("HH:mm:ss")}<br>
 * 功能描述：写明作用，调用方式，使用场景，以及特殊情况<br>
 *
 */
@Transactional
public interface ${classSimpleName}Service extends CommonService<${classSimpleName}> {
	
}