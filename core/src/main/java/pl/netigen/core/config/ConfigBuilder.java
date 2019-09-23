package pl.netigen.core.config;
import java.util.List;

public class ConfigBuilder {

    private Config config;

    public ConfigBuilder(boolean inDebugMode, boolean isSamsung) {
        config = Config.Companion.getInstance(inDebugMode, isSamsung);
    }

    public ConfigBuilder setTestDevices(List<String> testDevices) {
        config.testDevices = testDevices;
        return this;
    }

    public ConfigBuilder addTestDevice(String testDevice) {
        config.testDevices.add(testDevice);
        return this;
    }

    public ConfigBuilder setRewardedAdId(String rewardedAdId) {
        config.setRewardedAdId(rewardedAdId);
        return this;
    }

    public ConfigBuilder setBannerAdId(String bannerAdId) {
        config.setBannerAdId(bannerAdId);
        return this;
    }

    public ConfigBuilder setInterstitialAdId(String interstitialAdId) {
        config.setRewardedAdId(interstitialAdId);
        return this;
    }

    public ConfigBuilder setMultiScreenApp(boolean isMultiScreenApp) {
        config.setMultiScreen(isMultiScreenApp);
        return this;
    }

    public Config createConfig() {
        return config;
    }
}