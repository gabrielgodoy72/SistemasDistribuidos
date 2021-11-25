package com.fiuni.sd.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("security.jwt")
public class JwtSecurityProperties {

	private String secretKey;

	private String jwtExpirationInMs;

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getJwtExpirationInMs() {
		return jwtExpirationInMs;
	}

	public void setJwtExpirationInMs(String jwtExpirationInMs) {
		this.jwtExpirationInMs = jwtExpirationInMs;
	}
	
	
}
