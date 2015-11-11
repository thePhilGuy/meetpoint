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

    @ManyToMany
    @JoinTable(
            name = "session_user_joined",
            joinColumns = {@JoinColumn(name = "session_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}
    )
    public List<User> joinedUsers;

    @ManyToMany
    @JoinTable(
            name="session_user_unjoined",
            joinColumns = {@JoinColumn(name = "session_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}
    )
    public List<User> unjoinedUsers;

    public static Finder<Long,Session> find = new Finder<Long,Session>(
            Long.class, Session.class
    );

    public void printJoinedUsers() {
        System.out.println("Printing users in " + this.name + "'s joinedUsers:");
        for(User u : this.joinedUsers) {
            System.out.print(u.name + "; ");
        }
        System.out.println();
    }

    public void printunjoinedUsers() {
        System.out.println("Printing users in " + this.name + "'s unjoinedUsers:");
        for(User u : this.unjoinedUsers) {
            System.out.print(u.name + "; ");
        }
        System.out.println();
    }
}
