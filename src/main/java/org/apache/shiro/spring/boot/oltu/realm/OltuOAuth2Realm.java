package org.apache.shiro.spring.boot.oltu.realm;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.boot.oltu.exception.OAuth2AuthenticationException;
import org.apache.shiro.spring.boot.oltu.token.OltuOAuth2Token;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-18
 * <p>Version: 1.0
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class OltuOAuth2Realm extends AuthorizingRealm {

    private String clientId;
    private String clientSecret;
    private String accessTokenUrl;
    private String userInfoUrl;
    private String redirectUrl;

    /**
     * Sets the client id.
     *
     * @param clientId the client id
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
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
     * Sets the access token url.
     *
     * @param accessTokenUrl the access token url
     */
    public void setAccessTokenUrl(String accessTokenUrl) {
        this.accessTokenUrl = accessTokenUrl;
    }

    /**
     * Sets the user info url.
     *
     * @param userInfoUrl the user info url
     */
    public void setUserInfoUrl(String userInfoUrl) {
        this.userInfoUrl = userInfoUrl;
    }

    /**
     * Sets the redirect url.
     *
     * @param redirectUrl the redirect url
     */
    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Override
    /**
     * supports.
     *
     * @param token the token
     * @return the result
     */
    public boolean supports(AuthenticationToken token) {
        return token instanceof OltuOAuth2Token;//表示此Realm只支持OAuth2Token类型
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        OltuOAuth2Token oAuth2Token = (OltuOAuth2Token) token;
        String code = oAuth2Token.getAuthCode();
        String username = extractUsername(code);

        SimpleAuthenticationInfo authenticationInfo =
                new SimpleAuthenticationInfo(username, code, getName());
        return authenticationInfo;
    }

    private String extractUsername(String code) {

        try {
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

            OAuthClientRequest accessTokenRequest = OAuthClientRequest
                    .tokenLocation(accessTokenUrl)
                    .setGrantType(GrantType.AUTHORIZATION_CODE)
                    .setClientId(clientId)
                    .setClientSecret(clientSecret)
                    .setCode(code)
                    .setRedirectURI(redirectUrl)
                    .buildQueryMessage();

            OAuthAccessTokenResponse oAuthResponse = oAuthClient.accessToken(accessTokenRequest, OAuth.HttpMethod.POST);

            String accessToken = oAuthResponse.getAccessToken();
            Long expiresIn = oAuthResponse.getExpiresIn();

            OAuthClientRequest userInfoRequest = new OAuthBearerClientRequest(userInfoUrl)
                    .setAccessToken(accessToken).buildQueryMessage();

            OAuthResourceResponse resourceResponse = oAuthClient.resource(userInfoRequest, OAuth.HttpMethod.GET, OAuthResourceResponse.class);
            String username = resourceResponse.getBody();
            return username;
        } catch (Exception e) {
            throw new OAuth2AuthenticationException(e);
        }
    }
}
