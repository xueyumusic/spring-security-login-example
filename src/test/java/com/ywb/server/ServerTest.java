package com.ywb.server;


import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;









import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@DirtiesContext
public class ServerTest {
	private static Logger logger = LoggerFactory.getLogger(ServerTest.class);
	
	@Test
	public void testLogin() throws Exception {
		HttpHeaders headers = getHeaders();
		headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
		form.set("username", "duanlei");
		form.set("password", "duanlei123");
		ResponseEntity<String> entity = new TestRestTemplate().exchange(
				"http://127.0.0.1:8080" + "/login", HttpMethod.POST,
				new HttpEntity<MultiValueMap<String, String>>(form, headers),
				String.class);
		logger.info("####login post return entity: {}", entity);
		assertEquals(HttpStatus.FOUND, entity.getStatusCode());
		assertTrue("Wrong location:\n" + entity.getHeaders(), entity.getHeaders()
				.getLocation().toString().endsWith("8080/"));
		assertNotNull("Missing cookie:\n" + entity.getHeaders(),
				entity.getHeaders().get("Set-Cookie"));
	}
	
	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<String> page = new TestRestTemplate().getForEntity(
				"http://127.0.0.1:8080" + "/login", String.class);
		assertEquals(HttpStatus.OK, page.getStatusCode());
		String cookie = page.getHeaders().getFirst("Set-Cookie");
		headers.set("Cookie", cookie);
		//Matcher matcher = Pattern.compile("(?s).*name=\"_csrf\".*?value=\"([^\"]+).*")
		//		.matcher(page.getBody());
		//assertTrue("No csrf token: " + page.getBody(), matcher.matches());
		//headers.set("X-CSRF-TOKEN", matcher.group(1));
		return headers;
	}
}
