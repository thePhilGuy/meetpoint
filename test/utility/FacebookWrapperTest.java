package utility;
import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class FacebookWrapperTest extends TestCase {

    private String facebookId = "863985690389295";
    private String token = "CAAMvyLljWPMBAFIthUrs0X1ZCGR8qVAiQn5C3Vc7v5aSLcdFXwJvVxt5aYNnlv60eMCZAuRA1bj11GoEGzNbLmZASgGNp4Wz3bMsPXUbAKqGdfNNky3SMWbZABAcbK4curZCMx8RUyZAZBVGCrwzHLtCGIc4gKNIuD8wh6ZC7kxXdHF0ySUeqptAAiNVS8RzHHz4fuaKSCmFgQZDZD";

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