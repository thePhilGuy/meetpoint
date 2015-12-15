package models;

import junit.framework.TestCase;
import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

public class UserModelTest extends TestCase {

    public void testCreateModel() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                User user = new User();
                user.facebookId = "";
                user.isLoggedIn = true;
                user.latitude = 42.1;
                user.longitude = 42.1;
                user.friends = "";
                user.getFriends();
                user.userAccessToken = "1";
                user.save();
                user.updateFacebookToken("");
                assertThat(user.id).isNotNull();
                assertThat(User.find.byId(1L)).isNotNull();
            }
        });
    }
}