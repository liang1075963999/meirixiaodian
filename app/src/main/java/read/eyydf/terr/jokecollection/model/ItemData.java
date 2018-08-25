package read.eyydf.terr.jokecollection.model;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by liang on 2018/8/2.
 */

public class ItemData {
    private JSONObject article;
    private int dianzanCount;
    private int shoucangCount;
    private int is_like;
    private int is_collect;
    private JSONArray contentPictures;

    public ItemData() {
    }

    public ItemData(int dianzanCount, int shoucangCount, int is_like, int is_collect, JSONArray contentPictures) {
        this.dianzanCount = dianzanCount;
        this.shoucangCount = shoucangCount;
        this.is_like = is_like;
        this.is_collect = is_collect;
        this.contentPictures=contentPictures;
    }

    public int getDianzanCount() {
        return dianzanCount;
    }

    public JSONObject getArticle() {
        return article;
    }

    public void setArticle(JSONObject article) {
        this.article = article;
    }

    public void setDianzanCount(int dianzanCount) {
        this.dianzanCount = dianzanCount;
    }

    public int getShoucangCount() {
        return shoucangCount;
    }

    public void setShoucangCount(int shoucangCount) {
        this.shoucangCount = shoucangCount;
    }

    public int getIs_like() {
        return is_like;
    }

    public void setIs_like(int is_like) {
        this.is_like = is_like;
    }

    public int getIs_collect() {
        return is_collect;
    }

    public void setIs_collect(int is_collect) {
        this.is_collect = is_collect;
    }

    public JSONArray getContentPictures() {
        return contentPictures;
    }

    public void setContentPictures(JSONArray contentPictures) {
        this.contentPictures = contentPictures;
    }
}
