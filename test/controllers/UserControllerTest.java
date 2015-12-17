package controllers;

import junit.framework.TestCase;
import models.Session;
import models.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.Mockito;
import play.mvc.Http;
import play.mvc.Result;
import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.mvc.Http.Status.SEE_OTHER;
import static play.test.Helpers.*;

public class UserControllerTest extends TestCase {

    private String facebookId = "863985690389295";
    private String token = "CAAMvyLljWPMBAJaHaghLcHDSHJBY19mAZCwF2UuQjGJKYrgGBuNeUgjpG6hIlQuU2vsDM5IkdwQMXZBHqoPc3VdpHTrLQ1uFRsUBJU4C3UL2q3RzP3wUEv7yzEJ0sEKuTSDTusAq3Mjp2yK07HTIIw1BO73oVn8k8XvUZBsY5G9cyxo2XxfMMfACnZAg12XNSeuHAly4FQZDZD";
    Http.Context context = Mockito.mock(Http.Context.class);

    public void testCreateUser() {
        Http.Context.current.set(context);
        running(fakeApplication(), new Runnable() {
            public void run() {
                Result result = UserController.createUser(facebookId, token);
                assertThat(status(result)).isEqualTo(SEE_OTHER);
            }
        });
    }

    public void testShowUser() {
        Http.Context.current.set(context);
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
        Http.Context.current.set(context);
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
        Http.Context.current.set(context);
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