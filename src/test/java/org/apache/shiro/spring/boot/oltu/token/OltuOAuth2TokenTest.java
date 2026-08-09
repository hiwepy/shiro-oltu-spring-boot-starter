package org.apache.shiro.spring.boot.oltu.token;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("OltuOAuth2Token Tests")
class OltuOAuth2TokenTest {

    @Test
    @DisplayName("Constructor with authCode")
    void testConstructor() {
        OltuOAuth2Token token = new OltuOAuth2Token("testCode");
        assertThat(token).isNotNull();
        assertThat(token.getAuthCode()).isEqualTo("testCode");
    }

    @Test
    @DisplayName("Implements AuthenticationToken")
    void testImplementsAuthenticationToken() {
        assertThat(org.apache.shiro.authc.AuthenticationToken.class
                .isAssignableFrom(OltuOAuth2Token.class)).isTrue();
    }

    @Test
    @DisplayName("getCredentials returns authCode")
    void testGetCredentials() {
        OltuOAuth2Token token = new OltuOAuth2Token("myCode");
        assertThat(token.getCredentials()).isEqualTo("myCode");
    }

    @Test
    @DisplayName("authCode getter/setter")
    void testAuthCode() {
        OltuOAuth2Token token = new OltuOAuth2Token("initial");
        token.setAuthCode("updated");
        assertThat(token.getAuthCode()).isEqualTo("updated");
    }

    @Test
    @DisplayName("principal getter/setter")
    void testPrincipal() {
        OltuOAuth2Token token = new OltuOAuth2Token("code");
        token.setPrincipal("user123");
        assertThat(token.getPrincipal()).isEqualTo("user123");
    }

    @Test
    @DisplayName("getPrincipal returns null by default")
    void testDefaultPrincipal() {
        OltuOAuth2Token token = new OltuOAuth2Token("code");
        assertThat(token.getPrincipal()).isNull();
    }
}
