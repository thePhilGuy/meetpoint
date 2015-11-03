package models;

import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class User extends Model {

    @Id
    public Long id;

    public String name;
    public String facebookId;
    public boolean isLoggedIn;
    public List<String> friends;
    public List<Session> joinedSessions;
    public List<Session> unjoinedSessions;
}
