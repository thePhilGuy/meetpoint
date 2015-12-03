import org.junit.*;
import play.mvc.*;
import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

public class RouterTest {

    @Test
    public void simpleCheck() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Result result = route(fakeRequest(GET, "/user/Kiki/hj/gh/gh/gh"));
                assertThat(result).isNull();
            }
        });
    }

}
