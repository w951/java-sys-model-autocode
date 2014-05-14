<#ftl strip_whitespace=true/>
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"
	xmlns:aop="http://www.springframework.org/schema/aop" xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans-3.1.xsd
		http://www.springframework.org/schema/context
		http://www.springframework.org/schema/context/spring-context-3.1.xsd
		http://www.springframework.org/schema/aop
		http://www.springframework.org/schema/aop/spring-aop-3.1.xsd
		http://www.springframework.org/schema/tx
		http://www.springframework.org/schema/tx/spring-tx-3.1.xsd">

	<context:annotation-config />
	<context:component-scan base-package="com.w951.orm" />
	<context:component-scan base-package="${packageName}.service" />

	<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource"
		destroy-method="close">
		<property name="driverClass" value="${"$"}{c3p0.driverClass}"></property>
		<property name="jdbcUrl" value="${"$"}{c3p0.url}"></property>
		<property name="user" value="${"$"}{c3p0.user}"></property>
		<property name="password" value="${"$"}{c3p0.password}"></property>
		<property name="acquireIncrement" value="${"$"}{c3p0.acquireIncrement}"></property>
		<property name="initialPoolSize" value="${"$"}{c3p0.initialPoolSize}"></property>
		<property name="maxIdleTime" value="${"$"}{c3p0.maxIdleTime}"></property>
		<property name="maxPoolSize" value="${"$"}{c3p0.maxPoolSize}"></property>
		<property name="minPoolSize" value="${"$"}{c3p0.minPoolSize}"></property>
		<property name="acquireRetryDelay" value="1000"></property>
		<property name="acquireRetryAttempts" value="60"></property>
		<property name="breakAfterAcquireFailure" value="false"></property>
	</bean>

	<bean id="sessionFactory"
		class="org.springframework.orm.hibernate4.LocalSessionFactoryBean">
		<property name="dataSource" ref="dataSource" />
		<property name="packagesToScan">
			<list>
				<value>${packageName}.entity</value>
			</list>
		</property>
		<property name="hibernateProperties">
			<value>
				hibernate.dialect=org.hibernate.dialect.MySQLDialect
				hibernate.show_sql=false
				hibernate.format_sql=true
				hibernate.jdbc.batch_size=50
				hibernate.cache.region.factory_class=org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory
				hibernate.cache.use_query_cache=true
				hibernate.cache.use_second_level_cache=true
			</value>
		</property>
	</bean>

	<bean id="hibernateTemplate" class="org.springframework.orm.hibernate4.HibernateTemplate">
		<property name="sessionFactory" ref="sessionFactory"></property>
	</bean>

	<!-- Annotation事务 -->
	<bean id="txManager"
		class="org.springframework.orm.hibernate4.HibernateTransactionManager">
		<property name="sessionFactory" ref="sessionFactory" />
	</bean>
	<tx:annotation-driven transaction-manager="txManager" />

	<!-- 属性文件读入 -->
	<bean id="propertyConfigurer"
		class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
		<property name="locations">
			<list>
				<value>classpath:${packagePath}/config/c3p0.properties</value>
			</list>
		</property>
	</bean>

</beans>