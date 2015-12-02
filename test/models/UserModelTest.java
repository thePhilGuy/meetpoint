package models;

import junit.framework.TestCase;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;


public class UserModelTest extends TestCase {

    public void testCreateModel() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                User user = new User();
                user.save();
                assertThat(user.id).isNotNull();
            }
        });
    }
}