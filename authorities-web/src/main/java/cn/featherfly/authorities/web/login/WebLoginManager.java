package cn.featherfly.authorities.web.login;

import cn.featherfly.authorities.Actor;
import cn.featherfly.authorities.login.LoginManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * Web应用登录用户管理程序.
 *
 * @param <W>
 *        登陆信息
 * @param <A>
 *        行动者具体类型
 * @author zhongj
 */
public interface WebLoginManager<W extends WebLoginInfo<A>, A extends Actor>
    extends LoginManager<HttpServletRequest, W, A> {
    /**
     * 注销.
     *
     * @param session
     *        HttpSession
     */
    void logout(HttpSession session);

    /**
     * 获取指定行动者的登录信息.
     *
     * @param session
     *        HttpSession
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
