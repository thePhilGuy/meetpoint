package models;

import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.*;

@Entity
public class User extends Model {

    @Id
    public Long id;

    public String name;
    public String facebookId;
    public boolean isLoggedIn;
    public List<String> friends = new ArrayList<String>();
    public List<Long> joinedSessions = new ArrayList<Long>();
    public List<Long> unjoinedSessions = new ArrayList<Long>();

    public static Finder<Long,User> find = new Finder<Long,User>(
            Long.class, User.class
    );
}
