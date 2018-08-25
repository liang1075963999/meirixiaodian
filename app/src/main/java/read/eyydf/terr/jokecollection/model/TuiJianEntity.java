package read.eyydf.terr.jokecollection.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liang on 2018/8/23.
 */

public class TuiJianEntity implements Parcelable {

    private ArrayList<Choicenesslistshiti> choicenesslist;
    private List<Bannerlistshiti> bannerlist;
    private List<Picturelistshiti> picturelist;
    private List<Columnlistshiti> columnlist;

    public ArrayList<Choicenesslistshiti> getChoicenesslist() {
        return choicenesslist;
    }

    public void setChoicenesslist(ArrayList<Choicenesslistshiti> choicenesslist) {
        this.choicenesslist = choicenesslist;
    }

    public List<Bannerlistshiti> getBannerlist() {
        return bannerlist;
    }

    public void setBannerlist(List<Bannerlistshiti> bannerlist) {
        this.bannerlist = bannerlist;
    }

    public List<Picturelistshiti> getPicturelist() {
        return picturelist;
    }

    public void setPicturelist(List<Picturelistshiti> picturelist) {
        this.picturelist = picturelist;
    }

    public List<Columnlistshiti> getColumnlist() {
        return columnlist;
    }

    public void setColumnlist(List<Columnlistshiti> columnlist) {
        this.columnlist = columnlist;
    }

    public static class Choicenesslistshiti implements Parcelable {
        /**
         * appid : 1
         * article_id : 602
         * article_name :  污人真是让人脑洞大开呵~
         * class_id : 1
         * column_id : 27
         * content : 1、今日白露，就是提醒女孩子要把白白的大腿露出来，再不露就要穿秋裤了。
         2、对于女人来说，选男人就
         * contentPictures : [{"article_id":602,"column_id":0,"create_date":"2018-08-21 17:26:24","create_idate":20180821,"isaudit":1,"order":1,"picture_id":6802,"picture_name":"602_01.jpg","title":"602_01.jpg","url":"http://m.clubx.cn:9080/app/imageCompress/602_01.jpg"},{"article_id":602,"column_id":0,"create_date":"2018-08-21 17:26:24","create_idate":20180821,"isaudit":1,"order":2,"picture_id":6803,"picture_name":"602_02.jpg","title":"602_02.jpg","url":"http://m.clubx.cn:9080/app/imageCompress/602_02.jpg"},{"article_id":602,"column_id":0,"create_date":"2018-08-21 17:26:24","create_idate":20180821,"isaudit":1,"order":3,"picture_id":6804,"picture_name":"602_03.jpg","title":"602_03.jpg","url":"http://m.clubx.cn:9080/app/imageCompress/602_03.jpg"}]
         * countcollect : 374
         * countlike : 510
         * create_date : 2018-07-31 18:09:39
         * create_idate : 20180726
         * imageNum : 3
         * is_audit : 1
         * is_collect : 0
         * is_like : 0
         * superheat : 0
         * url : http://www.jokeji.cn/JokeHtml/bxnn/2016090814185169.htm
         */

        private int appid;
        private int article_id;
        private String article_name;
        private int class_id;
        private int column_id;
        private String content;
        private int countcollect;
        private int countlike;
        private String create_date;
        private int create_idate;
        private int imageNum;
        private int is_audit;
        private int is_collect;
        private int is_like;
        private int superheat;
        private String url;
        private List<ContentPicturesshiti> contentPictures;

        public int getAppid() {
            return appid;
        }

        public void setAppid(int appid) {
            this.appid = appid;
        }

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

        public int getClass_id() {
            return class_id;
        }

        public void setClass_id(int class_id) {
            this.class_id = class_id;
        }

        public int getColumn_id() {
            return column_id;
        }

        public void setColumn_id(int column_id) {
            this.column_id = column_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getCountcollect() {
            return countcollect;
        }

        public void setCountcollect(int countcollect) {
            this.countcollect = countcollect;
        }

        public int getCountlike() {
            return countlike;
        }

        public void setCountlike(int countlike) {
            this.countlike = countlike;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public int getCreate_idate() {
            return create_idate;
        }

        public void setCreate_idate(int create_idate) {
            this.create_idate = create_idate;
        }

        public int getImageNum() {
            return imageNum;
        }

        public void setImageNum(int imageNum) {
            this.imageNum = imageNum;
        }

        public int getIs_audit() {
            return is_audit;
        }

        public void setIs_audit(int is_audit) {
            this.is_audit = is_audit;
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

        public int getSuperheat() {
            return superheat;
        }

        public void setSuperheat(int superheat) {
            this.superheat = superheat;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<ContentPicturesshiti> getContentPictures() {
            return contentPictures;
        }

        public void setContentPictures(List<ContentPicturesshiti> contentPictures) {
            this.contentPictures = contentPictures;
        }

        public static class ContentPicturesshiti implements Parcelable {
            /**
             * article_id : 602
             * column_id : 0
             * create_date : 2018-08-21 17:26:24
             * create_idate : 20180821
             * isaudit : 1
             * order : 1
             * picture_id : 6802
             * picture_name : 602_01.jpg
             * title : 602_01.jpg
             * url : http://m.clubx.cn:9080/app/imageCompress/602_01.jpg
             */

            private int article_id;
            private int column_id;
            private String create_date;
            private int create_idate;
            private int isaudit;
            private int order;
            private int picture_id;
            private String picture_name;
            private String title;
            private String url;

            public int getArticle_id() {
                return article_id;
            }

            public void setArticle_id(int article_id) {
                this.article_id = article_id;
            }

            public int getColumn_id() {
                return column_id;
            }

            public void setColumn_id(int column_id) {
                this.column_id = column_id;
            }

            public String getCreate_date() {
                return create_date;
            }

            public void setCreate_date(String create_date) {
                this.create_date = create_date;
            }

            public int getCreate_idate() {
                return create_idate;
            }

            public void setCreate_idate(int create_idate) {
                this.create_idate = create_idate;
            }

            public int getIsaudit() {
                return isaudit;
            }

            public void setIsaudit(int isaudit) {
                this.isaudit = isaudit;
            }

            public int getOrder() {
                return order;
            }

            public void setOrder(int order) {
                this.order = order;
            }

            public int getPicture_id() {
                return picture_id;
            }

            public void setPicture_id(int picture_id) {
                this.picture_id = picture_id;
            }

            public String getPicture_name() {
                return picture_name;
            }

            public void setPicture_name(String picture_name) {
                this.picture_name = picture_name;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.article_id);
                dest.writeInt(this.column_id);
                dest.writeString(this.create_date);
                dest.writeInt(this.create_idate);
                dest.writeInt(this.isaudit);
                dest.writeInt(this.order);
                dest.writeInt(this.picture_id);
                dest.writeString(this.picture_name);
                dest.writeString(this.title);
                dest.writeString(this.url);
            }

            public ContentPicturesshiti() {
            }

            protected ContentPicturesshiti(Parcel in) {
                this.article_id = in.readInt();
                this.column_id = in.readInt();
                this.create_date = in.readString();
                this.create_idate = in.readInt();
                this.isaudit = in.readInt();
                this.order = in.readInt();
                this.picture_id = in.readInt();
                this.picture_name = in.readString();
                this.title = in.readString();
                this.url = in.readString();
            }

            public static final Creator<ContentPicturesshiti> CREATOR = new Creator<ContentPicturesshiti>() {
                @Override
                public ContentPicturesshiti createFromParcel(Parcel source) {
                    return new ContentPicturesshiti(source);
                }

                @Override
                public ContentPicturesshiti[] newArray(int size) {
                    return new ContentPicturesshiti[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.appid);
            dest.writeInt(this.article_id);
            dest.writeString(this.article_name);
            dest.writeInt(this.class_id);
            dest.writeInt(this.column_id);
            dest.writeString(this.content);
            dest.writeInt(this.countcollect);
            dest.writeInt(this.countlike);
            dest.writeString(this.create_date);
            dest.writeInt(this.create_idate);
            dest.writeInt(this.imageNum);
            dest.writeInt(this.is_audit);
            dest.writeInt(this.is_collect);
            dest.writeInt(this.is_like);
            dest.writeInt(this.superheat);
            dest.writeString(this.url);
            dest.writeList(this.contentPictures);
        }

        public Choicenesslistshiti() {
        }

        protected Choicenesslistshiti(Parcel in) {
            this.appid = in.readInt();
            this.article_id = in.readInt();
            this.article_name = in.readString();
            this.class_id = in.readInt();
            this.column_id = in.readInt();
            this.content = in.readString();
            this.countcollect = in.readInt();
            this.countlike = in.readInt();
            this.create_date = in.readString();
            this.create_idate = in.readInt();
            this.imageNum = in.readInt();
            this.is_audit = in.readInt();
            this.is_collect = in.readInt();
            this.is_like = in.readInt();
            this.superheat = in.readInt();
            this.url = in.readString();
            this.contentPictures = new ArrayList<ContentPicturesshiti>();
            in.readList(this.contentPictures, ContentPicturesshiti.class.getClassLoader());
        }

        public static final Creator<Choicenesslistshiti> CREATOR = new Creator<Choicenesslistshiti>() {
            @Override
            public Choicenesslistshiti createFromParcel(Parcel source) {
                return new Choicenesslistshiti(source);
            }

            @Override
            public Choicenesslistshiti[] newArray(int size) {
                return new Choicenesslistshiti[size];
            }
        };
    }

    public static class Bannerlistshiti implements Parcelable {
        /**
         * address : 1
         * advertise_url :
         * appid : 1
         * bannerImgs : [{"advertise_url":"","appid":1,"banner_id":9,"create_date":"2018-08-17 11:41:45","create_idate":20180816,"img_id":13,"img_name":"hongbaoguowei.jpg","method":1,"url":"http://app.clubx.cn:9080/RoflJoke/bannerimage/hongbaoguowei.jpg"},{"advertise_url":"","appid":1,"banner_id":9,"create_date":"2018-08-17 11:41:47","create_idate":20180816,"img_id":14,"img_name":"appwaer_120180813171855.png","method":1,"url":"http://app.clubx.cn:9080/RoflJoke/bannerimage/appwaer_120180813171855.png"},{"advertise_url":"","appid":1,"banner_id":9,"create_date":"2018-08-17 11:41:49","create_idate":20180816,"img_id":15,"img_name":"hongbaoguoshi.jpg","method":1,"url":"http://app.clubx.cn:9080/RoflJoke/bannerimage/hognbaoguoshi.jpg"},{"advertise_url":"","appid":1,"banner_id":9,"create_date":"2018-08-17 11:41:51","create_idate":20180816,"img_id":16,"img_name":"hognbaoguoshi3.jpg","method":1,"url":"http://app.clubx.cn:9080/RoflJoke/bannerimage/hognbaoguoshi3.jpg"}]
         * banner_id : 9
         * banner_name : 爆笑日报百科信息流
         * create_date : 2018-08-17 11:29:27
         * create_idate : 20180816
         * imageNum : 4
         */

        private int address;
        private String advertise_url;
        private int appid;
        private int banner_id;
        private String banner_name;
        private String create_date;
        private int create_idate;
        private int imageNum;
        private List<BannerImgsshiti> bannerImgs;

        public int getAddress() {
            return address;
        }

        public void setAddress(int address) {
            this.address = address;
        }

        public String getAdvertise_url() {
            return advertise_url;
        }

        public void setAdvertise_url(String advertise_url) {
            this.advertise_url = advertise_url;
        }

        public int getAppid() {
            return appid;
        }

        public void setAppid(int appid) {
            this.appid = appid;
        }

        public int getBanner_id() {
            return banner_id;
        }

        public void setBanner_id(int banner_id) {
            this.banner_id = banner_id;
        }

        public String getBanner_name() {
            return banner_name;
        }

        public void setBanner_name(String banner_name) {
            this.banner_name = banner_name;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public int getCreate_idate() {
            return create_idate;
        }

        public void setCreate_idate(int create_idate) {
            this.create_idate = create_idate;
        }

        public int getImageNum() {
            return imageNum;
        }

        public void setImageNum(int imageNum) {
            this.imageNum = imageNum;
        }

        public List<BannerImgsshiti> getBannerImgs() {
            return bannerImgs;
        }

        public void setBannerImgs(List<BannerImgsshiti> bannerImgs) {
            this.bannerImgs = bannerImgs;
        }

        public static class BannerImgsshiti implements Parcelable {
            /**
             * advertise_url :
             * appid : 1
             * banner_id : 9
             * create_date : 2018-08-17 11:41:45
             * create_idate : 20180816
             * img_id : 13
             * img_name : hongbaoguowei.jpg
             * method : 1
             * url : http://app.clubx.cn:9080/RoflJoke/bannerimage/hongbaoguowei.jpg
             */

            private String advertise_url;
            private int appid;
            private int banner_id;
            private String create_date;
            private int create_idate;
            private int img_id;
            private String img_name;
            private int method;
            private String url;

            public String getAdvertise_url() {
                return advertise_url;
            }

            public void setAdvertise_url(String advertise_url) {
                this.advertise_url = advertise_url;
            }

            public int getAppid() {
                return appid;
            }

            public void setAppid(int appid) {
                this.appid = appid;
            }

            public int getBanner_id() {
                return banner_id;
            }

            public void setBanner_id(int banner_id) {
                this.banner_id = banner_id;
            }

            public String getCreate_date() {
                return create_date;
            }

            public void setCreate_date(String create_date) {
                this.create_date = create_date;
            }

            public int getCreate_idate() {
                return create_idate;
            }

            public void setCreate_idate(int create_idate) {
                this.create_idate = create_idate;
            }

            public int getImg_id() {
                return img_id;
            }

            public void setImg_id(int img_id) {
                this.img_id = img_id;
            }

            public String getImg_name() {
                return img_name;
            }

            public void setImg_name(String img_name) {
                this.img_name = img_name;
            }

            public int getMethod() {
                return method;
            }

            public void setMethod(int method) {
                this.method = method;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.advertise_url);
                dest.writeInt(this.appid);
                dest.writeInt(this.banner_id);
                dest.writeString(this.create_date);
                dest.writeInt(this.create_idate);
                dest.writeInt(this.img_id);
                dest.writeString(this.img_name);
                dest.writeInt(this.method);
                dest.writeString(this.url);
            }

            public BannerImgsshiti() {
            }

            protected BannerImgsshiti(Parcel in) {
                this.advertise_url = in.readString();
                this.appid = in.readInt();
                this.banner_id = in.readInt();
                this.create_date = in.readString();
                this.create_idate = in.readInt();
                this.img_id = in.readInt();
                this.img_name = in.readString();
                this.method = in.readInt();
                this.url = in.readString();
            }

            public static final Creator<BannerImgsshiti> CREATOR = new Creator<BannerImgsshiti>() {
                @Override
                public BannerImgsshiti createFromParcel(Parcel source) {
                    return new BannerImgsshiti(source);
                }

                @Override
                public BannerImgsshiti[] newArray(int size) {
                    return new BannerImgsshiti[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.address);
            dest.writeString(this.advertise_url);
            dest.writeInt(this.appid);
            dest.writeInt(this.banner_id);
            dest.writeString(this.banner_name);
            dest.writeString(this.create_date);
            dest.writeInt(this.create_idate);
            dest.writeInt(this.imageNum);
            dest.writeList(this.bannerImgs);
        }

        public Bannerlistshiti() {
        }

        protected Bannerlistshiti(Parcel in) {
            this.address = in.readInt();
            this.advertise_url = in.readString();
            this.appid = in.readInt();
            this.banner_id = in.readInt();
            this.banner_name = in.readString();
            this.create_date = in.readString();
            this.create_idate = in.readInt();
            this.imageNum = in.readInt();
            this.bannerImgs = new ArrayList<BannerImgsshiti>();
            in.readList(this.bannerImgs, BannerImgsshiti.class.getClassLoader());
        }

        public static final Creator<Bannerlistshiti> CREATOR = new Creator<Bannerlistshiti>() {
            @Override
            public Bannerlistshiti createFromParcel(Parcel source) {
                return new Bannerlistshiti(source);
            }

            @Override
            public Bannerlistshiti[] newArray(int size) {
                return new Bannerlistshiti[size];
            }
        };
    }

    public static class Picturelistshiti implements Parcelable {
        /**
         * appid : 1
         * article_id : 254
         * article_name : 分享图片
         * class_id : 0
         * column_id : 6
         * content :
         * contentPictures : [{"article_id":254,"column_id":6,"create_date":null,"create_idate":20180726,"isaudit":0,"order":0,"picture_id":507,"picture_name":"Cg-4WlJWIDmIFbsjAAAzqmfa0HkAAMZFwKsQjIAADPC778.jpg","title":"分享图片","url":"http://m.clubx.cn:9080/app/image/Cg-4WlJWIDmIFbsjAAAzqmfa0HkAAMZFwKsQjIAADPC778.jpg"}]
         * countcollect : 89
         * countlike : 647
         * create_date : null
         * create_idate : 20180726
         * imageNum : 1
         * is_audit : 0
         * is_collect : 0
         * is_like : 0
         * superheat : 0
         * url :
         */

        private int appid;
        private int article_id;
        private String article_name;
        private int class_id;
        private int column_id;
        private String content;
        private int countcollect;
        private int countlike;
        private String create_date;
        private int create_idate;
        private int imageNum;
        private int is_audit;
        private int is_collect;
        private int is_like;
        private int superheat;
        private String url;
        private List<ContentPicturesshitiX> contentPictures;

        public int getAppid() {
            return appid;
        }

        public void setAppid(int appid) {
            this.appid = appid;
        }

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

        public int getClass_id() {
            return class_id;
        }

        public void setClass_id(int class_id) {
            this.class_id = class_id;
        }

        public int getColumn_id() {
            return column_id;
        }

        public void setColumn_id(int column_id) {
            this.column_id = column_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getCountcollect() {
            return countcollect;
        }

        public void setCountcollect(int countcollect) {
            this.countcollect = countcollect;
        }

        public int getCountlike() {
            return countlike;
        }

        public void setCountlike(int countlike) {
            this.countlike = countlike;
        }

        public Object getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public int getCreate_idate() {
            return create_idate;
        }

        public void setCreate_idate(int create_idate) {
            this.create_idate = create_idate;
        }

        public int getImageNum() {
            return imageNum;
        }

        public void setImageNum(int imageNum) {
            this.imageNum = imageNum;
        }

        public int getIs_audit() {
            return is_audit;
        }

        public void setIs_audit(int is_audit) {
            this.is_audit = is_audit;
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

        public int getSuperheat() {
            return superheat;
        }

        public void setSuperheat(int superheat) {
            this.superheat = superheat;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<ContentPicturesshitiX> getContentPictures() {
            return contentPictures;
        }

        public void setContentPictures(List<ContentPicturesshitiX> contentPictures) {
            this.contentPictures = contentPictures;
        }

        public static class ContentPicturesshitiX implements Parcelable {
            /**
             * article_id : 254
             * column_id : 6
             * create_date : null
             * create_idate : 20180726
             * isaudit : 0
             * order : 0
             * picture_id : 507
             * picture_name : Cg-4WlJWIDmIFbsjAAAzqmfa0HkAAMZFwKsQjIAADPC778.jpg
             * title : 分享图片
             * url : http://m.clubx.cn:9080/app/image/Cg-4WlJWIDmIFbsjAAAzqmfa0HkAAMZFwKsQjIAADPC778.jpg
             */

            private int article_id;
            private int column_id;
            private String create_date;
            private int create_idate;
            private int isaudit;
            private int order;
            private int picture_id;
            private String picture_name;
            private String title;
            private String url;

            public int getArticle_id() {
                return article_id;
            }

            public void setArticle_id(int article_id) {
                this.article_id = article_id;
            }

            public int getColumn_id() {
                return column_id;
            }

            public void setColumn_id(int column_id) {
                this.column_id = column_id;
            }

            public Object getCreate_date() {
                return create_date;
            }

            public void setCreate_date(String create_date) {
                this.create_date = create_date;
            }

            public int getCreate_idate() {
                return create_idate;
            }

            public void setCreate_idate(int create_idate) {
                this.create_idate = create_idate;
            }

            public int getIsaudit() {
                return isaudit;
            }

            public void setIsaudit(int isaudit) {
                this.isaudit = isaudit;
            }

            public int getOrder() {
                return order;
            }

            public void setOrder(int order) {
                this.order = order;
            }

            public int getPicture_id() {
                return picture_id;
            }

            public void setPicture_id(int picture_id) {
                this.picture_id = picture_id;
            }

            public String getPicture_name() {
                return picture_name;
            }

            public void setPicture_name(String picture_name) {
                this.picture_name = picture_name;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.article_id);
                dest.writeInt(this.column_id);
                dest.writeString(this.create_date);
                dest.writeInt(this.create_idate);
                dest.writeInt(this.isaudit);
                dest.writeInt(this.order);
                dest.writeInt(this.picture_id);
                dest.writeString(this.picture_name);
                dest.writeString(this.title);
                dest.writeString(this.url);
            }

            public ContentPicturesshitiX() {
            }

            protected ContentPicturesshitiX(Parcel in) {
                this.article_id = in.readInt();
                this.column_id = in.readInt();
                this.create_date = in.readParcelable(Object.class.getClassLoader());
                this.create_idate = in.readInt();
                this.isaudit = in.readInt();
                this.order = in.readInt();
                this.picture_id = in.readInt();
                this.picture_name = in.readString();
                this.title = in.readString();
                this.url = in.readString();
            }

            public static final Creator<ContentPicturesshitiX> CREATOR = new Creator<ContentPicturesshitiX>() {
                @Override
                public ContentPicturesshitiX createFromParcel(Parcel source) {
                    return new ContentPicturesshitiX(source);
                }

                @Override
                public ContentPicturesshitiX[] newArray(int size) {
                    return new ContentPicturesshitiX[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.appid);
            dest.writeInt(this.article_id);
            dest.writeString(this.article_name);
            dest.writeInt(this.class_id);
            dest.writeInt(this.column_id);
            dest.writeString(this.content);
            dest.writeInt(this.countcollect);
            dest.writeInt(this.countlike);
            dest.writeString(this.create_date);
            dest.writeInt(this.create_idate);
            dest.writeInt(this.imageNum);
            dest.writeInt(this.is_audit);
            dest.writeInt(this.is_collect);
            dest.writeInt(this.is_like);
            dest.writeInt(this.superheat);
            dest.writeString(this.url);
            dest.writeList(this.contentPictures);
        }

        public Picturelistshiti() {
        }

        protected Picturelistshiti(Parcel in) {
            this.appid = in.readInt();
            this.article_id = in.readInt();
            this.article_name = in.readString();
            this.class_id = in.readInt();
            this.column_id = in.readInt();
            this.content = in.readString();
            this.countcollect = in.readInt();
            this.countlike = in.readInt();
            this.create_date = in.readParcelable(Object.class.getClassLoader());
            this.create_idate = in.readInt();
            this.imageNum = in.readInt();
            this.is_audit = in.readInt();
            this.is_collect = in.readInt();
            this.is_like = in.readInt();
            this.superheat = in.readInt();
            this.url = in.readString();
            this.contentPictures = new ArrayList<ContentPicturesshitiX>();
            in.readList(this.contentPictures, ContentPicturesshitiX.class.getClassLoader());
        }

        public static final Creator<Picturelistshiti> CREATOR = new Creator<Picturelistshiti>() {
            @Override
            public Picturelistshiti createFromParcel(Parcel source) {
                return new Picturelistshiti(source);
            }

            @Override
            public Picturelistshiti[] newArray(int size) {
                return new Picturelistshiti[size];
            }
        };
    }

    public static class Columnlistshiti implements Parcelable {
        /**
         * appid : 1
         * article_id : 180
         * article_name :  高冷夫妻,比比谁更冷
         * class_id : 5
         * column_id : 5
         * content : 1、一天，丈夫下班回家，妻子问：“老公，如果隔壁老王在咱家衣柜你会生气吗？”丈夫闻言就火冒三丈：“什
         * contentPictures : [{"article_id":180,"column_id":0,"create_date":"2018-07-27 16:33:37","create_idate":20180726,"isaudit":1,"order":1,"picture_id":359,"picture_name":"Cg-4WFJWIVyIXfFkAAHK9LF2Xy4AAMY7gEzwHwAAcsM931.jpg","title":"分享图片","url":"http://m.clubx.cn:9080/app/imageCompress/Cg-4WFJWIVyIXfFkAAHK9LF2Xy4AAMY7gEzwHwAAcsM931.jpg"},{"article_id":180,"column_id":0,"create_date":"2018-07-27 16:33:37","create_idate":20180726,"isaudit":1,"order":2,"picture_id":360,"picture_name":"Cg-4V1JWIVyITa98AABLDpiCIzYAAMY7gEzdVYAAEsm343.jpg","title":"一只飞蛾引发的悲剧！","url":"http://m.clubx.cn:9080/app/imageCompress/Cg-4V1JWIVyITa98AABLDpiCIzYAAMY7gEzdVYAAEsm343.jpg"}]
         * countcollect : 748
         * countlike : 1346
         * create_date : 2018-07-31 18:33:28
         * create_idate : 20180726
         * imageNum : 2
         * is_audit : 1
         * is_collect : 0
         * is_like : 0
         * superheat : 0
         * url : http://www.jokeji.cn/JokeHtml/fq/2016072923075462.htm
         */

        private int appid;
        private int article_id;
        private String article_name;
        private int class_id;
        private int column_id;
        private String content;
        private int countcollect;
        private int countlike;
        private String create_date;
        private int create_idate;
        private int imageNum;
        private int is_audit;
        private int is_collect;
        private int is_like;
        private int superheat;
        private String url;
        private List<ContentPicturesshitiXX> contentPictures;

        public int getAppid() {
            return appid;
        }

        public void setAppid(int appid) {
            this.appid = appid;
        }

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

        public int getClass_id() {
            return class_id;
        }

        public void setClass_id(int class_id) {
            this.class_id = class_id;
        }

        public int getColumn_id() {
            return column_id;
        }

        public void setColumn_id(int column_id) {
            this.column_id = column_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getCountcollect() {
            return countcollect;
        }

        public void setCountcollect(int countcollect) {
            this.countcollect = countcollect;
        }

        public int getCountlike() {
            return countlike;
        }

        public void setCountlike(int countlike) {
            this.countlike = countlike;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public int getCreate_idate() {
            return create_idate;
        }

        public void setCreate_idate(int create_idate) {
            this.create_idate = create_idate;
        }

        public int getImageNum() {
            return imageNum;
        }

        public void setImageNum(int imageNum) {
            this.imageNum = imageNum;
        }

        public int getIs_audit() {
            return is_audit;
        }

        public void setIs_audit(int is_audit) {
            this.is_audit = is_audit;
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

        public int getSuperheat() {
            return superheat;
        }

        public void setSuperheat(int superheat) {
            this.superheat = superheat;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<ContentPicturesshitiXX> getContentPictures() {
            return contentPictures;
        }

        public void setContentPictures(List<ContentPicturesshitiXX> contentPictures) {
            this.contentPictures = contentPictures;
        }

        public static class ContentPicturesshitiXX implements Parcelable {
            /**
             * article_id : 180
             * column_id : 0
             * create_date : 2018-07-27 16:33:37
             * create_idate : 20180726
             * isaudit : 1
             * order : 1
             * picture_id : 359
             * picture_name : Cg-4WFJWIVyIXfFkAAHK9LF2Xy4AAMY7gEzwHwAAcsM931.jpg
             * title : 分享图片
             * url : http://m.clubx.cn:9080/app/imageCompress/Cg-4WFJWIVyIXfFkAAHK9LF2Xy4AAMY7gEzwHwAAcsM931.jpg
             */

            private int article_id;
            private int column_id;
            private String create_date;
            private int create_idate;
            private int isaudit;
            private int order;
            private int picture_id;
            private String picture_name;
            private String title;
            private String url;

            public int getArticle_id() {
                return article_id;
            }

            public void setArticle_id(int article_id) {
                this.article_id = article_id;
            }

            public int getColumn_id() {
                return column_id;
            }

            public void setColumn_id(int column_id) {
                this.column_id = column_id;
            }

            public String getCreate_date() {
                return create_date;
            }

            public void setCreate_date(String create_date) {
                this.create_date = create_date;
            }

            public int getCreate_idate() {
                return create_idate;
            }

            public void setCreate_idate(int create_idate) {
                this.create_idate = create_idate;
            }

            public int getIsaudit() {
                return isaudit;
            }

            public void setIsaudit(int isaudit) {
                this.isaudit = isaudit;
            }

            public int getOrder() {
                return order;
            }

            public void setOrder(int order) {
                this.order = order;
            }

            public int getPicture_id() {
                return picture_id;
            }

            public void setPicture_id(int picture_id) {
                this.picture_id = picture_id;
            }

            public String getPicture_name() {
                return picture_name;
            }

            public void setPicture_name(String picture_name) {
                this.picture_name = picture_name;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.article_id);
                dest.writeInt(this.column_id);
                dest.writeString(this.create_date);
                dest.writeInt(this.create_idate);
                dest.writeInt(this.isaudit);
                dest.writeInt(this.order);
                dest.writeInt(this.picture_id);
                dest.writeString(this.picture_name);
                dest.writeString(this.title);
                dest.writeString(this.url);
            }

            public ContentPicturesshitiXX() {
            }

            protected ContentPicturesshitiXX(Parcel in) {
                this.article_id = in.readInt();
                this.column_id = in.readInt();
                this.create_date = in.readString();
                this.create_idate = in.readInt();
                this.isaudit = in.readInt();
                this.order = in.readInt();
                this.picture_id = in.readInt();
                this.picture_name = in.readString();
                this.title = in.readString();
                this.url = in.readString();
            }

            public static final Creator<ContentPicturesshitiXX> CREATOR = new Creator<ContentPicturesshitiXX>() {
                @Override
                public ContentPicturesshitiXX createFromParcel(Parcel source) {
                    return new ContentPicturesshitiXX(source);
                }

                @Override
                public ContentPicturesshitiXX[] newArray(int size) {
                    return new ContentPicturesshitiXX[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.appid);
            dest.writeInt(this.article_id);
            dest.writeString(this.article_name);
            dest.writeInt(this.class_id);
            dest.writeInt(this.column_id);
            dest.writeString(this.content);
            dest.writeInt(this.countcollect);
            dest.writeInt(this.countlike);
            dest.writeString(this.create_date);
            dest.writeInt(this.create_idate);
            dest.writeInt(this.imageNum);
            dest.writeInt(this.is_audit);
            dest.writeInt(this.is_collect);
            dest.writeInt(this.is_like);
            dest.writeInt(this.superheat);
            dest.writeString(this.url);
            dest.writeList(this.contentPictures);
        }

        public Columnlistshiti() {
        }

        protected Columnlistshiti(Parcel in) {
            this.appid = in.readInt();
            this.article_id = in.readInt();
            this.article_name = in.readString();
            this.class_id = in.readInt();
            this.column_id = in.readInt();
            this.content = in.readString();
            this.countcollect = in.readInt();
            this.countlike = in.readInt();
            this.create_date = in.readString();
            this.create_idate = in.readInt();
            this.imageNum = in.readInt();
            this.is_audit = in.readInt();
            this.is_collect = in.readInt();
            this.is_like = in.readInt();
            this.superheat = in.readInt();
            this.url = in.readString();
            this.contentPictures = new ArrayList<ContentPicturesshitiXX>();
            in.readList(this.contentPictures, ContentPicturesshitiXX.class.getClassLoader());
        }

        public static final Creator<Columnlistshiti> CREATOR = new Creator<Columnlistshiti>() {
            @Override
            public Columnlistshiti createFromParcel(Parcel source) {
                return new Columnlistshiti(source);
            }

            @Override
            public Columnlistshiti[] newArray(int size) {
                return new Columnlistshiti[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.choicenesslist);
        dest.writeList(this.bannerlist);
        dest.writeList(this.picturelist);
        dest.writeList(this.columnlist);
    }

    public TuiJianEntity() {
    }

    protected TuiJianEntity(Parcel in) {
        this.choicenesslist = new ArrayList<Choicenesslistshiti>();
        in.readList(this.choicenesslist, Choicenesslistshiti.class.getClassLoader());
        this.bannerlist = new ArrayList<Bannerlistshiti>();
        in.readList(this.bannerlist, Bannerlistshiti.class.getClassLoader());
        this.picturelist = new ArrayList<Picturelistshiti>();
        in.readList(this.picturelist, Picturelistshiti.class.getClassLoader());
        this.columnlist = new ArrayList<Columnlistshiti>();
        in.readList(this.columnlist, Columnlistshiti.class.getClassLoader());
    }

    public static final Parcelable.Creator<TuiJianEntity> CREATOR = new Parcelable.Creator<TuiJianEntity>() {
        @Override
        public TuiJianEntity createFromParcel(Parcel source) {
            return new TuiJianEntity(source);
        }

        @Override
        public TuiJianEntity[] newArray(int size) {
            return new TuiJianEntity[size];
        }
    };
}
