package models;

import play.db.ebean.Model;
import javax.persistence.*;
import java.util.*;

@Entity
public class Session extends Model {

    @Id
    public Long id;

    public String name;
    public Long hostId;

    @ManyToMany
    public List<User> joinedUsers;

    @ManyToMany
    public List<User> unjoinedUsers;

    public static Finder<Long,Session> find = new Finder<Long,Session>(
            Long.class, Session.class
    );
}
