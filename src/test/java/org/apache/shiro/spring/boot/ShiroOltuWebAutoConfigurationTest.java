package org.apache.shiro.spring.boot;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ShiroOltuWebAutoConfiguration Tests")
class ShiroOltuWebAutoConfigurationTest {

    @Test
    @DisplayName("Auto-configuration class can be instantiated")
    void testInstantiation() {
        ShiroOltuWebAutoConfiguration configuration = new ShiroOltuWebAutoConfiguration();
        assertThat(configuration).isNotNull();
    }

    @Test
    @DisplayName("Has correct conditional annotation")
    void testConditionalOnProperty() {
        ConditionalOnProperty annotation =
                ShiroOltuWebAutoConfiguration.class.getAnnotation(ConditionalOnProperty.class);
        assertThat(annotation).isNotNull();
        assertThat(annotation.prefix()).isEqualTo("shiro.oltu");
        assertThat(annotation.value()).containsExactly("enabled");
        assertThat(annotation.havingValue()).isEqualTo("true");
    }

    @Test
    @DisplayName("Enables ShiroOltuProperties")
    void testEnableConfigurationProperties() {
        EnableConfigurationProperties annotation =
                ShiroOltuWebAutoConfiguration.class.getAnnotation(EnableConfigurationProperties.class);
        assertThat(annotation).isNotNull();
        assertThat(annotation.value()).contains(ShiroOltuProperties.class);
    }

    @Test
    @DisplayName("Has @AutoConfigureBefore annotation")
    void testAutoConfigureBefore() {
        AutoConfigureBefore annotation =
                ShiroOltuWebAutoConfiguration.class.getAnnotation(AutoConfigureBefore.class);
        assertThat(annotation).isNotNull();
    }

    @Test
    @DisplayName("Has @ConditionalOnWebApplication annotation")
    void testConditionalOnWebApplication() {
        ConditionalOnWebApplication annotation =
                ShiroOltuWebAutoConfiguration.class.getAnnotation(ConditionalOnWebApplication.class);
        assertThat(annotation).isNotNull();
    }

    @Test
    @DisplayName("oltuOauth2Realm method exists")
    void testOltuOauth2RealmMethodExists() throws NoSuchMethodException {
        assertThat(ShiroOltuWebAutoConfiguration.class.getMethod("oltuOauth2Realm",
                CredentialsMatcher.class)).isNotNull();
    }

    @Test
    @DisplayName("Extends AbstractShiroWebConfiguration")
    void testExtendsAbstractShiroWebConfiguration() {
        assertThat(org.apache.shiro.spring.web.config.AbstractShiroWebConfiguration.class
                .isAssignableFrom(ShiroOltuWebAutoConfiguration.class)).isTrue();
    }
}
