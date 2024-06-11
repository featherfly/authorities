
package cn.featherfly.authorities;

/**
 * Checker.
 *
 * @author zhongj
 */
public interface Checker<E> {
    /**
     * check
     *
     * @param env env
     * @return is available
     */
    boolean check(E env);
}
