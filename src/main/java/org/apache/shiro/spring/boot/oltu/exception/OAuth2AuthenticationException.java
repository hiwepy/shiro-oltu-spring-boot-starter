package org.apache.shiro.spring.boot.oltu.exception;

import org.apache.shiro.authc.AuthenticationException;

@SuppressWarnings("serial")
/**\n * Exception thrown when OAuth2 authentication fails.\n *\n * @author <a href="https://github.com/loong10k">Loong Wan</a>\n * @since 1.0.0\n */
public class OAuth2AuthenticationException extends AuthenticationException {

    public OAuth2AuthenticationException(Throwable cause) {
        super(cause);
    }
    
}
