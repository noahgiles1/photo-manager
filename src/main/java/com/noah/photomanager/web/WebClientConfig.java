package com.noah.photomanager.web;

import com.noah.db.document.User;
import com.noah.photomanager.exceptions.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

	@Value("${user.service.url:http://localhost:8080}")
	private String userUrl;


	public User getUser(HttpServletRequest request, String userId) throws UnauthorizedException{
		String jwtToken = getJwtToken(request);
		return WebClient.create(userUrl + "/api")
				.get()
				.uri("/users/test/" + userId)
				.headers(headers -> {
					assert jwtToken != null;
					headers.setBearerAuth(
							jwtToken
					);
				})
				.retrieve()
				.onStatus(HttpStatus.UNAUTHORIZED::equals, clientResponse ->
						Mono.error(new UnauthorizedException("Unauthorized")))
				.bodyToMono(User.class)
				.block();
	}

	private String getJwtToken(HttpServletRequest httpServletRequest) {
		String jwtToken = httpServletRequest.getHeader("Authorization");
		if(jwtToken != null && jwtToken.startsWith("Bearer ")) {
			jwtToken = jwtToken.substring(7);
		}
		return jwtToken;
	}

}
