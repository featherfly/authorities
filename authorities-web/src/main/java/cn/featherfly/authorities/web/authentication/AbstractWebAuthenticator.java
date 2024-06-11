package cn.featherfly.authorities.web.authentication;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.authorities.Actor;
import cn.featherfly.authorities.authentication.Authentication;
import jakarta.servlet.http.HttpServletRequest;

/**
 * WEB环境下的抽象认证这.
 *
 * @author zhongj
 * @param <A> 泛型，用于具体行动者的定义
 */
public abstract class AbstractWebAuthenticator<A extends Actor> implements WebAuthenticator<A> {

    /** The logger. */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /** The authentications. */
    protected Set<Authentication> authentications = new HashSet<>(0);

    /**
     * {@inheritDoc}
     */
    @Override
    public void authenticate(A actor, Authentication authentication, HttpServletRequest request) {
        // 如果是可以验证的类型，则进行验证
        if (!authentications.contains(authentication)) {
            logger.debug(
                this.getClass().getSimpleName() + " is not support for " + authentication.getClass().getSimpleName());
            return;
        }
        authenticate(actor, request);
    }

    /**
     * Authenticate.
     *
     * @param actor the actor
     * @param request the request
     */
    abstract protected void authenticate(A actor, HttpServletRequest request);

    /**
     * get authentications value.
     *
     * @return authentications
     */
    public Set<Authentication> getAuthentications() {
        return authentications;
    }

    /**
     * set authentications value.
     *
     * @param authentications authentications
     */
    public void setAuthentications(Set<Authentication> authentications) {
        this.authentications = authentications;
    }

}
