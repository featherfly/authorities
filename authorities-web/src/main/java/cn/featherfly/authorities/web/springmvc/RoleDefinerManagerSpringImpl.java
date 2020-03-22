package cn.featherfly.authorities.web.springmvc;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import cn.featherfly.authorities.BusinessRole;
import cn.featherfly.authorities.CustomRole;
import cn.featherfly.authorities.Role;
import cn.featherfly.authorities.RoleDefiner;
import cn.featherfly.authorities.RoleDefinerManager;

/**
 * <p>
 * RoleDefinerFactory
 * </p>
 * <p>
 * 2019-08-22
 * </p>
 *
 * @author zhongj
 */
public class RoleDefinerManagerSpringImpl implements RoleDefinerManager, ApplicationContextAware {

    @SuppressWarnings("rawtypes")
    private Map<String, RoleDefiner> roleDefinerMap;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        roleDefinerMap = applicationContext.getBeansOfType(RoleDefiner.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<RoleDefiner<Role>> getRoleDefiners() {
        return roleDefinerMap.values().stream().map(r -> (RoleDefiner<Role>) r).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<RoleDefiner<BusinessRole>> getBusinessRoleDefiners() {
        return roleDefinerMap.values().stream().filter(r -> r.support(BusinessRole.class))
                .map(r -> (RoleDefiner<BusinessRole>) r).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<RoleDefiner<CustomRole>> getCustomRoleDefiners() {
        return roleDefinerMap.values().stream().filter(r -> r.support(CustomRole.class))
                .map(r -> (RoleDefiner<CustomRole>) r).collect(Collectors.toList());
    }

}
