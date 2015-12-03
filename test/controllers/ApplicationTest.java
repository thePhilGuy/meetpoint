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
    private String token = "CAAMvyLljWPMBAADypzPHPs71IRShx9L1U3xgaH0omnKUgU7fuhmIbp6A2gI5nSvrdvggx5eDN2Ded5pj5Ip1S0ZAz5gUZCkCQyHZBc4cV2Mmk5mYDLvjh6mpANUuqCrSkjrIvwEjU6kQ6BSuGsWzAVitG5wBOSqoRrTH1b53mUB7tFRv2g1Klpsg0P7ADsprjVvhJUh2Sgq39oOdRK4";

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