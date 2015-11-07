package models;

import play.db.ebean.Model;
import javax.persistence.*;
import java.util.*;

@Entity
public class User extends Model {

    @Id
    public Long id;

    public String name;
    public String facebookId;
    public String userAccessToken;
    public boolean isLoggedIn;
    public List<String> friends = new ArrayList<String>();

    @ManyToMany(mappedBy = "joinedUsers")
    public List<Session> joinedSessions;

    @ManyToMany(mappedBy = "unjoinedUsers")
    public List<Session> unjoinedSessions;

    public static Finder<Long,User> find = new Finder<Long,User>(
            Long.class, User.class
    );
}
