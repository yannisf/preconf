package fraglab.preconf.resource;

import fraglab.preconf.ConfigurationWrapper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class ResourceValidatorTest {

    @Test
    public void testResourceValidatorSuccess() throws IOException {
        String path = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "app";
        File tempDirectory = new File(path);
        File file1 = new File(tempDirectory.getAbsolutePath() + System.getProperty("file.separator") + "one");
        File file2 = new File(tempDirectory.getAbsolutePath() + System.getProperty("file.separator") + "two");

        Properties props = System.getProperties();
        props.setProperty("app.conf.dir", path);

        try {
            tempDirectory.mkdir();
            file1.createNewFile();
            file2.createNewFile();

            ConfigurationWrapper wrapper = ConfigurationWrapper.create("app", "one", "two");
        } finally {
            file1.delete();
            file2.delete();
            tempDirectory.delete();
            props.remove("app.conf.dir");
        }
    }

    @Test(expectedExceptions = ConfigurationResourceNotFoundException.class)
    public void testResourceValidatorFail() throws IOException {
        String path = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "app";
        File tempDirectory = new File(path);

        Properties props = System.getProperties();
        props.setProperty("app.conf.dir", path);

        try {
            tempDirectory.mkdir();
            ConfigurationWrapper wrapper = ConfigurationWrapper.create("app", "one");
        } finally {
            tempDirectory.delete();
            props.remove("app.conf.dir");
        }
    }

}
