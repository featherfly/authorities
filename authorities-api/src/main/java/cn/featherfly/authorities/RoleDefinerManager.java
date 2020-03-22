
package cn.featherfly.authorities;

import java.util.List;

/**
 * <p>
 * RoleDefinerManager
 * </p>
 *
 * @author zhongj
 */
public interface RoleDefinerManager {

    List<RoleDefiner<Role>> getRoleDefiners();

    List<RoleDefiner<BusinessRole>> getBusinessRoleDefiners();

    List<RoleDefiner<CustomRole>> getCustomRoleDefiners();
}
