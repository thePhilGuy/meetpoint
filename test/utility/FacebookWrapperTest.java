package utility;
import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class FacebookWrapperTest extends TestCase {

    private String token = "CAAMvyLljWPMBAA4725tqRsSx1iixN1rZAMmnl4MMGDQQZAbOCLtIoUQY0SUrvSlXyedNglc5GVeA9BghgGR3rf8xpVi1D2zsZCuRsrMZAhl7FboDer5bfF8SbW03jXcTFQGDTBD1nmDPrwXY9vkGqM96h8ZByGEjKZBcR8HcJ3JaGTLsb05xFrDYisIsE1q8lguTyeTbgz0wZDZD";

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