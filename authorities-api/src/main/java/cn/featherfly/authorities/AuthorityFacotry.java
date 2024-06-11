
package cn.featherfly.authorities;

/**
 * AuthorityFacotry.
 *
 * @author zhongj
 */
public interface AuthorityFacotry<E> {

    /**
     * create Authority.
     *
     * @param env env
     * @return authority instance or null if the env is not a authority
     */
    Authority create(E env);
}
