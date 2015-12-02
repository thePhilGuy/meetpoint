package models;

import junit.framework.TestCase;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;


public class SessionModelTest extends TestCase {

    public void testSession() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Session session = new Session();
                session.save();
                assertThat(session.id).isNotNull();
            }
        });
    }


}