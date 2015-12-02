import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;
import play.twirl.api.Html;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import models.Session;
import models.User;

public class TemplateTest {

    @Test
    public void renderLoginTemplate() {
        Html html = views.html.login.render();
        assertThat(contentAsString(html)).contains("Welcome to Meetpoint");
    }

    @Test
    public void renderMainTemplate() {
        String title = "Test";
        String linksText = "Links here\n";
        String contentText = "Content here\n";
        Html links = new Html(linksText);
        Html content = new Html(contentText);
        Html html = views.html.main.render(title,links, content);
        assertThat(contentAsString(html)).contains("<title>Meetpoint " + title + "</title>");
        assertThat(contentAsString(html)).contains(linksText);
        assertThat(contentAsString(html)).contains(contentText);
    }

    //@Test
    public void renderSessionTemplate() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Session s = new Session();
                s.id = 1L;
                s.name = "TestSession";
                s.hostId = 101L;
                User host = new User();
                host.name = "host user";
                host.id = 101L;
                host.friends = "";
                User uJoined1 = new User();
                uJoined1.name = "joined user 1";
                User uJoined2 = new User();
                uJoined2.name = "joined user 2";
                s.joinedUsers.add(uJoined1);
                s.joinedUsers.add(uJoined2);
                User uUnjoined1 = new User();
                uUnjoined1.name = "unjoined user 1";
                User uUnjoined2 = new User();
                uUnjoined2.name = "unjoined user 2";
                s.unjoinedUsers.add(uUnjoined1);
                s.unjoinedUsers.add(uUnjoined2);
                Html html = views.html.session.render(s, host, host);
                assertThat(contentAsString(html)).contains("You are viewing session <strong>" + s.name + "</strong> hosted by <strong>" + host.name + "</strong>");
                assertThat(contentAsString(html)).contains("<p>You should get your friends to use meetpoint.</p>");
                assertThat(contentAsString(html)).contains("<li class=\"list-group-item\">" + uJoined1.name + "</li>");
                assertThat(contentAsString(html)).contains("<li class=\"list-group-item\">" + uJoined2.name + "</li>");
                assertThat(contentAsString(html)).contains("<li class=\"list-group-item\">" + uUnjoined1.name + "</li>");
                assertThat(contentAsString(html)).contains("<li class=\"list-group-item\">" + uUnjoined2.name + "</li>");
            }
        });
    }

    //@Test
    public void renderUserTemplate() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                User u = new User();
                u.id = 1L;
                u.name = "test user";
                u.friends = "";
                Session sJoined = new Session();
                sJoined.id = 101L;
                sJoined.name = "joined session 1";
                Session sUnjoined = new Session();
                sUnjoined.id = 102L;
                sUnjoined.name = "unjoined session 1";
                u.joinedSessions.add(sJoined);
                u.unjoinedSessions.add(sUnjoined);
                Html html = views.html.user.render(u);
                assertThat(contentAsString(html)).contains("Signed in as <a href=\"/user/" + u.id + "\" class=\"navbar-link\">" + u.name + "</a></p>");
                assertThat(contentAsString(html)).contains("<a href=\"javascript:showSession(" + sJoined.id + ", " + u.id + ")\" class=\"list-group-item\">" + sJoined.name + "</a>");
                assertThat(contentAsString(html)).contains("<a class=\"list-group-item\" href=\"javascript:joinSession(" + sUnjoined.id + ", " + u.id + ");\">" + sUnjoined.name + "</a>");
            }
        });
    }
}
