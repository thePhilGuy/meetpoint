package controllers;

import junit.framework.TestCase;
import play.mvc.Result;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.status;

public class ApplicationTest extends TestCase {

    public void testLogin() throws Exception {
        Result result = Application.login();
        assertThat(status(result)).isEqualTo(OK);
    }

    public void testIndex() throws Exception {

    }
}