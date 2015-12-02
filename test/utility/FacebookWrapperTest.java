package utility;
import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class FacebookWrapperTest extends TestCase {

    private String facebookId = "863985690389295";
    private String token = "CAAMvyLljWPMBAIe24Acww4Jo0ZCradz5RZBdEYWSNCe4wN7p81sFuFET7M9uyKWMwe9t7n1Wu0se13EntAeq8Hp9CmWQZBs99KKfjwhzWvj0G9fZCq6tYsCPpEUASjtmSoZCwBQ9XKoThZB9NIMbngMe2D39ymffCKIYYOqR2Jw3ZB8oXYW2nau6nj038EEMPLWb0xKPQyHyAZDZD";

    public void testGetUserName() throws Exception {
        assertThat(FacebookWrapper.getUserName(facebookId, token)).isEqualToIgnoringCase("Di  Ruan");
    }

    public void testGetFriends() throws Exception {
        assertThat(FacebookWrapper.getFriends(token)).hasSize(59);
    }

    public void testFriendListToJson() throws Exception {
        List<String> list = new ArrayList<String>();
        assertThat(FacebookWrapper.friendListToJson(list)).isEqualToIgnoringCase("[]");
    }

    public void testFriendListFromJson() throws Exception {
        List<String> list = new ArrayList<String>();
        assertThat(FacebookWrapper.friendListFromJson("[]")).isEqualTo(list);
    }
}