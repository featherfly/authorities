
package cn.featherfly.permission.core;



/**
 * <p>
 * 许可
 * </p>
 *
 * @author 钟冀
 */
public interface Privilege {
	/**
	 * <p>
	 * 返回许可的ID
	 * </p>
	 * @return ID
	 */
	String getId();
	/**
	 * <p>
	 * 返回许可的名称
	 * </p>
	 * @return 许可名称
	 */
	String getName();
}
