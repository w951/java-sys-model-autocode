<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE struts PUBLIC
    "-//Apache Software Foundation//DTD Struts Configuration 2.0//EN"
    "http://struts.apache.org/dtds/struts-2.0.dtd">

<struts>	
	<package name="${modelName}/api" namespace="/${modelName}/api" extends="json-default">
		<global-results>
			<result name="Exception">/exception.jsp</result>
		</global-results>
		<global-exception-mappings>
			<exception-mapping result="Exception" exception="java.lang.Exception"></exception-mapping>
		</global-exception-mappings>
		
		<action name="*/*" class="${packageName}.api.{1}API" method="{2}">
			<result type="json">
				<param name="contentType">text/html</param>
				<param name="root">result</param>
			</result>
			<result name="array" type="json">
				<param name="contentType">text/html</param>
				<param name="root">resultArray</param>
			</result>
		</action>
	</package>
</struts>