package org.apache.shiro.spring.boot;

import org.apache.shiro.spring.web.config.AbstractShiroWebFilterConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.apache.shiro.spring.boot.utils.JakartaFilterAdapter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import win.zqxu.shiro.oltu.web.OAuthAuthenticationFilter;


/**
 * 默认拦截器
 * Shiro内置了很多默认的拦截器，比如身份验证、授权等相关的。默认拦截器可以参考org.apache.shiro.web.filter.mgt.DefaultFilter中的枚举拦截器：
 * 自定义Filter通过@Bean注解后，被Spring Boot自动注册到了容器的Filter chain中，这样导致的结果是，所有URL都会被自定义Filter过滤，而不是Shiro中配置的一部分URL。
 * https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-disable-registration-of-a-servlet-or-filter
 * http://www.jianshu.com/p/bf79fdab9c19
 * https://www.cnblogs.com/wxy540843763/p/7675946.html
 * http://jinnianshilongnian.iteye.com/blog/2038646
 */
@Configuration
@AutoConfigureBefore( name = {
	"org.apache.shiro.spring.config.web.autoconfigure.ShiroWebFilterConfiguration",  // shiro-spring-boot-web-starter
	"org.apache.shiro.spring.boot.ShiroBizWebFilterConfiguration" // spring-boot-starter-shiro-biz
})
@ConditionalOnWebApplication
//@ConditionalOnClass({ org.scribe.up.provider.ProvidersDefinition.class, org.scribe.oauth.OAuth20ServiceImpl.class, io.buji.oauth.OAuthRealm.class })
/**
 * <p>Configuration properties.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@ConditionalOnProperty(prefix = ShiroOltuProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ ShiroOltuProperties.class })
public class ShiroOltuWebFilterConfiguration extends AbstractShiroWebFilterConfiguration implements ApplicationContextAware {

	//private static final Logger LOG = LoggerFactory.getLogger(ShiroOltuWebFilterConfiguration.class);
	private ApplicationContext applicationContext;
	
	@Autowired
	private ShiroBizProperties properties;
	@Autowired
	private ShiroOltuProperties oltuProperties;
	
	@Bean("oltu")
	@SuppressWarnings("rawtypes")
	/**
	 * OAuth Filter.
	 *
	 * @return the result
	 */
	public FilterRegistrationBean oauthFilter(){

		OAuthAuthenticationFilter oauthFilter = new OAuthAuthenticationFilter();

		oauthFilter.setFailureURI(oltuProperties.getFailureURI());
		oauthFilter.setLoginUrl(properties.getLoginUrl());
		oauthFilter.setState(oltuProperties.getState());
		oauthFilter.setSuccessUrl(properties.getSuccessUrl());

		FilterRegistrationBean registration = new FilterRegistrationBean<>();
		registration.setFilter(new JakartaFilterAdapter(oauthFilter));
	    registration.setEnabled(false);

	    return registration;
	}
 
	@Override
	/**
	 * Sets the application context.
	 *
	 * @param applicationContext the application context
	 * @throws BeansException if an error occurs
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * Returns the application context.
	 *
	 * @return the application context
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

}
