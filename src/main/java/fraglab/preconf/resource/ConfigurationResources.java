package fraglab.preconf.resource;

import java.util.Collections;
import java.util.Set;

/**
 * Wrapper class for the configuration resources (configuration files).
 */
public class ConfigurationResources {

    private final Set<String> mandatory;

    ConfigurationResources(final ConfigurationResourcesBuilder builder) {
        this.mandatory = Collections.unmodifiableSet(builder.getMandatory());
    }

    /**
     * Gets the configuration files.
     * @return a set of filenames
     */
    public Set<String> getMandatory() {
        return mandatory;
    }

}
