package pl.netigen.netigenapi;

public class ConfigBuilder {
    private boolean inDebugMode;
    private boolean isSamsung;

    public ConfigBuilder setInDebugMode(boolean inDebugMode) {
        this.inDebugMode = inDebugMode;
        return this;
    }

    public ConfigBuilder setIsSamsung(boolean isSamsung) {
        this.isSamsung = isSamsung;
        return this;
    }

    public Config createConfig() {
        return new Config(inDebugMode, isSamsung);
    }
}