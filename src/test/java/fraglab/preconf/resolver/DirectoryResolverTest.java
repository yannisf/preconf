package fraglab.preconf.resolver;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Properties;

public class DirectoryResolverTest {

    @Test
    public void testSystemProperty() {
        String path = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "app";
        File tempDirectory = new File(path);
        tempDirectory.mkdir();
        Properties props = System.getProperties();
        props.setProperty("app.conf.dir", path);
        try {
            DirectoryResolver resolver = new DirectoryResolver("app");
            File file = resolver.resolve();
            Assert.assertEquals(file.getAbsolutePath(), tempDirectory.getAbsolutePath());
        } finally {
            tempDirectory.delete();
            props.remove("app.conf.dir");
        }
    }

    @Test(expectedExceptions = DirectoryNotFoundException.class)
    public void testDefault() {
        DirectoryResolver resolver = new DirectoryResolver("app");
        File file = resolver.resolve();
        Assert.assertEquals(file.getAbsolutePath(), "/etc/app");
    }

}
