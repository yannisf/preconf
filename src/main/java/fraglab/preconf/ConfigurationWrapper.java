package fraglab.preconf;

import fraglab.preconf.resolver.DirectoryResolver;
import fraglab.preconf.resource.ConfigurationResourcesBuilder;
import fraglab.preconf.resource.ResourceValidator;

import java.io.File;
import java.util.Arrays;

/**
 * Wrapper class to facilitate resolution of configuration directory and validation of configuration resources
 */
public class ConfigurationWrapper {

    private File configurationDirectory;

    private ConfigurationWrapper() {
    }

    /**
     * Factory for a configuration wrapper.
     * @param appPrefix the application prefix
     * @param mandatory an array of mandatory file resources
     * @return the wrapper instance
     */
    public static ConfigurationWrapper create(final String appPrefix, String...mandatory) {
        DirectoryResolver resolver = new DirectoryResolver(appPrefix);
        File configurationDirectory = resolver.resolve();

        ConfigurationResourcesBuilder builder = new ConfigurationResourcesBuilder();
        Arrays.stream(mandatory).forEach(builder::addMandatory);

        ResourceValidator validator = new ResourceValidator();
        validator.validate(configurationDirectory, builder.build());

        ConfigurationWrapper wrapper = new ConfigurationWrapper();
        wrapper.configurationDirectory = configurationDirectory;

        return wrapper;
    }

    /**
     * Gets the resolved configuration directory
     * @return the configuration directory
     */
    public File getConfigurationDirectory() {
        return this.configurationDirectory;
    }

}
