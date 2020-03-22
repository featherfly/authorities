
package cn.featherfly.authorities;

import java.util.List;

/**
 * <p>
 * Role
 * </p>
 * <p>
 * 2019-08-22
 * </p>
 *
 * @author zhongj
 */
public interface Role {

    String getCode();

    String getName();

    String getDescp();

    List<Actor> getActors();

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
}
