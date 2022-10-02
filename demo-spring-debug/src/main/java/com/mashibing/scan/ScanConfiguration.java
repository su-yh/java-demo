package com.mashibing.scan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="com.mashibing.aop.annotation")
public class ScanConfiguration { }