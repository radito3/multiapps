package com.sap.cloud.lm.sl.mta.builders.v2;

import java.util.Arrays;

import org.junit.runners.Parameterized.Parameters;

import com.sap.cloud.lm.sl.common.util.TestUtil.Expectation;
import com.sap.cloud.lm.sl.mta.handlers.v2.ConfigurationParser;
import com.sap.cloud.lm.sl.mta.handlers.v2.DescriptorParser;
import com.sap.cloud.lm.sl.mta.model.v2.DeploymentDescriptor;
import com.sap.cloud.lm.sl.mta.model.v2.Platform;

public class ParametersChainBuilderTest extends com.sap.cloud.lm.sl.mta.builders.v1.PropertiesChainBuilderTest {

    @Parameters
    public static Iterable<Object[]> getParameters() {
        return Arrays.asList(new Object[][] {
// @formatter:off
            // (0) All module and resource types are present in the platform:
            {
                "mtad-02.yaml", "platform-04.json",
                new Expectation[] {
                    new Expectation(Expectation.Type.RESOURCE, "module-chain-01.json"),
                    new Expectation(Expectation.Type.RESOURCE, "module-chain-without-dependencies-01.json"),
                    new Expectation(Expectation.Type.RESOURCE, "resource-chain-04.json"),
                    new Expectation(Expectation.Type.RESOURCE, "resource-type-chain-01.json"),
                },
            },
            // (1) No module and resource types in the platform:
            {
                "mtad-02.yaml", "platform-02.json",
                new Expectation[] {
                    new Expectation(Expectation.Type.RESOURCE, "module-chain-02.json"),
                    new Expectation(Expectation.Type.RESOURCE, "module-chain-without-dependencies-02.json"),
                    new Expectation(Expectation.Type.RESOURCE, "resource-chain-02.json"),
                    new Expectation(Expectation.Type.RESOURCE, "resource-type-chain-02.json"),
                },
            },
            // (2) Some module and resource types are present in the platform:
            {
                "mtad-02.yaml", "platform-05.json",
                new Expectation[] {
                    new Expectation(Expectation.Type.RESOURCE, "module-chain-03.json"),
                    new Expectation(Expectation.Type.RESOURCE, "module-chain-without-dependencies-03.json"),
                    new Expectation(Expectation.Type.RESOURCE, "resource-chain-05.json"),
                    new Expectation(Expectation.Type.RESOURCE, "resource-type-chain-03.json"),
                },
            },
// @formatter:on
        });
    }

    public ParametersChainBuilderTest(String deploymentDescriptorLocation, String platformLocation,
        Expectation[] expectations) {
        super(deploymentDescriptorLocation, platformLocation, expectations);
    }

    @Override
    protected ConfigurationParser getConfigurationParser() {
        return new ConfigurationParser();
    }

    @Override
    protected DescriptorParser getDescriptorParser() {
        return new DescriptorParser();
    }

    @Override
    protected PropertiesChainBuilder createPropertiesChainBuilder(
        com.sap.cloud.lm.sl.mta.model.v1.DeploymentDescriptor deploymentDescriptor, com.sap.cloud.lm.sl.mta.model.v1.Platform platform) {
        return new ParametersChainBuilder((DeploymentDescriptor) deploymentDescriptor, (Platform) platform);
    }

}