
package cn.featherfly.permission;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * SimplePrivilege
 * </p>
 * <p>
 * 2019-08-21
 * </p>
 *
 * @author zhongj
 */
public class SimpleRole implements Role {

    private String code;

    private String name;

    private String descp;

    private Map<String, Privilege> ownPrivilegeMap = new HashMap<>();

    private Map<String, Privilege> readablePrivilegeMap = new HashMap<>();

    private Map<String, Privilege> authorizablePrivilegeMap = new HashMap<>();

    private Map<String, PermissionActor> actorMap = new HashMap<>();

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
    public boolean hasPrivilege(Privilege privilege) {
        return ownPrivilegeMap.containsKey(privilege.getCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Privilege> getOwnPrivileges() {
        return new ArrayList<>(ownPrivilegeMap.values());
    }

    public SimpleRole addOwnPrivilege(Privilege privilege) {
        ownPrivilegeMap.put(privilege.getCode(), privilege);
        return this;
    }

    public SimpleRole addOwnPrivilege(Privilege... privileges) {
        for (Privilege privilege : privileges) {
            ownPrivilegeMap.put(privilege.getCode(), privilege);
        }
        return this;
    }

    public SimpleRole addOwnPrivilege(Collection<Privilege> privileges) {
        for (Privilege privilege : privileges) {
            ownPrivilegeMap.put(privilege.getCode(), privilege);
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Privilege> getReadblePrivileges() {
        return new ArrayList<>(readablePrivilegeMap.values());
    }

    public SimpleRole addReadblePrivilege(Privilege privilege) {
        readablePrivilegeMap.put(privilege.getCode(), privilege);
        return this;
    }

    public SimpleRole addReadblePrivilege(Privilege... privileges) {
        for (Privilege privilege : privileges) {
            readablePrivilegeMap.put(privilege.getCode(), privilege);
        }
        return this;
    }

    public SimpleRole addReadblePrivilege(Collection<Privilege> privileges) {
        for (Privilege privilege : privileges) {
            readablePrivilegeMap.put(privilege.getCode(), privilege);
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Privilege> getAuthorizablePrivileges() {
        return new ArrayList<>(authorizablePrivilegeMap.values());
    }

    public SimpleRole addAuthorizablePrivilege(Privilege privilege) {
        authorizablePrivilegeMap.put(privilege.getCode(), privilege);
        return this;
    }

    public SimpleRole addAuthorizablePrivilege(Privilege... privileges) {
        for (Privilege privilege : privileges) {
            authorizablePrivilegeMap.put(privilege.getCode(), privilege);
        }
        return this;
    }

    public SimpleRole addAuthorizablePrivilege(Collection<Privilege> privileges) {
        for (Privilege privilege : privileges) {
            authorizablePrivilegeMap.put(privilege.getCode(), privilege);
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PermissionActor> getActors() {
        return new ArrayList<>(actorMap.values());
    }

    public SimpleRole addActor(PermissionActor actor) {
        actorMap.put(actor.getId(), actor);
        return this;
    }

    public SimpleRole addActor(PermissionActor... actors) {
        for (PermissionActor actor : actors) {
            actorMap.put(actor.getId(), actor);
        }
        return this;
    }

    public SimpleRole addActor(Collection<PermissionActor> actors) {
        for (PermissionActor actor : actors) {
            actorMap.put(actor.getId(), actor);
        }
        return this;
    }

}
