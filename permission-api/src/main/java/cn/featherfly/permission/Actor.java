package cn.featherfly.permission;

import java.util.List;

/**
 * <p>
 * 行动者
 * </p>
 *
 * @author 钟冀
 */
public interface Actor {

    enum ActorType {
        USER,
        SYSTEM
    }

    /**
     * <p>
     * 返回字符串表示的唯一标示
     * </p>
     *
     * @return 字符串表示的唯一标示
     */
    String getId();

    /**
     * 是否可用
     *
     * @return available
     */
    boolean isAvailable();

    /**
     * <p>
     * 获取名称
     * </p>
     *
     * @return 名称
     */
    String getName();

    /**
     * <p>
     * 获取描述信息
     * </p>
     *
     * @return 描述信息
     */
    String getDescp();

    ActorType getType();

    boolean hasRole(Role role);

    List<Role> getRoles();

    /**
     * <p>
     * 返回是否拥有指定的权限.
     * </p>
     *
     * @param authority 权限
     * @return 是否拥有指定的权限
     */
    boolean hasAuthority(Authority authority);

    /**
     * <p>
     * 返回行动者拥有的许可
     * </p>
     *
     * @return 行动者拥有的许可
     */
    List<Authority> getOwnAuthoritys();

    /**
     * <p>
     * 返回行动者可以看到的许可
     * </p>
     *
     * @return 行动者可以看到的许可
     */
    List<Authority> getReadbleAuthoritys();

    /**
     * <p>
     * 返回行动者可以对其他人授权的权限
     * </p>
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
