<#ftl strip_whitespace=true/>
log4j.rootLogger=INFO,console,info

log4j.appender.console=org.apache.log4j.ConsoleAppender
log4j.appender.console.Target=System.out
log4j.appender.console.layout=org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=[${logName}] - [%x] - %d{yyyy-MM-dd HH\:mm\:ss} %5p [%c]\:[%L] - %m%n

log4j.appender.info=org.apache.log4j.DailyRollingFileAppender
log4j.appender.info.File=../logs/${logName}/log.log
log4j.appender.info.Append=true
log4j.appender.info.Threshold=INFO
log4j.appender.info.DatePattern='.'yyyy-MM-dd
log4j.appender.info.layout=org.apache.log4j.PatternLayout
log4j.appender.info.layout.ConversionPattern=[${logName}] - [%x] - %d{yyyy-MM-dd HH\:mm\:ss} %5p [%c]\:[%L] %m%n