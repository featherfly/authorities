package cn.featherfly.authorities.authentication;

import cn.featherfly.authorities.Actor;

/**
 * <p>
 * 认证者
 * </p>
 * 
 * @param <A>
 *            泛型，用于具体行动者的定义
 * @param <E>
 *            泛型，用于具体依赖环境的定义
 * @author 钟冀
 */
public interface Authenticator<A extends Actor, E> {
    /**
     * <p>
     * 认证. 认证失败抛出AuthenticationException异常
     * </p>
     *
     * @param actor
     *            行动者
     * @param env
     *            依赖环境
     */
    void authenticate(A actor, E env);
}
