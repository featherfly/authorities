package cn.featherfly.authorities.web.authentication;

import cn.featherfly.authorities.Actor;
import cn.featherfly.authorities.authentication.Authentication;
import cn.featherfly.authorities.authentication.Authenticator;
import jakarta.servlet.http.HttpServletRequest;

/**
 * WEB环境下的认证者.
 *
 * @param <A> 泛型，用于具体行动者的定义
 * @author zhongj
 */
public interface WebAuthenticator<A extends Actor> extends Authenticator<A, HttpServletRequest> {

    /**
     * {@inheritDoc}
     */
    @Override
    void authenticate(A actor, Authentication authentication, HttpServletRequest request);
}
