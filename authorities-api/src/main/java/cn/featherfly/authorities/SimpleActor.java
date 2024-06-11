
package cn.featherfly.authorities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SimpleActor.
 *
 * @author zhongj
 */
public class SimpleActor implements Actor {

    private String id;

    private String name;

    private String descp;

    private boolean available;

    private ActorType type;

    private Map<String, Authority> ownAuthorityMap = new HashMap<>();

    private Map<String, Authority> readableAuthorityMap = new HashMap<>();

    private Map<String, Authority> authorizableAuthorityMap = new HashMap<>();

    private Map<String, Role> roleMap = new HashMap<>();

    /**
     * 返回id.
     *
     * @return id
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * 设置id.
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 返回name.
     *
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * 设置name.
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescp() {
        return descp;
    }

    /**
     * 设置descp.
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
    public ActorType getType() {
        return type;
    }

    /**
     * 设置type.
     *
     * @param type type
     */
    public void setType(ActorType type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAvailable() {
        return available;
    }

    /**
     * 设置available.
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

    /**
     * Adds the own authority.
     *
     * @param authority the authority
     * @return the simple actor
     */
    public SimpleActor addOwnAuthority(Authority authority) {
        ownAuthorityMap.put(authority.getCode(), authority);
        return this;
    }

    /**
     * Adds the own authority.
     *
     * @param authoritys the authoritys
     * @return the simple actor
     */
    public SimpleActor addOwnAuthority(Authority... authoritys) {
        for (Authority authority : authoritys) {
            ownAuthorityMap.put(authority.getCode(), authority);
        }
        return this;
    }

    /**
     * Adds the own authority.
     *
     * @param authoritys the authoritys
     * @return the simple actor
     */
    public SimpleActor addOwnAuthority(Collection<Authority> authoritys) {
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

    /**
     * Adds the readble authority.
     *
     * @param authority the authority
     * @return the simple actor
     */
    public SimpleActor addReadbleAuthority(Authority authority) {
        readableAuthorityMap.put(authority.getCode(), authority);
        return this;
    }

    /**
     * Adds the readble authority.
     *
     * @param authoritys the authoritys
     * @return the simple actor
     */
    public SimpleActor addReadbleAuthority(Authority... authoritys) {
        for (Authority authority : authoritys) {
            readableAuthorityMap.put(authority.getCode(), authority);
        }
        return this;
    }

    /**
     * Adds the readble authority.
     *
     * @param authoritys the authoritys
     * @return the simple actor
     */
    public SimpleActor addReadbleAuthority(Collection<Authority> authoritys) {
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

    /**
     * Adds the authorizable authority.
     *
     * @param authority the authority
     * @return the simple actor
     */
    public SimpleActor addAuthorizableAuthority(Authority authority) {
        authorizableAuthorityMap.put(authority.getCode(), authority);
        return this;
    }

    /**
     * Adds the authorizable authority.
     *
     * @param authoritys the authoritys
     * @return the simple actor
     */
    public SimpleActor addAuthorizableAuthority(Authority... authoritys) {
        for (Authority authority : authoritys) {
            authorizableAuthorityMap.put(authority.getCode(), authority);
        }
        return this;
    }

    /**
     * Adds the authorizable authority.
     *
     * @param authoritys the authoritys
     * @return the simple actor
     */
    public SimpleActor addAuthorizableAuthority(Collection<Authority> authoritys) {
        for (Authority authority : authoritys) {
            authorizableAuthorityMap.put(authority.getCode(), authority);
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

    /**
     * Adds the role.
     *
     * @param role the role
     * @return the simple actor
     */
    public SimpleActor addRole(Role role) {
        roleMap.put(role.getCode(), role);
        return this;
    }

    /**
     * Adds the role.
     *
     * @param roles the roles
     * @return the simple actor
     */
    public SimpleActor addRole(Role... roles) {
        for (Role role : roles) {
            roleMap.put(role.getCode(), role);
        }
        return this;
    }

    /**
     * Adds the role.
     *
     * @param roles the roles
     * @return the simple actor
     */
    public SimpleActor addRole(Collection<Role> roles) {
        for (Role role : roles) {
            roleMap.put(role.getCode(), role);
        }
        return this;
    }

}
