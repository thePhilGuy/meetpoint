package utility;
import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class FacebookWrapperTest extends TestCase {

    private String facebookId = "863985690389295";
    private String token = "CAAMvyLljWPMBAADypzPHPs71IRShx9L1U3xgaH0omnKUgU7fuhmIbp6A2gI5nSvrdvggx5eDN2Ded5pj5Ip1S0ZAz5gUZCkCQyHZBc4cV2Mmk5mYDLvjh6mpANUuqCrSkjrIvwEjU6kQ6BSuGsWzAVitG5wBOSqoRrTH1b53mUB7tFRv2g1Klpsg0P7ADsprjVvhJUh2Sgq39oOdRK4";

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