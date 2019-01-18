package pl.netigen.netigenapi;

public class Config {
    private static Config instance;
    private boolean noAdsBought;
    private boolean inDebugMode;
    private boolean isSamsung;

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
}
