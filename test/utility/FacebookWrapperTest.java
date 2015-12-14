package utility;
import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class FacebookWrapperTest extends TestCase {

    private String facebookId = "863985690389295";
    private String token = "CAAMvyLljWPMBAJkfA5wLvH4Wwt3Kb187lHUScKsCefV5NuQ8d1MSeJKpzzIknhLMsXJWaQIl8qS0Ve8JzWenWT40iW0y70u9kXTG1rqnaJOUmtZA6WhbUcnZAuLL26PNOB9kjxQAiqis2SAtWOYE3W5j4mhjhwmoqYNBOcaOh0aYDpHMANdRes6HOIu9XPSfRbptYZCRAZDZD";

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