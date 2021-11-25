package com.fiuni.sd.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:fiuni_sd.properties")
public class Setting {
	public static final Integer PAGE_SIZE = 5;

	public static final String CACHE_NAME = "platform-cache";
}