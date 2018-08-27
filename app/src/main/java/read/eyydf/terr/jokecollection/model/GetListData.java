package read.eyydf.terr.jokecollection.model;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class GetListData implements Serializable{
	private String tuPianURL;
	private String guangGaoURL;
	private String guangGaoTitle="";
	private int article_id;
	private String article_name;
	private String content;
	private int imageNum;
	private int countlike;
	private int countcollect;
	private int is_collect;
	private int is_like;
	private String date;

	public String getGuangGaoTitle() {
		return guangGaoTitle;
	}

	public void setGuangGaoTitle(String guangGaoTitle) {
		this.guangGaoTitle = guangGaoTitle;
	}

	public String getTuPianURL() {
		return tuPianURL;
	}

	public void setTuPianURL(String tuPianURL) {
		this.tuPianURL = tuPianURL;
	}

	public String getGuangGaoURL() {
		return guangGaoURL;
	}

	public void setGuangGaoURL(String guangGaoURL) {
		this.guangGaoURL = guangGaoURL;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	private ArrayList<String> contentPictures;
	public int getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
	public String getArticle_name() {
		return article_name;
	}
	public void setArticle_name(String article_name) {
		this.article_name = article_name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getImageNum() {
		return imageNum;
	}
	public void setImageNum(int imageNum) {
		this.imageNum = imageNum;
	}
	public ArrayList<String> getContentPictures() {
		return contentPictures;
	}
	public void setContentPictures(ArrayList<String> contentPictures) {
		this.contentPictures = contentPictures;
	}
	public int getCountlike() {
		return countlike;
	}
	public void setCountlike(int countlike) {
		this.countlike = countlike;
	}
	public int getCountcollect() {
		return countcollect;
	}
	public void setCountcollect(int countcollect) {
		this.countcollect = countcollect;
	}
	public int getIs_collect() {
		return is_collect;
	}
	public void setIs_collect(int is_collect) {
		this.is_collect = is_collect;
	}
	public int getIs_like() {
		return is_like;
	}
	public void setIs_like(int is_like) {
		this.is_like = is_like;
	}
}
