package com.jersey.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.jersey.resource.UserResource;

import jakarta.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/boot-jersey")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(UserResource.class);
	}

}
