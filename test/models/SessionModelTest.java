package models;

import junit.framework.TestCase;

import java.util.ArrayList;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

public class SessionModelTest extends TestCase {

    public void testSession() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Session session = new Session();
                session.name = "";
                session.hostId = 1L;
                session.meetType = "bar";
                session.joinedUsers = new ArrayList<User>();
                session.unjoinedUsers = new ArrayList<User>();
                session.save();
                assertThat(session.id).isNotNull();
                assertThat(Session.find.byId(1L)).isNotNull();
            }
        });
    }


}