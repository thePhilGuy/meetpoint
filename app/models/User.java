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
    public HashSet<String> friends = new HashSet<String>();
    public HashSet<Long> joinedSessions = new HashSet<Long>();
    public HashSet<Long> unjoinedSessions = new HashSet<Long>();

    public static Finder<Long,User> find = new Finder<Long,User>(
            Long.class, User.class
    );
}
