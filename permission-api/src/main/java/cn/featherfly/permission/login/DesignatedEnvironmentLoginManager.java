package cn.featherfly.permission.login;

import java.util.List;

import cn.featherfly.permission.PermissionActor;

/**
 * <p>
 * 指定环境下的应用认证（登录）用户管理程序
 * </p>
 *
 * @param <I> 登录信息
 * @param <A> actor类型
 * @author 钟冀
 */
public interface DesignatedEnvironmentLoginManager<I extends LoginInfo<A>, A extends PermissionActor> {

    /**
     * <p>
     * 登录. 不成功会抛出AuthenticationException
     * </p>
     *
     * @param actor 行动者
     */
    void login(A actor);

    /**
     * <p>
     * 是否登录.
     * </p>
     *
     * @return 是否登录.
     */
    boolean isLogin();

    /**
     * <p>
     * 是否登录.
     * </p>
     *
     * @param actor 行动者
     * @return 是否登录.
     */
    boolean isLogin(A actor);

    /**
     * <p>
     * 注销
     * </p>
     */
    void logout();

    /**
     * <p>
     * 注销
     * </p>
     *
     * @param actor 行动者
     */
    void logout(A actor);

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
     * 获取指定行动者的登录信息.
     * </p>
     *
     * @return 指定行动者的登录信息
     */
    I getLoginInfo();

    /**
     * <p>
     * 获取指定行动者的登录信息.
     * </p>
     *
     * @param actor 行动者
     * @return 指定行动者的登录信息
     */
    I getLoginInfo(A actor);

    /**
     * <p>
     * 获取所有在线登陆信息.
     * </p>
     *
     * @return 所有在线登录信息
     */
    List<I> getLoginInfos();

    /**
     * <p>
     * 添加登录监听器
     * </p>
     *
     * @param loginListener loginListener
     */
    void addLoginListener(LoginListener<I, A> loginListener);
}
