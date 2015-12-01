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

    @Test
    public void getUserNameTest() {

    }

    @Test
    public void getFriendsTest() {

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
