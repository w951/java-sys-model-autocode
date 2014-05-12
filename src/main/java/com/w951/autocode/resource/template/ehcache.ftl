<#ftl strip_whitespace=true/>
<?xml version="1.0" encoding="UTF-8"?>
<ehcache xsi:noNamespaceSchemaLocation="ehcache.xsd"
	updateCheck="false" monitoring="autodetect" dynamicConfig="true">
	<diskStore path="java.io.tmpdir" />
	<defaultCache 
		eternal="false" 
		maxElementsInMemory="10000"
		overflowToDisk="true" 
		diskPersistent="false" 
		timeToIdleSeconds="120"
		timeToLiveSeconds="120" 
		memoryStoreEvictionPolicy="LRU" />
</ehcache>