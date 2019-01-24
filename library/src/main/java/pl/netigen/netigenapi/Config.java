package pl.netigen.netigenapi;

import java.util.List;

public class Config {
    private static Config instance;
    private boolean noAdsBought;
    private boolean inDebugMode;
    private boolean isSamsung;
    private List<String> testDevices;

    Config(boolean inDebugMode, boolean isSamsung) {
        this.inDebugMode = inDebugMode;
        this.isSamsung = isSamsung;
    }

    public static boolean isNoAdsBought() {
        return getInstance().noAdsBought;
    }

    public static void setNoAdsBought(boolean noAdsBought) {
        getInstance().noAdsBought = noAdsBought;
    }

    public static void initialize(ConfigBuilder configBuilder) {
        instance = configBuilder.createConfig();
    }

    public static boolean isInDebugMode() {
        return getInstance().inDebugMode;
    }

    public static boolean isSamsung() {
        return getInstance().isSamsung;
    }

    private static Config getInstance() {
        if (instance == null) {
            initialize(new ConfigBuilder());
        }
        return instance;
    }

    public static List<String> getTestDevices(){
        return getInstance().testDevices;
    }

    public void setTestDevices(List<String> testDevices) {
        this.testDevices = testDevices;
    }


}
