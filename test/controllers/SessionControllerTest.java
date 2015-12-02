package controllers;

import junit.framework.TestCase;
import models.Session;
import models.User;
import play.mvc.Result;
import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;
import static play.test.Helpers.status;
import static play.mvc.Http.Status.OK;
import static play.mvc.Http.Status.SEE_OTHER;

public class SessionControllerTest extends TestCase {

    public void testCreateSession() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                User user = new User();
                user.save();
                Result result = SessionController.createSession(1L, "testSession");
                assertThat(status(result)).isEqualTo(SEE_OTHER);
            }
        });
    }

    public void testShowSession() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                User user = new User();
                user.save();
                Session session = new Session();
                session.joinedUsers.add(user);
                session.hostId = user.id;
                session.save();
                Result result = SessionController.showSession(1L, 1L);
                assertThat(status(result)).isEqualTo(OK);
            }
        });
    }

    public void testInviteUser() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Session session = new Session();
                session.save();
                User user = new User();
                user.name = "Di";
                user.save();
                Result result = SessionController.inviteUser(1L, "Di");
                assertThat(status(result)).isEqualTo(OK);
            }
        });
    }

    public void testLeaveSession() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                User user = new User();
                user.save();
                Session session = new Session();
                session.joinedUsers.add(user);
                session.hostId = user.id;
                session.save();
                Result result = SessionController.leaveSession(1L, 1L);
                assertThat(status(result)).isEqualTo(SEE_OTHER);
            }
        });
    }
}