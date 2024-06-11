package cn.featherfly.authorities;

/**
 * Authority.
 * 许可.
 *
 * @author zhongj
 */
public interface Authority {
    /**
     * 返回许可的唯一标识Code.
     *
     * @return code
     */
    String getCode();

    /**
     * 返回许可的名称.
     *
     * @return 许可名称
     */
    String getName();

    /**
     * 返回许可的描述.
     *
     * @return 许可描述
     */
    String getDescp();
}
