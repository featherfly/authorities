
package cn.featherfly.authorities;

import java.util.List;

/**
 * RoleDefinerManager.
 *
 * @author zhongj
 */
public interface RoleDefinerManager {

    /**
     * Gets the role definers.
     *
     * @return the role definers
     */
    List<RoleDefiner<Role>> getRoleDefiners();

    /**
     * Gets the business role definers.
     *
     * @return the business role definers
     */
    List<RoleDefiner<BusinessRole>> getBusinessRoleDefiners();

    /**
     * Gets the custom role definers.
     *
     * @return the custom role definers
     */
    List<RoleDefiner<CustomRole>> getCustomRoleDefiners();
}
