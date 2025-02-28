package cn.featherfly.authorities.web.login;

import cn.featherfly.authorities.Actor;
import cn.featherfly.authorities.login.ActorLoginStorage;

/**
 * 登录用户信息储存与访问接口.
 *
 * @param <W>
 *        登陆信息
 * @param <A>
 *        行动者具体类型
 * @author zhongj
 */
public interface WebActorLoginStorage<W extends WebLoginInfo<A>, A extends Actor>
    extends ActorLoginStorage<W, A> {
}
