package utility;

import facebook4j.*;
import facebook4j.auth.AccessToken;
import models.User;

public class FacebookWrapper {

    private static FacebookWrapper instance;
    private static Facebook fb;

    public static String appID = "896964080392435";
    public static String appSecret = "b26ecb19307ecc7b2383a5c644c05856";
    public static String permissions = "email";
    // public static String accessToken = "CAAMvyLljWPMBAAES1uSodn2ODEYZCHmYCYIhSOWsAEq2ZBlwMI3ZCIyFJOcn88caE77hYrZAN5SMRvZACZA6kAuucotv4vUo4EMEorqgO0RVFHvrK9SqCKli06NSPoVpZAzaDDNDdmv1wljAgHvw5rZBGFWpAyktneu3SmNVCK17VvCJQ57SwEMUGpFIBWMCoOoj8T6ZALmDacgZDZD";

    private FacebookWrapper() {
        try {
            fb = new FacebookFactory().getInstance();
            fb.setOAuthAppId(appID, appSecret);
            fb.setOAuthPermissions(permissions);
            // fb.setOAuthAccessToken(new AccessToken(accessToken, null));
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
            System.out.println(list);
            User user = new User();
            user.name = name;
            user.facebookId = facebookId;
            return user;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getUserName(String facebookId, String userToken) {
        if(instance == null) {
            instance = new FacebookWrapper();
        }
        try {
            fb.setOAuthAccessToken(new AccessToken(userToken, null));
            return fb.getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Name not found";
    }

    public static void getFriends(String userToken) {
        if(instance == null) {
            instance = new FacebookWrapper();
        }
        try {
            fb.setOAuthAccessToken(new AccessToken(userToken, null));
            ResponseList<Friend> list = fb.getFriends();
            if (list.isEmpty()) System.out.println("Friend list is empty.");
            for (Friend f : list) {
                System.out.println("Friend: " + f.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
