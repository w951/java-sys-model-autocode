<#ftl strip_whitespace=true/>
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://java.sun.com/xml/ns/javaee" xmlns:web="http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
	version="3.0">
	<display-name>${name}</display-name>
	<distributable />
	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>
			classpath:${packagePath}/config/beans.xml
	    </param-value>
	</context-param>
	<filter>
		<filter-name>encoding</filter-name>
		<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
		<init-param>
			<param-name>encoding</param-name>
			<param-value>UTF-8</param-value>
		</init-param>
	</filter>
	<filter-mapping>
		<filter-name>encoding</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
	<filter>
		<filter-name>OpenSessionInViewFilter</filter-name>
		<filter-class>org.springframework.orm.hibernate4.support.OpenSessionInViewFilter</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>OpenSessionInViewFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>

	<filter>
		<filter-name>struts2</filter-name>
		<filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>
		<init-param>
			<param-name>config</param-name>
			<param-value>
				struts-default.xml,struts-plugin.xml,
				${packagePath}/config/struts.xml
			</param-value>
		</init-param>
	</filter>
	<filter-mapping>
		<filter-name>struts2</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
	<filter>
		<filter-name>JspFilter</filter-name>
		<filter-class>com.w951.util.filter.JSPFilter</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>JspFilter</filter-name>
		<url-pattern>*.jsp</url-pattern>
	</filter-mapping>
	<filter>
		<filter-name>IPFilter</filter-name>
		<filter-class>com.w951.util.filter.IPFilter</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>IPFilter</filter-name>
		<url-pattern>*</url-pattern>
	</filter-mapping>
	
	<servlet>
		<servlet-name>InitServlet</servlet-name>
		<servlet-class>${packageName}.servlet.InitServlet</servlet-class>
		<load-on-startup>0</load-on-startup>
	</servlet>
	<servlet-mapping>
		<servlet-name>InitServlet</servlet-name>
		<url-pattern>/servlet/InitServlet</url-pattern>
	</servlet-mapping>
	
	<welcome-file-list>
		<welcome-file>/index</welcome-file>
	</welcome-file-list>
	
	<error-page>
		<error-code>500</error-code>
		<location>/exception.jsp</location>
	</error-page>

	<session-config>
		<session-timeout>30</session-timeout>
	</session-config>
</web-app>