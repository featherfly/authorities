
package cn.featherfly.permission.login;

import java.util.List;

import cn.featherfly.permission.core.PermissionActor;

/**
 * <p>
 * 应用认证（登录）用户管理程序
 * </p>
 *
 * @param <E> 泛型，用于具体依赖环境的定义
 * @param <I> 登录信息
 * @author 钟冀
 */
public interface ApplicationLoginManager<E, I extends LoginInfo<A>, A extends PermissionActor> {
	/**
	 * <p>
	 * 登录.
	 * 不成功会抛出AuthenticationException
	 * </p>
	 * @param <A> 行动者具体类型
	 * @param actor 行动者
	 * @param env 依赖环境
	 */
	void login(A actor, E env);
	/**
	 * <p>
	 * 是否登录.
	 * </p>
	 * @param env 依赖环境
	 * @return 是否登录.
	 */
	boolean isLogin(E env);
	/**
	 * <p>
	 * 是否登录.
	 * </p>
	 * @param <A> 行动者具体类型
	 * @param actor 行动者
	 * @return 是否登录.
	 */
	boolean isLogin(A actor);
	/**
	 * <p>
	 * 注销
	 * </p>
	 * @param env 依赖环境
	 */
	void logout(E env);
	/**
	 * <p>
	 * 注销
	 * </p>
	 * @param <A> 行动者具体类型
	 * @param actor 行动者
	 */
	void logout(A actor);
	/**
	 * <p>
	 * 获取登录的行动者.
	 * </p>
	 * @param <A> 行动者具体类型
	 * @return 登录的行动者
	 */
	List<A> getLoginActors();
	/**
	 * <p>
	 * 获取指定行动者的登录信息.
	 * </p>
	 * @param env 依赖环境
	 * @return 指定行动者的登录信息
	 */
	I getLoginInfo(E env);
	/**
	 * <p>
	 * 获取指定行动者的登录信息.
	 * </p>
	 * @param <A> 行动者具体类型
	 * @param actor 行动者
	 * @return 指定行动者的登录信息
	 */
	I getLoginInfo(A actor);
	/**
	 * <p>
	 * 添加登录监听器
	 * </p>
	 * @param loginListener loginListener
	 */
	void addLoginListener(LoginListener<I, A> loginListener);
}
