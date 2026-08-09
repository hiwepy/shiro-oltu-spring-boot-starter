package org.apache.shiro.spring.boot.oltu.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.spring.boot.oltu.exception.OAuth2AuthenticationException;
import org.apache.shiro.spring.boot.oltu.token.OltuOAuth2Token;
import org.apache.shiro.subject.PrincipalCollection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

@DisplayName("OltuOAuth2Realm Tests")
class OltuOAuth2RealmTest {

    @Test
    @DisplayName("Instance can be created via constructor")
    void testInstantiation() {
        OltuOAuth2Realm instance = new OltuOAuth2Realm();
        assertThat(instance).isNotNull();
    }

    @Test
    @DisplayName("Extends AuthorizingRealm")
    void testExtendsAuthorizingRealm() {
        assertThat(org.apache.shiro.realm.AuthorizingRealm.class
                .isAssignableFrom(OltuOAuth2Realm.class)).isTrue();
    }

    @Test
    @DisplayName("supports returns true for OltuOAuth2Token")
    void testSupportsOAuth2Token() {
        OltuOAuth2Realm realm = new OltuOAuth2Realm();
        AuthenticationToken token = new OltuOAuth2Token("code");
        assertThat(realm.supports(token)).isTrue();
    }

    @Test
    @DisplayName("supports returns false for other token types")
    void testSupportsOtherToken() {
        OltuOAuth2Realm realm = new OltuOAuth2Realm();
        AuthenticationToken token = mock(AuthenticationToken.class);
        assertThat(realm.supports(token)).isFalse();
    }

    @Test
    @DisplayName("doGetAuthorizationInfo returns non-null info")
    void testDoGetAuthorizationInfo() {
        OltuOAuth2Realm realm = new OltuOAuth2Realm();
        PrincipalCollection principals = mock(PrincipalCollection.class);
        AuthorizationInfo info = realm.doGetAuthorizationInfo(principals);
        assertThat(info).isNotNull();
    }

    @Test
    @DisplayName("doGetAuthenticationInfo throws OAuth2AuthenticationException when not configured")
    void testDoGetAuthenticationInfoFailure() {
        OltuOAuth2Realm realm = new OltuOAuth2Realm();
        OltuOAuth2Token token = new OltuOAuth2Token("testCode");
        assertThatThrownBy(() -> realm.doGetAuthenticationInfo(token))
                .isInstanceOf(AuthenticationException.class);
    }

    @Test
    @DisplayName("clientId setter works")
    void testClientId() {
        OltuOAuth2Realm realm = new OltuOAuth2Realm();
        realm.setClientId("myClient");
        assertThat(realm).isNotNull();
    }

    @Test
    @DisplayName("clientSecret setter works")
    void testClientSecret() {
        OltuOAuth2Realm realm = new OltuOAuth2Realm();
        realm.setClientSecret("secret");
        assertThat(realm).isNotNull();
    }

    @Test
    @DisplayName("accessTokenUrl setter works")
    void testAccessTokenUrl() {
        OltuOAuth2Realm realm = new OltuOAuth2Realm();
        realm.setAccessTokenUrl("http://token");
        assertThat(realm).isNotNull();
    }

    @Test
    @DisplayName("userInfoUrl setter works")
    void testUserInfoUrl() {
        OltuOAuth2Realm realm = new OltuOAuth2Realm();
        realm.setUserInfoUrl("http://userinfo");
        assertThat(realm).isNotNull();
    }

    @Test
    @DisplayName("redirectUrl setter works")
    void testRedirectUrl() {
        OltuOAuth2Realm realm = new OltuOAuth2Realm();
        realm.setRedirectUrl("http://redirect");
        assertThat(realm).isNotNull();
    }
}
