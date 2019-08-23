package cn.featherfly.permission.login;

import cn.featherfly.permission.PermissionActor;

/**
 * <p>
 * LoginListener
 * </p>
 *
 * @param <I>
 *            具体的LoginInfo类型
 * @param <A>
 *            actor类型
 * @author 钟冀
 */
public interface LoginListener<I extends LoginInfo<A>, A extends PermissionActor> {
    /**
     * <p>
     * 登录成功以后执行
     * </p>
     * 
     * @param loginEvent
     *            loginEvent
     */
    void onLogin(LoginEvent<I, A> loginEvent);

    /**
     * <p>
     * 注销成功以后执行
     * </p>
     * 
     * @param loginEvent
     *            loginEvent
     */
    void onLogout(LoginEvent<I, A> loginEvent);
}
