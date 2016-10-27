package fraglab.preconf.resource;

/**
 * Thrown when a designated configuration resource (file) is not found in the configuration directory.
 */
public class ConfigurationResourceNotFoundException extends RuntimeException {

    public ConfigurationResourceNotFoundException(String message) {
        super(message);
    }
}
