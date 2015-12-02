import controllers.Application;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;
import play.twirl.api.Html;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

public class ApplicationControllerTest {

    @Test
    public void loginTest() {
        Result result = Application.login();
        assertThat(status(result)).isEqualTo(OK);
    }

    @Test
    public void indexTest() {
        Result result = Application.index("", "");
        assertThat(status(result)).isEqualTo(OK);
    }
}
