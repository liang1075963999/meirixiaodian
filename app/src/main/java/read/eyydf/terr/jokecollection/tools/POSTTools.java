package read.eyydf.terr.jokecollection.tools;

import android.util.Log;

import org.json.JSONObject;

/**
 * 上传参数
 *
 * @author fenghu
 */
public class POSTTools {
    public static byte[] getString(int position, int type, int column, int articleId, int page, String user,
                                   String pass, int userId, int collecttype, int classId) {
        JSONObject postString = null;
        try {
            postString = new JSONObject();
            postString.put("appId", 1);
            postString.put("version", DeviceUtils.packageName + "_" + DeviceUtils.version);
            if (column != -1) {
                postString.put("column", column);
            }
            if (position != -1) {
                postString.put("position", position);
            }

            if (articleId != -1) {
                postString.put("articleId", articleId);
            }
            if (type != -1) {
                postString.put("Type", type);
            }
            if (page != -1) {
                postString.put("page", page);
            }
            if (user != null) {
                postString.put("user", user);
            }
            if (pass != null) {
                postString.put("pass", pass);
            }
            if (userId != -1) {
                postString.put("userId", userId);
            }
            if (collecttype != -1) {
                postString.put("collecttype", collecttype);
            }
            if (classId != -1) {
                postString.put("classId", classId);
            }
            postString.put("imei", DeviceUtils.getIMEI());
            postString.put("androidId", DeviceUtils.getAndroidID());
            Log.e("testLog", postString.toString());
        } catch (Exception e) {
        }
        byte[] b = null;
        try {
            b = postString.toString().getBytes();
        } catch (Exception e) {
        }
        return b;
    }

    public static byte[] getRiBaoString(int position, int type, int column, int articleId, int page, String user,
                                        String pass, int userId, int collecttype, int classId, int affiliateid) {
        JSONObject postString = null;
        try {
            postString = new JSONObject();
            postString.put("appId", 1);
            postString.put("version", DeviceUtils.packageName + "_" + DeviceUtils.version);
            if (column != -1) {
                postString.put("column", column);
            }
            if (position != -1) {
                postString.put("position", position);
            }

            if (articleId != -1) {
                postString.put("articleId", articleId);
            }
            if (type != -1) {
                postString.put("Type", type);
            }
            if (page != -1) {
                postString.put("page", page);
            }
            if (user != null) {
                postString.put("user", user);
            }
            if (pass != null) {
                postString.put("pass", pass);
            }
            if (userId != -1) {
                postString.put("userId", userId);
            }
            if (collecttype != -1) {
                postString.put("collecttype", collecttype);
            }
            if (classId != -1) {
                postString.put("classId", classId);
            }
            if (affiliateid != -1) {
                postString.put("affiliateid", affiliateid);
            }
            postString.put("imei", DeviceUtils.getIMEI());
            postString.put("androidId", DeviceUtils.getAndroidID());
            Log.d("POSTTools", "affiliateid:" + affiliateid);
            Log.e("testLog", postString.toString());
        } catch (Exception e) {
            Log.d("POSTTools", e.getMessage().toString());
        }
        byte[] b = null;
        try {
            b = postString.toString().getBytes();
        } catch (Exception e) {
            Log.d("POSTTools", e.getMessage().toString());
        }
        return b;
    }
    public static byte[] getRiBaoNiChengString(int position, int type, int column, int articleId, int page, String user,
                                        String pass, int userId, int collecttype, int classId, int affiliateid,String nicheng) {
        JSONObject postString = null;
        try {
            postString = new JSONObject();
            postString.put("appId", 1);
            postString.put("version", DeviceUtils.packageName + "_" + DeviceUtils.version);
            if (column != -1) {
                postString.put("column", column);
            }
            if (position != -1) {
                postString.put("position", position);
            }

            if (articleId != -1) {
                postString.put("articleId", articleId);
            }
            if (type != -1) {
                postString.put("Type", type);
            }
            if (page != -1) {
                postString.put("page", page);
            }
            if (user != null) {
                postString.put("user", user);
            }
            if (pass != null) {
                postString.put("pass", pass);
            }
            if (userId != -1) {
                postString.put("userId", userId);
            }
            if (collecttype != -1) {
                postString.put("collecttype", collecttype);
            }
            if (classId != -1) {
                postString.put("classId", classId);
            }
            if (affiliateid != -1) {
                postString.put("affiliateid", affiliateid);
            }
            if (!nicheng.equals("")) {
                postString.put("nickname", nicheng);
            }
            postString.put("imei", DeviceUtils.getIMEI());
            postString.put("androidId", DeviceUtils.getAndroidID());
            Log.d("POSTTools", "affiliateid:" + affiliateid);
            Log.e("testLog", postString.toString());
        } catch (Exception e) {
            Log.d("POSTTools", e.getMessage().toString());
        }
        byte[] b = null;
        try {
            b = postString.toString().getBytes();
        } catch (Exception e) {
            Log.d("POSTTools", e.getMessage().toString());
        }
        return b;
    }
    public static byte[] getRiBaoQianMingString(int position, int type, int column, int articleId, int page, String user,
                                               String pass, int userId, int collecttype, int classId, int affiliateid,String qianming) {
        JSONObject postString = null;
        try {
            postString = new JSONObject();
            postString.put("appId", 1);
            postString.put("version", DeviceUtils.packageName + "_" + DeviceUtils.version);
            if (column != -1) {
                postString.put("column", column);
            }
            if (position != -1) {
                postString.put("position", position);
            }

            if (articleId != -1) {
                postString.put("articleId", articleId);
            }
            if (type != -1) {
                postString.put("Type", type);
            }
            if (page != -1) {
                postString.put("page", page);
            }
            if (user != null) {
                postString.put("user", user);
            }
            if (pass != null) {
                postString.put("pass", pass);
            }
            if (userId != -1) {
                postString.put("userId", userId);
            }
            if (collecttype != -1) {
                postString.put("collecttype", collecttype);
            }
            if (classId != -1) {
                postString.put("classId", classId);
            }
            if (affiliateid != -1) {
                postString.put("affiliateid", affiliateid);
            }
            if (!qianming.equals("")) {
                postString.put("signature", qianming);
            }
            postString.put("imei", DeviceUtils.getIMEI());
            postString.put("androidId", DeviceUtils.getAndroidID());
            Log.d("POSTTools", "affiliateid:" + affiliateid);
            Log.e("testLog", postString.toString());
        } catch (Exception e) {
            Log.d("POSTTools", e.getMessage().toString());
        }
        byte[] b = null;
        try {
            b = postString.toString().getBytes();
        } catch (Exception e) {
            Log.d("POSTTools", e.getMessage().toString());
        }
        return b;
    }
}
