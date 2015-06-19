
package cn.featherfly.permission.login;



/**
 * <p>
 * LoginEvent
 * </p>
 *
 * @param <I> 具体的LoginInfo类型
 * @author 钟冀
 */
public class LoginEvent<I extends LoginInfo> {

	/**
	 */
	public LoginEvent() {
	}

	private I loginInfo;

	/**
	 * 返回loginInfo
	 * @return loginInfo
	 */
	public I getLoginInfo() {
		return loginInfo;
	}

	/**
	 * 设置loginInfo
	 * @param loginInfo loginInfo
	 */
	public void setLoginInfo(I loginInfo) {
		this.loginInfo = loginInfo;
	}
}
