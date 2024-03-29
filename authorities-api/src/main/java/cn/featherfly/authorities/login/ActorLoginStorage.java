package cn.featherfly.authorities.login;

import java.util.List;

import cn.featherfly.authorities.Actor;

/**
 * <p>
 * 登录用户信息储存与访问接口
 * </p>
 *
 * @param <I>
 *            登录信息
 * @param <A>
 *            行动者具体类型
 * @author 钟冀
 */
public interface ActorLoginStorage<I extends LoginInfo<A>, A extends Actor> {
    /**
     * <p>
     * 存储
     * </p>
     * 
     * @param key
     *            唯一标示
     * @param actor
     *            行动者
     */
    void store(String key, A actor);

    /**
     * <p>
     * 移除
     * </p>
     * 
     * @param actor
     *            行动者
     */
    void remove(A actor);

    /**
     * <p>
     * 是否包含指定key
     * </p>
     * 
     * @param key
     *            key
     * @return 是否包含指定key
     */
    boolean containsKey(String key);

    /**
     * <p>
     * 移除
     * </p>
     * 
     * @param key
     *            唯一标示
     */
    void remove(String key);

    /**
     * <p>
     * 获取指定行动者的登录信息.
     * </p>
     * 
     * @param key
     *            唯一标示
     * @return 指定行动者的登录信息
     */
    I getLoginInfo(String key);

    /**
     * <p>
     * 获取指定行动者的登录信息.
     * </p>
     * 
     * @param actor
     *            行动者
     * @return 指定行动者的登录信息
     */
    I getLoginInfo(A actor);

    /**
     * <p>
     * 获取登录的行动者.
     * </p>
     * 
     * @return 登录的行动者
     */
    List<A> getLoginActors();

    /**
     * <p>
     * 获取登录的行动者.
     * </p>
     * 
     * @return 登录的行动者
     */
    List<I> getLoginInfos();
}
