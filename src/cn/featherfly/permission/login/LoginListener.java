
package cn.featherfly.permission.login;



/**
 * <p>
 * LoginListener
 * </p>
 *
 * @param <I> 具体的LoginInfo类型
 * @author 钟冀
 */
public interface LoginListener<I extends LoginInfo> {
	/**
	 * <p>
	 * 登录成功以后执行
	 * </p>
	 * @param loginEvent loginEvent
	 */
    void onLogin(LoginEvent<I> loginEvent);
	/**
	 * <p>
	 * 注销成功以后执行
	 * </p>
	 * @param loginEvent loginEvent
	 */
    void onLogout(LoginEvent<I> loginEvent);
}
