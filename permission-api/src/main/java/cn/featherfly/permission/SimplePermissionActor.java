
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
public class SimplePermissionActor implements PermissionActor {

    private String id;

    private String name;

    private String descp;

    private boolean available;

    private ActorType type;

    private Map<String, Privilege> ownPrivilegeMap = new HashMap<>();

    private Map<String, Privilege> readablePrivilegeMap = new HashMap<>();

    private Map<String, Privilege> authorizablePrivilegeMap = new HashMap<>();

    private Map<String, Role> roleMap = new HashMap<>();

    /**
     * 返回id
     *
     * @return id
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
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
     * 返回type
     *
     * @return type
     */
    @Override
    public ActorType getType() {
        return type;
    }

    /**
     * 设置type
     *
     * @param type type
     */
    public void setType(ActorType type) {
        this.type = type;
    }

    /**
     * 返回available
     *
     * @return available
     */
    @Override
    public boolean isAvailable() {
        return available;
    }

    /**
     * 设置available
     *
     * @param available available
     */
    public void setAvailable(boolean available) {
        this.available = available;
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

    public SimplePermissionActor addOwnPrivilege(Privilege privilege) {
        ownPrivilegeMap.put(privilege.getCode(), privilege);
        return this;
    }

    public SimplePermissionActor addOwnPrivilege(Privilege... privileges) {
        for (Privilege privilege : privileges) {
            ownPrivilegeMap.put(privilege.getCode(), privilege);
        }
        return this;
    }

    public SimplePermissionActor addOwnPrivilege(Collection<Privilege> privileges) {
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

    public SimplePermissionActor addReadblePrivilege(Privilege privilege) {
        readablePrivilegeMap.put(privilege.getCode(), privilege);
        return this;
    }

    public SimplePermissionActor addReadblePrivilege(Privilege... privileges) {
        for (Privilege privilege : privileges) {
            readablePrivilegeMap.put(privilege.getCode(), privilege);
        }
        return this;
    }

    public SimplePermissionActor addReadblePrivilege(Collection<Privilege> privileges) {
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

    public SimplePermissionActor addAuthorizablePrivilege(Privilege privilege) {
        authorizablePrivilegeMap.put(privilege.getCode(), privilege);
        return this;
    }

    public SimplePermissionActor addAuthorizablePrivilege(Privilege... privileges) {
        for (Privilege privilege : privileges) {
            authorizablePrivilegeMap.put(privilege.getCode(), privilege);
        }
        return this;
    }

    public SimplePermissionActor addAuthorizablePrivilege(Collection<Privilege> privileges) {
        for (Privilege privilege : privileges) {
            authorizablePrivilegeMap.put(privilege.getCode(), privilege);
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasRole(Role role) {
        return roleMap.containsKey(role.getCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Role> getRoles() {
        return new ArrayList<>(roleMap.values());
    }

    public SimplePermissionActor addRole(Role role) {
        roleMap.put(role.getCode(), role);
        return this;
    }

    public SimplePermissionActor addRole(Role... roles) {
        for (Role role : roles) {
            roleMap.put(role.getCode(), role);
        }
        return this;
    }

    public SimplePermissionActor addRole(Collection<Role> roles) {
        for (Role role : roles) {
            roleMap.put(role.getCode(), role);
        }
        return this;
    }

}
