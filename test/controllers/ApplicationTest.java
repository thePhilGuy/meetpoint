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
    private String token = "CAAMvyLljWPMBAFIthUrs0X1ZCGR8qVAiQn5C3Vc7v5aSLcdFXwJvVxt5aYNnlv60eMCZAuRA1bj11GoEGzNbLmZASgGNp4Wz3bMsPXUbAKqGdfNNky3SMWbZABAcbK4curZCMx8RUyZAZBVGCrwzHLtCGIc4gKNIuD8wh6ZC7kxXdHF0ySUeqptAAiNVS8RzHHz4fuaKSCmFgQZDZD";

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