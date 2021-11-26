package com.fiuni.sd.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:fiuni_sd.properties")
public class Setting {

	public static Integer PAGE_SIZE = 5;

	// esto no deja hacer uso en el servicio
	//public final static String CACHE_NAME = env.getProperty("cache.name");
	
	public final static String CACHE_NAME = "platform-cache";
	
}