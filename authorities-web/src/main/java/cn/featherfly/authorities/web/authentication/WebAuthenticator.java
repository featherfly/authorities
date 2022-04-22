package cn.featherfly.authorities.web.authentication;

import javax.servlet.http.HttpServletRequest;

import cn.featherfly.authorities.Actor;
import cn.featherfly.authorities.authentication.Authentication;
import cn.featherfly.authorities.authentication.Authenticator;

/**
 * WEB环境下的认证者.
 *
 * @param <A> 泛型，用于具体行动者的定义
 * @author 钟冀
 */
public interface WebAuthenticator<A extends Actor> extends Authenticator<A, HttpServletRequest> {

    /**
     * {@inheritDoc}
     */
    @Override
    void authenticate(A actor, Authentication authentication, HttpServletRequest request);
}
