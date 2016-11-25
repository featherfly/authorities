package cn.featherfly.permission.web.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.featherfly.permission.core.PermissionActor;
import cn.featherfly.permission.login.ApplicationLoginManager;

/**
 * <p>
 * Web应用登录用户管理程序
 * </p>
 *
 * @param <W>
 *            登陆信息
 * @param <A>
 *            行动者具体类型
 * @author 钟冀
 */
public interface WebApplicationLoginManager<W extends WebLoginInfo<A>, A extends PermissionActor>
        extends ApplicationLoginManager<HttpServletRequest, W, A> {
    /**
     * <p>
     * 注销
     * </p>
     * 
     * @param session
     *            HttpSession
     */
    void logout(HttpSession session);

    /**
     * <p>
     * 获取指定行动者的登录信息.
     * </p>
     * 
     * @param session
     *            HttpSession
     * @return 指定行动者的登录信息
     */
    W getLoginInfo(HttpSession session);
    // /**
    // * <p>
    // * 获取指定行动者的登录信息.
    // * </p>
    // * @param request HttpServletRequest
    // * @return 指定行动者的登录信息
    // */
    // WebLoginInfo getWebLoginInfo(HttpServletRequest request);
    // /**
    // * <p>
    // * 获取指定行动者的登录信息.
    // * </p>
    // * @param <A> 行动者具体类型
    // * @param actor 行动者
    // * @return 指定行动者的登录信息
    // */
    // <A extends Actor> WebLoginInfo getWebLoginInfo(A actor);
}
