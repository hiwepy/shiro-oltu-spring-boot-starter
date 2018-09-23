/*
 * Copyright (c) 2017, vindell (https://github.com/vindell).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.apache.shiro.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(ShiroOltuProperties.PREFIX)
public class ShiroOltuProperties {

	public static final String PREFIX = "shiro.oltu";

	/**
	 * Enable Shiro Oltu Oauth Client.
	 */
	private boolean enabled = false;
	/** 
	 * The state to be checked in OAuth2 authentication. 
	 */
	private String state;
	/**
	 * The failure URI, when OAuth authorization failed, redirect to this URI with
	 * parameters error and error_description.
	 */
	private String failureURI;
	/**
	 * The token URI
	 */
	private String tokenURI;
	/**
	 * The client id
	 */
	private String clientId;
	/**
	 * The client secret
	 */
	private String clientSecret;
	/**
	 * The default roles for OAuth2 authenticated user
	 */
	private String defaultRoles;
	/**
	 * The default permissions for OAuth2 authenticated user
	 */
	private String defaultPermissions;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFailureURI() {
		return failureURI;
	}

	public void setFailureURI(String failureURI) {
		this.failureURI = failureURI;
	}

	public String getTokenURI() {
		return tokenURI;
	}

	public void setTokenURI(String tokenURI) {
		this.tokenURI = tokenURI;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getDefaultRoles() {
		return defaultRoles;
	}

	public void setDefaultRoles(String defaultRoles) {
		this.defaultRoles = defaultRoles;
	}

	public String getDefaultPermissions() {
		return defaultPermissions;
	}

	public void setDefaultPermissions(String defaultPermissions) {
		this.defaultPermissions = defaultPermissions;
	}
	
}
