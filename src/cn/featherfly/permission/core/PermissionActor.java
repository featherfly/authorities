
package cn.featherfly.permission.core;

import java.util.List;




/**
 * <p>
 * 行动者
 * </p>
 *
 * @author 钟冀
 */
public interface PermissionActor {
    /**
     * <p>
     * 返回字符串表示的唯一标示
     * </p>
     * @return 字符串表示的唯一标示
     */
    String getId();
    /**
     * <p>
     * 获取名称
     * </p>
     * @return 名称
     */
    String getName();
    /**
     * <p>
     * 获取描述信息
     * </p>
     * @return 描述信息
     */
    String getDescp();
	/**
	 * <p>
	 * 返回是否拥有指定的权限.
	 * </p>
	 * @param privilege 权限
	 * @return 是否拥有指定的权限
	 */
	boolean hasPrivilege(Privilege privilege);
	/**
	 * <p>
	 * 返回行动者拥有的许可
	 * </p>
	 * @return 行动者拥有的许可
	 */
	List<Privilege> getOwnPrivileges();
	/**
	 * <p>
	 * 返回行动者可以看到的许可
	 * </p>
	 * @return 行动者可以看到的许可
	 */
	List<Privilege> getReadblePrivileges();
//	/**
//	 * <p>
//	 * 返回指定键对应的对象
//	 * </p>
//	 * @param key 键
//	 * @return 指定键对应的对象
//	 */
//	Object get(String key);
}
