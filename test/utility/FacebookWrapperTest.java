package utility;
import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class FacebookWrapperTest extends TestCase {

    private String token = "CAAMvyLljWPMBAJaHaghLcHDSHJBY19mAZCwF2UuQjGJKYrgGBuNeUgjpG6hIlQuU2vsDM5IkdwQMXZBHqoPc3VdpHTrLQ1uFRsUBJU4C3UL2q3RzP3wUEv7yzEJ0sEKuTSDTusAq3Mjp2yK07HTIIw1BO73oVn8k8XvUZBsY5G9cyxo2XxfMMfACnZAg12XNSeuHAly4FQZDZD";

    public void testGetUserName() throws Exception {
        assertThat(FacebookWrapper.getUserName(token)).isEqualToIgnoringCase("Di  Ruan");
    }

    public void testGetFriends() throws Exception {
        assertThat(FacebookWrapper.getFriends(token)).hasSize(78);
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