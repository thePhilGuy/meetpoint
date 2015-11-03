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
    public List<Long> joinedUsers = new ArrayList<Long>();
    public List<Long> unJoinedUsers = new ArrayList<Long>();

    public static Finder<Long,Session> find = new Finder<Long,Session>(
            Long.class, Session.class
    );
}
