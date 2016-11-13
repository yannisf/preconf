package fraglab.preconf.resource;

import java.io.File;

/**
 * Validates that the configuration directory, at minimum included the provided resources.
 */
public class ResourceValidator {

    private static final String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * Validates that all required configuration resources (files) exist in the configured directory. If not
     * {@link ConfigurationResourceNotFoundException} is thrown.
     *
     * @param directory the configuration directory
     * @param resources the mandatory configuration resources
     */
    public void validate(File directory, ConfigurationResources resources) {
        resources.getMandatory().forEach(m -> checkFileExists(directory, m));
    }

    private void checkFileExists(File directory, String m) {
        File file = new File(directory.getAbsolutePath() + FILE_SEPARATOR + m);
        if (!file.exists()) {
            throw new ConfigurationResourceNotFoundException(file.getAbsolutePath() + " not found");
        }
    }

}
