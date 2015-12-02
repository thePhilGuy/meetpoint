package models;

import play.db.ebean.Model;
import utility.FacebookWrapper;
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
    public String friends;

    public Double latitude;
    public Double longitude;

    @ManyToMany(mappedBy = "joinedUsers")
    public List<Session> joinedSessions;

    @ManyToMany(mappedBy = "unjoinedUsers")
    public List<Session> unjoinedSessions;

    public static Finder<Long,User> find = new Finder<Long,User>(
            Long.class, User.class
    );

    public void updateFacebookToken(String accessToken) {
        System.out.println("In updateFacebookToken");
        System.out.println("accessToken: " + accessToken);
        System.out.println("userAccessToken: " + userAccessToken);
        if (!userAccessToken.equals(accessToken)) {
            System.out.println("Updating facebook access token.");
            userAccessToken = accessToken;
        }
    }

    public List<String> getFriends() {
        return FacebookWrapper.friendListFromJson(friends);
    }
}
