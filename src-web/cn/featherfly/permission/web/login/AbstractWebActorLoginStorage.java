
package cn.featherfly.permission.web.login;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.featherfly.permission.core.PermissionActor;

/**
 * <p>
 * web环境简单实现抽象，不支持集群
 * </p>
 *
 *
 * @author 钟冀
 */
public abstract class AbstractWebActorLoginStorage<W extends WebLoginInfo> implements WebActorLoginStorage<W> {

	private Map<String, W> webLoginInfos = new HashMap<String, W>();

	/**
	 */
	public AbstractWebActorLoginStorage() {
	}
	
	
	protected abstract W createLoginInfo();


	/**
	 * {@inheritDoc}
	 */
	@Override
	public <A extends PermissionActor> void store(String key, A actor) {
		W webLoginInfo = createLoginInfo();
		webLoginInfo.setActor(actor);
		webLoginInfo.setLoginTime(new Date());
		webLoginInfo.setSession(key);
		webLoginInfos.put(key, webLoginInfo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <A extends PermissionActor> void remove(A actor) {
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
	public W getLoginInfo(String key) {
		return webLoginInfos.get(key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <A extends PermissionActor> W getLoginInfo(A actor) {
		if (actor != null) {
			for (W webLoginInfo : webLoginInfos.values()) {
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
	public <A extends PermissionActor> List<A> getLoginActors() {
		ArrayList<A> actors = new ArrayList<A>();
		for (W loginActor : webLoginInfos.values()) {
		    A a = loginActor.getActor();
			actors.add(a);
		}
		return actors;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<W> getLoginInfos() {
		return new ArrayList<W>(webLoginInfos.values());
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsKey(String key) {
		return webLoginInfos.containsKey(key);
	}

}
