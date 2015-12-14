package models;

import play.db.ebean.Model;
import javax.persistence.*;
import java.util.*;

@Entity
public class Session extends Model {

    @Id
    @Column(name = "id")
    public Long id;

    public String name;
    public Long hostId;
    public String meetType;

    @ManyToMany
    @JoinTable(
            name = "session_user_joined",
            joinColumns = {@JoinColumn(name = "session_id_joined", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id_joined", referencedColumnName = "id")}
    )
    public List<User> joinedUsers;

    @ManyToMany
    @JoinTable(
            name="session_user_unjoined",
            joinColumns = {@JoinColumn(name = "session_id_unjoined", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id_unjoined", referencedColumnName = "id")}
    )
    public List<User> unjoinedUsers;

    public static Finder<Long,Session> find = new Finder<Long,Session>(
            Long.class, Session.class
    );
}
