
package cn.featherfly.authorities;

import java.util.List;

/**
 * RoleDefiner.
 *
 * @author zhongj
 * @param <R> the generic type
 */
public interface RoleDefiner<R extends Role> {

    /**
     * Support.
     *
     * @param roleType the role type
     * @return true, if successful
     */
    boolean support(Class<R> roleType);

    /**
     * Gets the roles.
     *
     * @return the roles
     */
    List<R> getRoles();
}
