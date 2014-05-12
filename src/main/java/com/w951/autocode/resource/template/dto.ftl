<#ftl strip_whitespace=true/>
package ${packageName}.dto;

import java.io.Serializable;

/**
 * 
 * 系统版本：v1.0<br>
 * 开发人员：${author}<br>
 * 日期：${date?string("yyyy-MM-dd")}<br>
 * 时间：${time?string("HH:mm:ss")}<br>
 * 功能描述：写明作用，调用方式，使用场景，以及特殊情况<br>
 *
 */
public class ${classSimpleName}DTO implements Serializable {
	private static final long serialVersionUID = -1L;

    <#list propertyList as pl>
    private ${pl.javaType} ${pl.propertyName};
    </#list>

    <#list propertyList as pl>
    public ${pl.javaType} ${pl.getMethodName}() {
        return ${pl.propertyName};
    }

    public void ${pl.setMethodName}(${pl.javaType} ${pl.propertyName}) {
        this.${pl.propertyName} = ${pl.propertyName};
    }
    
    </#list>

}