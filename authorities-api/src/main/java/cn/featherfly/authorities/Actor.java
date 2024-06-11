package cn.featherfly.authorities;

import java.util.List;

/**
 * actor.
 * 行动者.
 *
 * @author zhongj
 */
public interface Actor {

    /**
     * The Enum ActorType.
     *
     * @author zhongj
     */
    enum ActorType {
        /** The user. */
        USER,
        /** The system. */
        SYSTEM
    }

    /**
     * 返回字符串表示的唯一标示.
     *
     * @return 字符串表示的唯一标示
     */
    String getId();

    /**
     * 是否可用.
     *
     * @return available
     */
    boolean isAvailable();

    /**
     * 获取名称.
     *
     * @return 名称
     */
    String getName();

    /**
     * 获取描述信息.
     *
     * @return 描述信息
     */
    String getDescp();

    /**
     * Gets the type.
     *
     * @return the type
     */
    ActorType getType();

    /**
     * Checks for role.
     *
     * @param role the role
     * @return true, if successful
     */
    boolean hasRole(Role role);

    /**
     * Gets the roles.
     *
     * @return the roles
     */
    List<Role> getRoles();

    /**
     * 返回是否拥有指定的权限.
     *
     * @param authority 权限
     * @return 是否拥有指定的权限
     */
    boolean hasAuthority(Authority authority);

    /**
     * 返回行动者拥有的许可.
     *
     * @return 行动者拥有的许可
     */
    List<Authority> getOwnAuthoritys();

    /**
     * 返回行动者可以看到的许可.
     *
     * @return 行动者可以看到的许可
     */
    List<Authority> getReadbleAuthoritys();

    /**
     * 返回行动者可以对其他人授权的权限.
     *
     * @return 行动者可以对其他人授权的权限
     */
    List<Authority> getAuthorizableAuthoritys();
    // /**
    // * <p>
    // * 返回指定键对应的对象
    // * </p>
    // * @param key 键
    // * @return 指定键对应的对象
    // */
    // Object get(String key);
}
