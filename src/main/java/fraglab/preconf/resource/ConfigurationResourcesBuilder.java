package fraglab.preconf.resource;

import java.util.HashSet;
import java.util.Set;

/**
 * Builds a {@link ConfigurationResources} bean, to capture the configuration resources.
 */
public class ConfigurationResourcesBuilder {

    private Set<String> store = new HashSet<>();

    public ConfigurationResourcesBuilder() {
    }

    /**
     * Incrementally build a list of configuration resources.
     * @param filename the filename to append
     * @return the same instance
     */
    public ConfigurationResourcesBuilder addMandatory(String filename) {
        this.store.add(filename);
        return this;
    }

    Set<String> getMandatory() {
        return this.store;
    }

    /**
     * Builds the configuration resource set that should exist in the configuration directory.
     * @return the a resources wrapper
     */
    public ConfigurationResources build() {
        return new ConfigurationResources(this);
    }

}
