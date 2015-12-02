package utility;

import com.google.gson.Gson;
import facebook4j.*;
import facebook4j.auth.AccessToken;
import models.User;
import java.util.*;
import models.*;
import play.db.ebean.Model;

public class FacebookWrapper {

    private static FacebookWrapper instance;
    private static Facebook fb;

    private static String appID = "896964080392435";
    private static String appSecret = "b26ecb19307ecc7b2383a5c644c05856";
    private static String permissions = "email";

    private FacebookWrapper() {
        try {
            fb = new FacebookFactory().getInstance();
            fb.setOAuthAppId(appID, appSecret);
            fb.setOAuthPermissions(permissions);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static String getUserName(String facebookId, String userToken) {
        if(instance == null) {
            instance = new FacebookWrapper();
        }
        try {
            instance = new FacebookWrapper();
            fb.setOAuthAccessToken(new AccessToken(userToken, null));
            // System.out.println(fb.getName());
            return fb.getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Name not found";
    }

    public static String getFriends(String userToken) {
        if(instance == null) {
            instance = new FacebookWrapper();
        }
        List<String> friendList = new ArrayList<String>();
        try {
            fb.setOAuthAccessToken(new AccessToken(userToken, null));
            ResponseList<Friend> friends = fb.getFriends();
            for (Friend f : friends) {
                friendList.add(f.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return friendListToJson(friendList);
    }

    public static String friendListToJson(List<String> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    public static List<String> friendListFromJson(String str) {
        Gson gson = new Gson();
        return gson.fromJson(str, List.class);
    }
}
