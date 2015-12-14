package controllers;

import junit.framework.TestCase;
import models.User;
import play.mvc.Result;
import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.mvc.Http.Status.SEE_OTHER;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;
import static play.test.Helpers.status;

public class ApplicationTest extends TestCase {

    private String facebookId = "863985690389295";
    private String token = "CAAMvyLljWPMBAJkfA5wLvH4Wwt3Kb187lHUScKsCefV5NuQ8d1MSeJKpzzIknhLMsXJWaQIl8qS0Ve8JzWenWT40iW0y70u9kXTG1rqnaJOUmtZA6WhbUcnZAuLL26PNOB9kjxQAiqis2SAtWOYE3W5j4mhjhwmoqYNBOcaOh0aYDpHMANdRes6HOIu9XPSfRbptYZCRAZDZD";

    public void testLogin() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Result result = Application.login();
                assertThat(status(result)).isEqualTo(OK);
            }
        });
    }

    public void testIndex() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Result result = Application.index(facebookId, token);
                assertThat(status(result)).isEqualTo(SEE_OTHER);
            }
        });
        running(fakeApplication(), new Runnable() {
            public void run() {
                User user = new User();
                user.facebookId = "1";
                user.userAccessToken = "";
                user.save();
                Result result = Application.index("1", "");
                assertThat(status(result)).isEqualTo(SEE_OTHER);
            }
        });
    }
}