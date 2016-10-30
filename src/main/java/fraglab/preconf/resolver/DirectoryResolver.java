package fraglab.preconf.resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Resolves the configuration directory
 */
public class DirectoryResolver {

    private static final Logger LOG = LoggerFactory.getLogger(DirectoryResolver.class);

    /**
     * The system property suffixed after the application prefix
     */
    public static final String SYSTEM_PROPERTY = "conf.dir";

    /**
     * The environment property suffixes after the uppercased application prefix
     */
    public static final String ENVIRONMENT_PROPERTY = "CONF_DIR";

    /**
     * The directory that should include a directory with the application prefix
     */
    public static final String DEFAULT_DIRECTORY = "/etc";

    private final String appPrefix;

    /**
     * Initialized a resolver with a specific application prefix
     * @param appPrefix the application prefix
     */
    public DirectoryResolver(final String appPrefix) {
        this.appPrefix = appPrefix;
    }

    /**
     * Resolves the configuration directory. The algorithm used goes as follow:
     * <ul>
     *     <li>If a system property with value {appPrefix}.conf.dir exists, then the application is system property configured</li>
     *     <li>If an environment property with value {appPrefix|uppercase}_CONF_DIR exists, then the application is environment property configured</li>
     *     <li>If none of the above match, then the default configuration directory in /etc/{appPrefix} will be tried</li>
     * </ul>
     * @return
     */
    public File resolve() {
        LOG.debug("Starting configuration directory resolution for application prefix {}", appPrefix);
        File configurationDirectory;

        if (isSystemProperty()) {
            LOG.debug("Found system property [{}]", compileSystemProperty());
            configurationDirectory = new File(System.getProperty(compileSystemProperty()));
        } else if (isEnvironmentProperty()) {
            LOG.debug("Found environment property [{}]", compileEnvironmentProperty());
            configurationDirectory = new File(System.getenv(compileEnvironmentProperty()));
        } else {
            LOG.debug("Falling back to default configuration directory [{}]", compileDefaultDirectory());
            configurationDirectory = new File(compileDefaultDirectory());
        }

        LOG.info("Resolved configuration directory: [{}]", configurationDirectory.getAbsolutePath());

        if (!configurationDirectory.exists() || !configurationDirectory.isDirectory()) {
            LOG.error("Resolved configuration directory not found");
            throw new DirectoryNotFoundException();
        }

        return configurationDirectory;
    }

    private boolean isSystemProperty() {
        return System.getProperty(compileSystemProperty()) != null;
    }

    private boolean isEnvironmentProperty() {
        return System.getenv(compileEnvironmentProperty()) != null;
    }

    private String compileSystemProperty() {
        return appPrefix + "." + SYSTEM_PROPERTY;
    }

    private String compileEnvironmentProperty() {
        return appPrefix.toUpperCase() + "_" + ENVIRONMENT_PROPERTY;
    }

    private String compileDefaultDirectory() {
        return DEFAULT_DIRECTORY + "/" + appPrefix;
    }

}
