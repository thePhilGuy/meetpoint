package controllers;

import junit.framework.TestCase;
import models.Session;
import models.User;
import play.mvc.Result;
import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.mvc.Http.Status.SEE_OTHER;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;
import static play.test.Helpers.status;

public class UserControllerTest extends TestCase {

    private String facebookId = "863985690389295";
    private String token = "CAAMvyLljWPMBAJkfA5wLvH4Wwt3Kb187lHUScKsCefV5NuQ8d1MSeJKpzzIknhLMsXJWaQIl8qS0Ve8JzWenWT40iW0y70u9kXTG1rqnaJOUmtZA6WhbUcnZAuLL26PNOB9kjxQAiqis2SAtWOYE3W5j4mhjhwmoqYNBOcaOh0aYDpHMANdRes6HOIu9XPSfRbptYZCRAZDZD";

    public void testCreateUser() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Result result = UserController.createUser(facebookId, token);
                assertThat(status(result)).isEqualTo(SEE_OTHER);
            }
        });
    }

    public void testShowUser() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                User user = new User();
                user.facebookId = facebookId;
                user.userAccessToken = token;
                user.save();
                Result result = UserController.showUser(1L);
                assertThat(status(result)).isEqualTo(OK);
            }
        });
    }

    public void testJoinSession() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                User user = new User();
                user.facebookId = facebookId;
                user.userAccessToken = token;
                user.save();
                Session session = new Session();
                session.save();
                Result result = UserController.joinSession(1L, 1L);
                assertThat(status(result)).isEqualTo(SEE_OTHER);
            }
        });
    }

    public void testUpdateLocation() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                User user = new User();
                user.facebookId = facebookId;
                user.userAccessToken = token;
                user.save();
                Result result = UserController.updateLocation(1L, 33.0, 33.0);
                assertThat(status(result)).isEqualTo(OK);
            }
        });
    }
}