
package cn.featherfly.permission;

/**
 * <p>
 * SimplePrivilege
 * </p>
 * <p>
 * 2019-08-21
 * </p>
 *
 * @author zhongj
 */
public class SimplePrivilege implements Privilege {

    private String code;

    private String name;

    private String descp;

    /**
     * 返回code
     *
     * @return code
     */
    @Override
    public String getCode() {
        return code;
    }

    /**
     * 设置code
     *
     * @param code code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 返回name
     *
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * 设置name
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 返回descp
     *
     * @return descp
     */
    @Override
    public String getDescp() {
        return descp;
    }

    /**
     * 设置descp
     *
     * @param descp descp
     */
    public void setDescp(String descp) {
        this.descp = descp;
    }

}
