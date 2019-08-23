
package cn.featherfly.permission;

import java.util.List;

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

    boolean support(Class<R> roleType);

    List<R> getRoles();
}
