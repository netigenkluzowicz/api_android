package pl.netigen.netigenapi;


public abstract class BaseResourcesLoader {
    private LoadProgressListener loadProgressListener;

    protected BaseResourcesLoader(LoadProgressListener loadProgressListener) {
        this.loadProgressListener = loadProgressListener;
    }

    /**
     * You should call this method to display loading resources task progress to inform user about it
     */
    public void onLoadProgress(String progressText) {
        loadProgressListener.onLoadProgress(progressText);
    }

    /**
     * You must call this method when load resources task is finished or splash activity will not launch Your main activity
     */
    public void onFinishLoading() {
        loadProgressListener.onFinishLoading();
    }
}
