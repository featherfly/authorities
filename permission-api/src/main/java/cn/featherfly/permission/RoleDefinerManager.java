
package cn.featherfly.permission;

import java.util.List;

/**
 * <p>
 * RoleDefiner
 * </p>
 *
 * @author zhongj
 */
public interface RoleDefinerManager {

    List<RoleDefiner<Role>> getRoleDefiners();

    List<RoleDefiner<BusinessRole>> getBusinessRoleDefiners();

    List<RoleDefiner<CustomRole>> getCustomRoleDefiners();
}
