import org.junit.Test;
import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;
import play.twirl.api.Html;
import utility.FacebookWrapper;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.List;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

public class FacebookWrapperTest {

    private String facebookId = "Id863985690389295";
    private String token = "CAAMvyLljWPMBADevYGuSieYJvN785xLlimJUCRfGsPcClbx7KvW51KVbLKXAMSZA1pwEwByCKYEjiuJJD8wbaEuQWEumFBl1lcrZB2EjdOBwEwzZAzez3fCgoEnm19k6YEt1OngoCHOivP0ZC14ooVnzpZCansjd2Wh9XoIFui6qUQqJbGUQBdo0h7XMOBzdLEfdbW7YVcQZDZD";

    @Test
    public void getUserNameTest() {
        assertThat(FacebookWrapper.getUserName(facebookId, token)).isEqualToIgnoringCase("Di  Ruan");
    }

    @Test
    public void getFriendsTest() {
        assertThat(FacebookWrapper.getFriends(token)).hasSize(59);
    }

    @Test
    public void friendListToJsonTest() {
        List<String> list = new ArrayList<String>();
        assertThat(FacebookWrapper.friendListToJson(list)).isEqualToIgnoringCase("[]");
    }

    @Test
    public void friendListFromJsonTest() {
        List<String> list = new ArrayList<String>();
        assertThat(FacebookWrapper.friendListFromJson("[]")).isEqualTo(list);
    }

}
