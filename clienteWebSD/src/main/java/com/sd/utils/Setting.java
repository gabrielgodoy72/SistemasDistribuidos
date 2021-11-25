package com.sd.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:../conf/spring/fiuni_sd.properties")
public class Setting {

    public static final String CACHE_NAME = "client-web-cache";
}
