
package cn.featherfly.permission;

/**
 * <p>
 * PrivilegeFacotry
 * </p>
 * <p>
 * 2019-08-21
 * </p>
 *
 * @author zhongj
 */
public interface PrivilegeFacotry<E> {

    /**
     * create privilete.
     *
     * @param env env
     * @return Privilege or null if the env is not a privilege
     */
    Privilege create(E env);
}
