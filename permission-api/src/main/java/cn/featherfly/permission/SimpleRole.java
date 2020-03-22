
package cn.featherfly.permission;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * SimpleRole
 * </p>
 *
 * @author zhongj
 */
public class SimpleRole implements Role {

    private String code;

    private String name;

    private String descp;

    private Map<String, Authority> ownAuthorityMap = new HashMap<>();

    private Map<String, Authority> readableAuthorityMap = new HashMap<>();

    private Map<String, Authority> authorizableAuthorityMap = new HashMap<>();

    private Map<String, Actor> actorMap = new HashMap<>();

    /**
     * 返回code
     *
     * @return code
     */
    @Override
    public String getCode() {
        return code;
    }

    /**
     * 设置code
     *
     * @param code code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 返回name
     *
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * 设置name
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 返回descp
     *
     * @return descp
     */
    @Override
    public String getDescp() {
        return descp;
    }

    /**
     * 设置descp
     *
     * @param descp descp
     */
    public void setDescp(String descp) {
        this.descp = descp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasAuthority(Authority authority) {
        return ownAuthorityMap.containsKey(authority.getCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Authority> getOwnAuthoritys() {
        return new ArrayList<>(ownAuthorityMap.values());
    }

    public SimpleRole addOwnAuthority(Authority authority) {
        ownAuthorityMap.put(authority.getCode(), authority);
        return this;
    }

    public SimpleRole addOwnAuthority(Authority... authoritys) {
        for (Authority authority : authoritys) {
            ownAuthorityMap.put(authority.getCode(), authority);
        }
        return this;
    }

    public SimpleRole addOwnAuthority(Collection<Authority> authoritys) {
        for (Authority authority : authoritys) {
            ownAuthorityMap.put(authority.getCode(), authority);
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Authority> getReadbleAuthoritys() {
        return new ArrayList<>(readableAuthorityMap.values());
    }

    public SimpleRole addReadbleAuthority(Authority authority) {
        readableAuthorityMap.put(authority.getCode(), authority);
        return this;
    }

    public SimpleRole addReadbleAuthority(Authority... authoritys) {
        for (Authority authority : authoritys) {
            readableAuthorityMap.put(authority.getCode(), authority);
        }
        return this;
    }

    public SimpleRole addReadbleAuthority(Collection<Authority> authoritys) {
        for (Authority authority : authoritys) {
            readableAuthorityMap.put(authority.getCode(), authority);
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Authority> getAuthorizableAuthoritys() {
        return new ArrayList<>(authorizableAuthorityMap.values());
    }

    public SimpleRole addAuthorizableAuthority(Authority authority) {
        authorizableAuthorityMap.put(authority.getCode(), authority);
        return this;
    }

    public SimpleRole addAuthorizableAuthority(Authority... authoritys) {
        for (Authority authority : authoritys) {
            authorizableAuthorityMap.put(authority.getCode(), authority);
        }
        return this;
    }

    public SimpleRole addAuthorizableAuthority(Collection<Authority> authoritys) {
        for (Authority authority : authoritys) {
            authorizableAuthorityMap.put(authority.getCode(), authority);
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Actor> getActors() {
        return new ArrayList<>(actorMap.values());
    }

    public SimpleRole addActor(Actor actor) {
        actorMap.put(actor.getId(), actor);
        return this;
    }

    public SimpleRole addActor(Actor... actors) {
        for (Actor actor : actors) {
            actorMap.put(actor.getId(), actor);
        }
        return this;
    }

    public SimpleRole addActor(Collection<Actor> actors) {
        for (Actor actor : actors) {
            actorMap.put(actor.getId(), actor);
        }
        return this;
    }

}
