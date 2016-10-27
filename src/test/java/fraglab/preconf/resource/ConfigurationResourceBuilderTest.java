package fraglab.preconf.resource;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

public class ConfigurationResourceBuilderTest {

    @DataProvider(name = "standard")
    public Object[][] standardProvider() {
        return new Object[][]{
                {mandatoryArray()}
        };
    }

    private String[] mandatoryArray() {
        return new String[]{
                "one", "two", "three"
        };
    }

    @Test(dataProvider = "standard")
    public void testBuilderStandard(String[] mandatory) {
        ConfigurationResourcesBuilder builder = new ConfigurationResourcesBuilder();
        Arrays.stream(mandatory).forEach(builder::addMandatory);
        ConfigurationResources configurationResources = builder.build();

        Assert.assertTrue(configurationResources.getMandatory().size() == mandatory.length);
        Arrays.stream(mandatory).forEach(m -> Assert.assertTrue(configurationResources.getMandatory().contains(m)));
    }

}
