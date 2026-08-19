/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
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

/**
 * <p>Configuration properties.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
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

	/**
	 * Returns the enabled.
	 *
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Sets the enabled.
	 *
	 * @param enabled the enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Returns the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Returns the failure u r i.
	 *
	 * @return the failure u r i
	 */
	public String getFailureURI() {
		return failureURI;
	}

	/**
	 * Sets the failure u r i.
	 *
	 * @param failureURI the failure u r i
	 */
	public void setFailureURI(String failureURI) {
		this.failureURI = failureURI;
	}

	/**
	 * Returns the token u r i.
	 *
	 * @return the token u r i
	 */
	public String getTokenURI() {
		return tokenURI;
	}

	/**
	 * Sets the token u r i.
	 *
	 * @param tokenURI the token u r i
	 */
	public void setTokenURI(String tokenURI) {
		this.tokenURI = tokenURI;
	}

	/**
	 * Returns the client id.
	 *
	 * @return the client id
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * Sets the client id.
	 *
	 * @param clientId the client id
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * Returns the client secret.
	 *
	 * @return the client secret
	 */
	public String getClientSecret() {
		return clientSecret;
	}

	/**
	 * Sets the client secret.
	 *
	 * @param clientSecret the client secret
	 */
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	/**
	 * Returns the default roles.
	 *
	 * @return the default roles
	 */
	public String getDefaultRoles() {
		return defaultRoles;
	}

	/**
	 * Sets the default roles.
	 *
	 * @param defaultRoles the default roles
	 */
	public void setDefaultRoles(String defaultRoles) {
		this.defaultRoles = defaultRoles;
	}

	/**
	 * Returns the default permissions.
	 *
	 * @return the default permissions
	 */
	public String getDefaultPermissions() {
		return defaultPermissions;
	}

	/**
	 * Sets the default permissions.
	 *
	 * @param defaultPermissions the default permissions
	 */
	public void setDefaultPermissions(String defaultPermissions) {
		this.defaultPermissions = defaultPermissions;
	}
	
}
