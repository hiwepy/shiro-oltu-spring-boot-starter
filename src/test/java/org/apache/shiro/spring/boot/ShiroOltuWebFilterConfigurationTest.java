package org.apache.shiro.spring.boot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ShiroOltuWebFilterConfiguration Tests")
class ShiroOltuWebFilterConfigurationTest {

    @Test
    @DisplayName("Instance can be created via constructor")
    void testInstantiation() {
        ShiroOltuWebFilterConfiguration instance = new ShiroOltuWebFilterConfiguration();
        assertThat(instance).isNotNull();
    }

    @Test
    @DisplayName("Has @Configuration annotation")
    void testConfigurationAnnotation() {
        assertThat(ShiroOltuWebFilterConfiguration.class.getAnnotation(
                org.springframework.context.annotation.Configuration.class)).isNotNull();
    }

    @Test
    @DisplayName("Has @ConditionalOnProperty annotation")
    void testConditionalOnProperty() {
        ConditionalOnProperty annotation = ShiroOltuWebFilterConfiguration.class.getAnnotation(
                ConditionalOnProperty.class);
        assertThat(annotation).isNotNull();
        assertThat(annotation.prefix()).isEqualTo("shiro.oltu");
        assertThat(annotation.havingValue()).isEqualTo("true");
    }

    @Test
    @DisplayName("Implements ApplicationContextAware")
    void testApplicationContextAware() {
        assertThat(ApplicationContextAware.class.isAssignableFrom(
                ShiroOltuWebFilterConfiguration.class)).isTrue();
    }

    @Test
    @DisplayName("setApplicationContext and getApplicationContext work")
    void testApplicationContext() {
        ShiroOltuWebFilterConfiguration config = new ShiroOltuWebFilterConfiguration();
        assertThat(config.getApplicationContext()).isNull();
        ApplicationContext mockCtx = org.mockito.Mockito.mock(ApplicationContext.class);
        config.setApplicationContext(mockCtx);
        assertThat(config.getApplicationContext()).isEqualTo(mockCtx);
    }

    @Test
    @DisplayName("Has @AutoConfigureBefore annotation")
    void testAutoConfigureBefore() {
        AutoConfigureBefore annotation = ShiroOltuWebFilterConfiguration.class.getAnnotation(
                AutoConfigureBefore.class);
        assertThat(annotation).isNotNull();
    }

    @Test
    @DisplayName("Has @EnableConfigurationProperties annotation")
    void testEnableConfigurationProperties() {
        EnableConfigurationProperties annotation = ShiroOltuWebFilterConfiguration.class.getAnnotation(
                EnableConfigurationProperties.class);
        assertThat(annotation).isNotNull();
        assertThat(annotation.value()).contains(ShiroOltuProperties.class);
    }

    @Test
    @DisplayName("Has @ConditionalOnWebApplication annotation")
    void testConditionalOnWebApplication() {
        ConditionalOnWebApplication annotation = ShiroOltuWebFilterConfiguration.class.getAnnotation(
                ConditionalOnWebApplication.class);
        assertThat(annotation).isNotNull();
    }

    @Test
    @DisplayName("oauthFilter method exists and returns FilterRegistrationBean")
    void testOauthFilterMethodExists() throws NoSuchMethodException {
        assertThat(ShiroOltuWebFilterConfiguration.class.getMethod("oauthFilter")).isNotNull();
        assertThat(FilterRegistrationBean.class.isAssignableFrom(
                ShiroOltuWebFilterConfiguration.class.getMethod("oauthFilter").getReturnType())).isTrue();
    }

    @Test
    @DisplayName("oauthFilter creates filter with correct configuration")
    void testOauthFilter() throws Exception {
        ShiroOltuWebFilterConfiguration config = new ShiroOltuWebFilterConfiguration();

        // Set up required fields via reflection
        ShiroBizProperties bizProps = new ShiroBizProperties();
        ShiroOltuProperties oltuProps = new ShiroOltuProperties();
        oltuProps.setFailureURI("/error");
        oltuProps.setState("testState");

        java.lang.reflect.Field bizField = ShiroOltuWebFilterConfiguration.class.getDeclaredField("properties");
        bizField.setAccessible(true);
        bizField.set(config, bizProps);

        java.lang.reflect.Field oltuField = ShiroOltuWebFilterConfiguration.class.getDeclaredField("oltuProperties");
        oltuField.setAccessible(true);
        oltuField.set(config, oltuProps);

        FilterRegistrationBean<?> result = config.oauthFilter();
        assertThat(result).isNotNull();
        assertThat(result.isEnabled()).isFalse();
    }
}
