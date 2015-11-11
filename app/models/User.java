package models;

import play.db.ebean.Model;
import javax.persistence.*;
import java.util.*;

@Entity
public class User extends Model {

    @Id
    @Column(name = "id")
    public Long id;

    public String name;
    public String facebookId;
    public String userAccessToken;
    public boolean isLoggedIn;
    public List<String> friends;

    @ManyToMany(mappedBy = "joinedUsers")
    public List<Session> joinedSessions;

    @ManyToMany(mappedBy = "unjoinedUsers")
    public List<Session> unjoinedSessions;

    public static Finder<Long,User> find = new Finder<Long,User>(
            Long.class, User.class
    );

    public void printJoinedSessions() {
        System.out.println("Printing sessions in " + this.name + "'s joinedSessions:");
        for(Session s : this.joinedSessions) {
            System.out.print(s.name + "; ");
        }
        System.out.println();
    }

    public void printUnjoinedSessions() {
        System.out.println("Printing sessions in " + this.name + "'s unjoinedSessions:");
        for(Session s : this.unjoinedSessions) {
            System.out.print(s.name + "; ");
        }
        System.out.println();
    }
}
