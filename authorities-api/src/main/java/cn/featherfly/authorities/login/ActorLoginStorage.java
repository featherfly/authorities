package cn.featherfly.authorities.login;

import java.util.List;

import cn.featherfly.authorities.Actor;

/**
 * 登录用户信息储存与访问接口.
 *
 * @param <I>
 *        登录信息
 * @param <A>
 *        行动者具体类型
 * @author zhongj
 */
public interface ActorLoginStorage<I extends LoginInfo<A>, A extends Actor> {

    /**
     * 存储.
     *
     * @param key 唯一标示
     * @param actor 行动者
     * @return the LoginInfo
     */
    I store(String key, A actor);

    /**
     * 移除.
     *
     * @param actor
     *        行动者
     */
    void remove(A actor);

    /**
     * 是否包含指定key.
     *
     * @param key
     *        key
     * @return 是否包含指定key
     */
    boolean containsKey(String key);

    /**
     * 移除.
     *
     * @param key
     *        唯一标示
     */
    void remove(String key);

    /**
     * 获取指定行动者的登录信息.
     *
     * @param key
     *        唯一标示
     * @return 指定行动者的登录信息
     */
    I getLoginInfo(String key);

    /**
     * 获取指定行动者的登录信息.
     *
     * @param actor
     *        行动者
     * @return 指定行动者的登录信息
     */
    I getLoginInfo(A actor);

    /**
     * 获取登录的行动者.
     *
     * @return 登录的行动者
     */
    List<A> getLoginActors();

    /**
     * 获取登录的行动者.
     *
     * @return 登录的行动者
     */
    List<I> getLoginInfos();
}
