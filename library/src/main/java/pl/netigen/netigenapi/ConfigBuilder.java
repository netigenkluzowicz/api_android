package pl.netigen.netigenapi;

import java.util.ArrayList;
import java.util.List;

public class ConfigBuilder {

    private boolean inDebugMode;
    private boolean isSamsung;
    private List<String> testDevices;

    public ConfigBuilder setInDebugMode(boolean inDebugMode) {
        this.inDebugMode = inDebugMode;
        return this;
    }

    public ConfigBuilder setIsSamsung(boolean isSamsung) {
        this.isSamsung = isSamsung;
        return this;
    }

    public ConfigBuilder setTestDevices(List<String> testDevices) {
        this.testDevices = testDevices;
        return this;
    }

    public ConfigBuilder addTestDevice(String testDevice) {
        if (this.testDevices == null) testDevices = new ArrayList<>();
        this.testDevices.add(testDevice);
        return this;
    }

    public Config createConfig() {
        Config config = new Config(inDebugMode, isSamsung);
        config.setTestDevices(this.testDevices);
        return config;
    }
}