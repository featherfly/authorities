
package cn.featherfly.permission.web.login;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.featherfly.permission.core.PermissionActor;

/**
 * <p>
 * web环境简单实现，不支持集群
 * </p>
 *
 *
 * @author 钟冀
 */
public class WebActorLoginStorageSimpleImpl<A extends PermissionActor> implements WebActorLoginStorage<A> {

	private Map<String, WebLoginInfo<A>> webLoginInfos = new HashMap<String, WebLoginInfo<A>>();

	/**
	 */
	public WebActorLoginStorageSimpleImpl() {
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void store(String key, A actor) {
		WebLoginInfo<A> webLoginInfo = new WebLoginInfo<A>();
		webLoginInfo.setActor(actor);
		webLoginInfo.setLoginTime(new Date());
		webLoginInfo.setSession(key);
		webLoginInfos.put(key, webLoginInfo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remove(A actor) {
		if (actor != null) {
			webLoginInfos.remove(
					getLoginInfo(actor).getSession());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remove(String key) {
		webLoginInfos.remove(key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WebLoginInfo<A> getLoginInfo(String key) {
		return webLoginInfos.get(key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WebLoginInfo<A> getLoginInfo(A actor) {
		if (actor != null) {
			for (WebLoginInfo<A> webLoginInfo : webLoginInfos.values()) {
				if (webLoginInfo.getActor().getId().equals(actor.getId())) {
					return webLoginInfo;
				}
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<A> getLoginActors() {
		ArrayList<A> actors = new ArrayList<A>();
		for (WebLoginInfo<A> loginActor : webLoginInfos.values()) {
			actors.add(loginActor.getActor());
		}
		return actors;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<WebLoginInfo<A>> getLoginInfos() {
		return new ArrayList<WebLoginInfo<A>>(webLoginInfos.values());
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsKey(String key) {
		return webLoginInfos.containsKey(key);
	}

}
