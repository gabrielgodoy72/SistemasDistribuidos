package Managers;

import java.net.http.HttpClient;

public abstract class AbstractBaseManager {
	private final String BASE_URL = "http://localhost:8080/api/";
	private final HttpClient httpClient;

	public AbstractBaseManager() {
		httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
	}

	protected String getBaseUrl() {
		return BASE_URL;
	}

	protected HttpClient getHttpClient() {
		return httpClient;
	}
}