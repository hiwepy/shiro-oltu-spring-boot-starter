package org.apache.shiro.spring.boot;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.AbstractShiroWebConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import win.zqxu.shiro.oltu.client.OAuthAuthorizeRealm;

@Configuration
@AutoConfigureBefore( name = {
	"org.apache.shiro.spring.config.web.autoconfigure.ShiroWebAutoConfiguration",  // shiro-spring-boot-web-starter
	"org.apache.shiro.spring.boot.ShiroBizWebAutoConfiguration" // spring-boot-starter-shiro-biz
})
@ConditionalOnWebApplication
//@ConditionalOnClass({ org.scribe.up.provider.ProvidersDefinition.class, org.scribe.oauth.OAuth20ServiceImpl.class, io.buji.oauth.OAuthRealm.class })
@ConditionalOnProperty(prefix = ShiroOltuProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ ShiroOltuProperties.class })
public class ShiroOltuWebAutoConfiguration extends AbstractShiroWebConfiguration {

	@Autowired
	private ShiroBizProperties properties;
	@Autowired
	private ShiroOltuProperties oltuProperties;
	
	@Bean
	public Realm oltuOauth2Realm(CredentialsMatcher credentialsMatcher) {
		
		OAuthAuthorizeRealm oauthRealm = new OAuthAuthorizeRealm();
		
		// 认证缓存配置:无状态情况不缓存认证信息
		oauthRealm.setAuthenticationCachingEnabled(properties.isAuthenticationCachingEnabled());
		oauthRealm.setAuthenticationCacheName(properties.getAuthenticationCacheName());
		// 授权缓存配置:无状态情况不缓存认证信息
		oauthRealm.setAuthorizationCachingEnabled(properties.isAuthorizationCachingEnabled());
		oauthRealm.setAuthorizationCacheName(properties.getAuthorizationCacheName());
		
		oauthRealm.setCacheManager(cacheManager);
		//缓存相关的配置：采用提供的默认配置即可
		oauthRealm.setCachingEnabled(properties.isCachingEnabled());

		oauthRealm.setClientId(oltuProperties.getClientId());
		oauthRealm.setClientSecret(oltuProperties.getClientSecret());
		// 凭证匹配器：该对象主要做密码校验
		oauthRealm.setCredentialsMatcher(credentialsMatcher);
		
		oauthRealm.setDefaultPermissions(oltuProperties.getDefaultPermissions());
		oauthRealm.setDefaultRoles(oltuProperties.getDefaultRoles());
		
		oauthRealm.setPermissionResolver(permissionResolver);
		oauthRealm.setRolePermissionResolver(rolePermissionResolver);
		
		oauthRealm.setTokenURI(oltuProperties.getTokenURI());
		
		return oauthRealm;
	}
 

}
