package utility;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.Friendlist;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;
import models.User;

public class FacebookWrapper {

    private static FacebookWrapper instance;
    private static Facebook fb;

    public static String appID = "896964080392435";
    public static String appSecret = "b26ecb19307ecc7b2383a5c644c05856";
    public static String permissions = "email";
    public static String accessToken = "";

    private FacebookWrapper() {
        try {
            fb = new FacebookFactory().getInstance();
            fb.setOAuthAppId(appID, appSecret);
            fb.setOAuthPermissions(permissions);
            fb.setOAuthAccessToken(new AccessToken(accessToken, null));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static User getUserInfo() {
        if(instance == null) {
            instance = new FacebookWrapper();
        }
        try {
            String name = fb.getName();
            String facebookId = fb.getId();
            ResponseList<Friendlist> list = fb.getFriendlists();
            User user = new User();
            user.name = name;
            user.facebookId = facebookId;
            return user;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
