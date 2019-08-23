package cn.featherfly.permission;

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
     * 返回许可的唯一标识Code
     * </p>
     *
     * @return code
     */
    String getCode();

    /**
     * <p>
     * 返回许可的名称
     * </p>
     *
     * @return 许可名称
     */
    String getName();

    /**
     * <p>
     * 返回许可的描述
     * </p>
     *
     * @return 许可描述
     */
    String getDescp();
}
