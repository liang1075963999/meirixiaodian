package read.eyydf.terr.jokecollection.model;

/**
 * Created by liang on 2018/8/1.
 */

public class BannerData {
    private boolean isOpen;
    private String url;

    public BannerData() {
    }

    public BannerData(boolean isOpen, String url) {
        this.isOpen = isOpen;
        this.url = url;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
