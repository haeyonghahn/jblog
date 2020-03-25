package com.douzone.jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.douzone.jblog.config.web.FileUploadConfig;
import com.douzone.jblog.config.web.MessageConfig;
import com.douzone.jblog.config.web.MvcConfig;
import com.douzone.jblog.config.web.SecurityConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.douzone.jblog.controller", "com.douzone.jblog.exception"})
@Import({MvcConfig.class, SecurityConfig.class, MessageConfig.class, FileUploadConfig.class})
public class WebConfig {

}

