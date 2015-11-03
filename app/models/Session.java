package models;

import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Session extends Model {

    @Id
    public Long id;

    public String name;
    public String hostId;
    public List<User> joinedUsers;
    public List<User> unJoinedUsers;
}
