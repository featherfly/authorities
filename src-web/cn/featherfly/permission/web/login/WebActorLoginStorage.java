
package cn.featherfly.permission.web.login;

import java.util.List;

import cn.featherfly.permission.core.PermissionActor;
import cn.featherfly.permission.login.ActorLoginStorage;

/**
 * <p>
 * 登录用户信息储存与访问接口
 * </p>
 *
 * @author 钟冀
 */
public interface WebActorLoginStorage<A extends PermissionActor> extends ActorLoginStorage<WebLoginInfo<A>, A>{
	/**
	 * <p>
	 * 存储
	 * </p>
	 * @param <A> 行动者具体类型
	 * @param key 唯一标示
	 * @param actor 行动者
	 */
	@Override
	void store(String key, A actor);
	/**
	 * <p>
	 * 移除
	 * </p>
	 * @param <A> 行动者具体类型
	 * @param actor 行动者
	 */
	@Override
	void remove(A actor);
	/**
	 * <p>
	 * 移除
	 * </p>
	 * @param key 唯一标示
	 */
	@Override
	void remove(String key);
	/**
	 * <p>
	 * 获取指定行动者的登录信息.
	 * </p>
	 * @param key 唯一标示
	 * @return 指定行动者的登录信息
	 */
	@Override
	WebLoginInfo<A> getLoginInfo(String key);
	/**
	 * <p>
	 * 获取指定行动者的登录信息.
	 * </p>
	 * @param <A> 行动者具体类型
	 * @param actor 行动者
	 * @return 指定行动者的登录信息
	 */
	@Override
	WebLoginInfo<A> getLoginInfo(A actor);
	/**
	 * <p>
	 * 获取登录的行动者.
	 * </p>
	 * @param <A> 行动者具体类型
	 * @return 登录的行动者
	 */
	@Override
	List<A> getLoginActors();
	/**
	 * <p>
	 * 获取登录的行动者.
	 * </p>
	 * @return 登录的行动者
	 */
	@Override
	List<WebLoginInfo<A>> getLoginInfos();
}
