
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

    Privilege create(E env);
}
