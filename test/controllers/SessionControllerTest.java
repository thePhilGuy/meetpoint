package controllers;

import junit.framework.TestCase;
import models.Session;
import models.User;
import org.mockito.Mockito;
import play.mvc.Http;
import play.mvc.Result;
import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.BAD_REQUEST;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;
import static play.test.Helpers.status;
import static play.mvc.Http.Status.OK;
import static play.mvc.Http.Status.SEE_OTHER;

public class SessionControllerTest extends TestCase {

    Http.Context context = Mockito.mock(Http.Context.class);

    public void testCreateSession() throws Exception {
        Http.Context.current.set(context);
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
        Http.Context.current.set(context);
        running(fakeApplication(), new Runnable() {
            public void run() {
                User user = new User();
                user.name = "tester";
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
        Http.Context.current.set(context);
        running(fakeApplication(), new Runnable() {
            public void run() {
                Session session = new Session();
                session.save();
                User user = new User();
                user.name = "Di";
                user.save();
                Result result1 = SessionController.inviteUser(1L, "Di");
                assertThat(status(result1)).isEqualTo(OK);
                Result result2 = SessionController.inviteUser(1L, "Nobody");
                assertThat(status(result2)).isEqualTo(BAD_REQUEST);
            }
        });
    }

    public void testLeaveSession() throws Exception {
        Http.Context.current.set(context);
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

    public void testUpdateMeetType() throws Exception {
        Http.Context.current.set(context);
        running(fakeApplication(), new Runnable() {
            public void run() {
                Session session = new Session();
                session.save();
                Result result = SessionController.updateMeetType(session.id, "bar");
                assertThat(status(result)).isEqualTo(OK);
            }
        });
    }

    public void testGetMeetPoints() throws Exception {
        Http.Context.current.set(context);
        running(fakeApplication(), new Runnable() {
            public void run() {
                SessionController.Position p = new SessionController.Position(42.0, 42.0);
                Session session = new Session();
                User user = new User();
                user.latitude = 41.0;
                user.longitude = 41.0;
                user.save();
                session.joinedUsers.add(user);
                session.save();
                Result result = SessionController.getMeetPoints(1L);
                assertThat(status(result)).isEqualTo(OK);
            }
        });
    }

    public void testGetCurrentUsers() throws Exception {
        Http.Context.current.set(context);
        running(fakeApplication(), new Runnable() {
            public void run() {
                Session session = new Session();
                User user = new User();
                user.latitude = 41.0;
                user.longitude = 41.0;
                user.save();
                session.joinedUsers.add(user);
                session.save();
                Result result = SessionController.getCurrentUsers(1L);
                assertThat(status(result)).isEqualTo(OK);
            }
        });
    }
}