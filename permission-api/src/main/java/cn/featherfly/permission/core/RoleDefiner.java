
package cn.featherfly.permission.core;

/**
 * <p>
 * RoleDefiner
 * </p>
 * <p>
 * 2019-08-22
 * </p>
 *
 * @author zhongj
 */
public interface RoleDefiner<R extends Role> {

    R getRole();
}
