package fraglab.preconf.web;

import fraglab.preconf.ConfigurationWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web listener facility to resolve configuration directory and ascertain the existence of mandatory configuration resources.
 * After the proper initialization, the in the servlet context with {@link #CONF_DIR_CONTEXT_PARAM} the configuration directory
 * is stored.
 */
public class PreConfigurationServletListener implements ServletContextListener {

    /**
     * The context init param where the application prefix is expected.
     */
    public static final String APP_PREFIX_CONTEXT_PARAM = "appPrefix";
    /**
     * The context init param where the mandatory configuration resources are expected. The resources should be a string,
     * with comma separated filenames.
     */
    public static final String PRE_CONF_MANDATORY_CONTEXT_PARAM = "preconf.mandatory";
    /**
     * The property in the servlet context where the configuration directory path will be stored.
     */
    public static final String CONF_DIR_CONTEXT_PARAM = "conf.dir";
    private static final Logger LOG = LoggerFactory.getLogger(PreConfigurationServletListener.class);

    /**
     * Resolves the configuration directory with input from web.xml context params.
     *
     * @param sce the context
     * @see #APP_PREFIX_CONTEXT_PARAM
     * @see #PRE_CONF_MANDATORY_CONTEXT_PARAM
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.info("Initializing pre configuration listener");
        String appPrefix = sce.getServletContext().getInitParameter(APP_PREFIX_CONTEXT_PARAM);
        String mandatoryParameterValue = sce.getServletContext().getInitParameter(PRE_CONF_MANDATORY_CONTEXT_PARAM);
        String[] mandatory = mandatoryParameterValue.split(",");
        ConfigurationWrapper wrapper = getWrapper(appPrefix, mandatory);
        sce.getServletContext().setAttribute(CONF_DIR_CONTEXT_PARAM, wrapper.getConfigurationDirectory().getAbsolutePath());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //Nothing to destroy
    }

    private ConfigurationWrapper getWrapper(String appPrefix, String[] mandatory) {
        return ConfigurationWrapper.create(appPrefix, mandatory);
    }

}
