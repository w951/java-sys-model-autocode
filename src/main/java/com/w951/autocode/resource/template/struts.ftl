<#ftl strip_whitespace=true/>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE struts PUBLIC
    "-//Apache Software Foundation//DTD Struts Configuration 2.0//EN"
    "http://struts.apache.org/dtds/struts-2.0.dtd">

<struts>
	<!-- 去掉默认样式 -->
	<constant name="struts.ui.theme" value="simple" />
	<constant name="struts.ui.templateDir" value="template" />
	<constant name="struts.ui.templateSuffix" value="ftl" />

	<!-- 开发模式 -->
	<constant name="struts.devMode" value="false" />

	<!-- 配置中使用反斜杠 -->
	<constant name="struts.enable.SlashesInActionNames" value="true" />

	<!-- 默认配置 -->
	<package name="default" namespace="/" extends="struts-default">
		<default-action-ref name="index"></default-action-ref>
		<action name="index">
			<result>/index.jsp</result>
		</action>
	</package>

	<!-- 验证码 -->
	<package name="valicode" namespace="/valicode" extends="json-default">
		<action name="valicodeImageAction" class="com.w951.util.valicode.ValicodeImageAction">
			<result name="success" type="stream">
				<param name="contentType">image/jpeg</param>
				<param name="inputName">imageStream</param>
				<param name="bufferSize">2048</param>
			</result>
		</action>
	</package>
	
	<package name="${modelName}" namespace="/${modelName}" extends="json-default">
		<interceptors>
			<interceptor name="PermissionInterceptor" class="com.w951.zsbus.permission.interceptor.PermissionInterceptor"></interceptor>
			<interceptor-stack name="FuntlInterceptor">
				<interceptor-ref name="PermissionInterceptor"></interceptor-ref>
				<interceptor-ref name="defaultStack"></interceptor-ref>
			</interceptor-stack>
		</interceptors>
		
		<global-results>
			<result name="Exception">/exception.jsp</result>
		</global-results>
		<global-exception-mappings>
			<exception-mapping result="Exception" exception="java.lang.Exception"></exception-mapping>
		</global-exception-mappings>
		
		<action name="Main/*" class="${packageName}.action.MainAction" method="{1}">
			<interceptor-ref name="FuntlInterceptor"></interceptor-ref>
			<result>/${"$"}{adminPath}/{1}.jsp</result>
		</action>
		
		<action name="*/action/*" class="${packageName}.action.{1}Action" method="{2}">
			<interceptor-ref name="FuntlInterceptor"></interceptor-ref>
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