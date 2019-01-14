package pl.netigen.netigenapi;


public interface LoadProgressListener {
    void onLoadProgress(String progressText);

    void onFinishLoading();
}
