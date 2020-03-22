package cn.featherfly.authorities.web.login;

import cn.featherfly.authorities.Actor;
import cn.featherfly.authorities.login.EnvironmentLoginManager;

/**
 * <p>
 * 指定环境Web应用登录用户管理程序
 * </p>
 *
 * @param <W> 登陆信息
 * @param <A> 行动者具体类型
 * @author 钟冀
 */
public interface WebEnvironmentLoginManager<W extends WebLoginInfo<A>, A extends Actor>
        extends EnvironmentLoginManager<W, A> {
}
