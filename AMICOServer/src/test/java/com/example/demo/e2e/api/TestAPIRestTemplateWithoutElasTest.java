package com.example.demo.e2e.api;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.example.demo.e2e.api.SSLClientFactory.HttpClientType;

public class TestAPIRestTemplateWithoutElasTest {

	final static Logger log = getLogger(lookup().lookupClass());

    protected static String profile_uri;

    @BeforeAll
    public static void setupClass() {
        profile_uri = "http://localhost:8000/api/users/{user}";
    }
    
	@Test
	public void checkShowProfile() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		String url = profile_uri.replace("{user}", "amico");
		RestTemplate restTemplate = new RestTemplate(SSLClientFactory.getClientHttpRequestFactory(HttpClientType.HttpClient));

		HttpStatus status;
		HttpStatus expected = HttpStatus.UNAUTHORIZED;

		try {
			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
			status = response.getStatusCode();
		} catch (HttpStatusCodeException e) {
			status = e.getStatusCode();
		}

		Assert.assertEquals("failure - expected HTTP status " + expected, expected, status);
		log.info("The response is correct");
	}

}
