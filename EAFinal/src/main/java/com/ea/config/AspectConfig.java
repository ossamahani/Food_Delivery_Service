package com.ea.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by amanadhikari on 9/27/16.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages="com.ea")
public class AspectConfig {

}
