
package cn.featherfly.permission.web.login;


/**
 * <p>
 * web环境简单实现，不支持集群
 * </p>
 *
 *
 * @author 钟冀
 */
public class WebActorLoginStorageSimpleImpl extends AbstractWebActorLoginStorage<WebLoginInfo>{

	/**
	 */
	public WebActorLoginStorageSimpleImpl() {
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected WebLoginInfo createLoginInfo() {
	    return new WebLoginInfo();
	}
}
