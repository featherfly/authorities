
package cn.featherfly.authorities;

import java.util.List;

/**
 * <p>
 * RoleDefiner
 * </p>
 *
 * @author zhongj
 */
public interface RoleDefiner<R extends Role> {

    boolean support(Class<R> roleType);

    List<R> getRoles();
}
