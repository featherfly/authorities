package cn.featherfly.permission.web.login;

import cn.featherfly.permission.PermissionActor;
import cn.featherfly.permission.login.LoginInfo;

/**
 * <p>
 * Web系统用户登录以后的信息绑定值对象
 * </p>
 * 
 * @param <A>
 *            行动者具体类型
 * @author 钟冀
 */
public class WebLoginInfo<A extends PermissionActor> extends LoginInfo<A> {

    /**
	 */
    public WebLoginInfo() {
    }

    // ********************************************************************
    // property
    // ********************************************************************

    private String session;

    private String ip;

    /**
     * 返回ip
     * 
     * @return ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置ip
     * 
     * @param ip
     *            ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 返回session
     * 
     * @return session
     */
    public String getSession() {
        return session;
    }

    /**
     * 设置session
     * 
     * @param session
     *            session
     */
    public void setSession(String session) {
        this.session = session;
    }
}
