package org.apache.shiro.spring.boot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ShiroOltuProperties Tests")
class ShiroOltuPropertiesTest {

    @Test
    @DisplayName("Default values are correct")
    void testDefaults() {
        ShiroOltuProperties props = new ShiroOltuProperties();
        assertThat(props.isEnabled()).isFalse();
        assertThat(props.getState()).isNull();
        assertThat(props.getFailureURI()).isNull();
        assertThat(props.getTokenURI()).isNull();
        assertThat(props.getClientId()).isNull();
        assertThat(props.getClientSecret()).isNull();
        assertThat(props.getDefaultRoles()).isNull();
        assertThat(props.getDefaultPermissions()).isNull();
    }

    @Test
    @DisplayName("PREFIX constant has expected value")
    void testPREFIXConstant() {
        assertThat(ShiroOltuProperties.PREFIX).isEqualTo("shiro.oltu");
    }

    @Test
    @DisplayName("enabled getter/setter")
    void testEnabled() {
        ShiroOltuProperties props = new ShiroOltuProperties();
        props.setEnabled(true);
        assertThat(props.isEnabled()).isTrue();
    }

    @Test
    @DisplayName("state getter/setter")
    void testState() {
        ShiroOltuProperties props = new ShiroOltuProperties();
        props.setState("testState");
        assertThat(props.getState()).isEqualTo("testState");
    }

    @Test
    @DisplayName("failureURI getter/setter")
    void testFailureURI() {
        ShiroOltuProperties props = new ShiroOltuProperties();
        props.setFailureURI("/error");
        assertThat(props.getFailureURI()).isEqualTo("/error");
    }

    @Test
    @DisplayName("tokenURI getter/setter")
    void testTokenURI() {
        ShiroOltuProperties props = new ShiroOltuProperties();
        props.setTokenURI("http://token");
        assertThat(props.getTokenURI()).isEqualTo("http://token");
    }

    @Test
    @DisplayName("clientId getter/setter")
    void testClientId() {
        ShiroOltuProperties props = new ShiroOltuProperties();
        props.setClientId("myClient");
        assertThat(props.getClientId()).isEqualTo("myClient");
    }

    @Test
    @DisplayName("clientSecret getter/setter")
    void testClientSecret() {
        ShiroOltuProperties props = new ShiroOltuProperties();
        props.setClientSecret("secret");
        assertThat(props.getClientSecret()).isEqualTo("secret");
    }

    @Test
    @DisplayName("defaultRoles getter/setter")
    void testDefaultRoles() {
        ShiroOltuProperties props = new ShiroOltuProperties();
        props.setDefaultRoles("admin,user");
        assertThat(props.getDefaultRoles()).isEqualTo("admin,user");
    }

    @Test
    @DisplayName("defaultPermissions getter/setter")
    void testDefaultPermissions() {
        ShiroOltuProperties props = new ShiroOltuProperties();
        props.setDefaultPermissions("read,write");
        assertThat(props.getDefaultPermissions()).isEqualTo("read,write");
    }
}
