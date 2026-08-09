package org.apache.shiro.spring.boot.oltu.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("OAuth2AuthenticationException Tests")
class OAuth2AuthenticationExceptionTest {

    @Test
    @DisplayName("Constructor with cause")
    void testConstructorWithCause() {
        RuntimeException cause = new RuntimeException("root cause");
        OAuth2AuthenticationException ex = new OAuth2AuthenticationException(cause);
        assertThat(ex).isNotNull();
        assertThat(ex.getCause()).isEqualTo(cause);
    }

    @Test
    @DisplayName("Extends AuthenticationException")
    void testExtendsAuthenticationException() {
        assertThat(org.apache.shiro.authc.AuthenticationException.class
                .isAssignableFrom(OAuth2AuthenticationException.class)).isTrue();
    }

    @Test
    @DisplayName("Constructor with null cause")
    void testConstructorWithNullCause() {
        OAuth2AuthenticationException ex = new OAuth2AuthenticationException(null);
        assertThat(ex).isNotNull();
    }
}
