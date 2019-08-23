package cn.featherfly.permission.login;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.featherfly.permission.PermissionActor;

/**
 * <p>
 * 登陆信息
 * </p>
 * <p>
 * copyright featherfly 2010-2020, all rights reserved.
 * </p>
 * 
 * @param <A>
 *            actor类型
 * @author 钟冀
 */
public class LoginInfo<A extends PermissionActor> {
    /**
	 */
    public LoginInfo() {
    }

    private A actor;

    private Date loginTime;

    private Map<String, Object> attribute = new HashMap<String, Object>();

    /**
     * 返回actor
     * 
     * @return actor
     */
    public A getActor() {
        return (A) actor;
    }

    /**
     * 设置actor
     * 
     * @param actor
     *            actor
     */
    public void setActor(A actor) {
        this.actor = actor;
    }

    /**
     * 返回loginTime
     * 
     * @return loginTime
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置loginTime
     * 
     * @param loginTime
     *            loginTime
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    // ********************************************************************
    // method
    // ********************************************************************

    /**
     * 返回属性
     * 
     * @param name
     *            属性名称
     * @return attribute
     */
    public Object get(String name) {
        return attribute.get(name);
    }

    /**
     * 设置属性
     * 
     * @param name
     *            属性名称
     * @param value
     *            属性值
     */
    public void set(String name, Object value) {
        attribute.put(name, value);
    }
}
