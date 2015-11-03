package models;

import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.*;

@Entity
public class Session extends Model {

    @Id
    public Long id;

    public String name;
    public Long hostId;
    public HashSet<Long> joinedUsers = new HashSet<Long>();
    public HashSet<Long> unJoinedUsers = new HashSet<Long>();

    public static Finder<Long,Session> find = new Finder<Long,Session>(
            Long.class, Session.class
    );
}
