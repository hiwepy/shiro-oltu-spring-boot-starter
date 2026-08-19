package org.apache.shiro.spring.boot.oltu.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-18
 * <p>Version: 1.0
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class OltuOAuth2Token implements AuthenticationToken {

    public OltuOAuth2Token(String authCode) {
        this.authCode = authCode;
    }

    private String authCode;
    private String principal;

    /**
     * Returns the auth code.
     *
     * @return the auth code
     */
    public String getAuthCode() {
        return authCode;
    }

    /**
     * Sets the auth code.
     *
     * @param authCode the auth code
     */
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    /**
     * Returns the principal.
     *
     * @return the principal
     */
    public String getPrincipal() {
        return principal;
    }

    /**
     * Sets the principal.
     *
     * @param principal the principal
     */
    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    @Override
    /**
     * Returns the credentials.
     *
     * @return the credentials
     */
    public Object getCredentials() {
        return authCode;
    }
}
