package cn.featherfly.authorities.web.login.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;

import cn.featherfly.authorities.web.login.WebLoginManager;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * WebActorLoginListener.
 *
 * @author zhongj
 */
public class WebActorLoginListener implements HttpSessionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebActorLoginListener.class);

    /**
     */
    public WebActorLoginListener() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sessionCreated(HttpSessionEvent event) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        WebApplicationContext context = (WebApplicationContext) event.getSession().getServletContext()
            .getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        context.getBean(WebLoginManager.class).logout(event.getSession());
        LOGGER.debug("session销毁");
    }
}
