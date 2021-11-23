package com.exercise.phoneapplication.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="users")
public class User {

    public User() {
        this.userId = UUID.randomUUID();
    }

    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "preferred_phone_number")
    private String preferredPhoneNumber;

//    todo
//    @Column(name = "phones_owned")
//    @OneToMany(mappedBy="User") // https://www.baeldung.com/hibernate-one-to-many
//    private Set<Phone> phonesOwned;


    public UUID getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return new String(this.password.replaceAll(".", "*"));
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPreferredPhoneNumber() {
        return preferredPhoneNumber;
    }

    public void setPreferredPhoneNumber(String preferredPhoneNumber) {
        this.preferredPhoneNumber = preferredPhoneNumber;
    }

    @Override
    public String toString() {
        return "Details of user " + userId + ":\n" +
                "user name: '" + userName + "', password: '" + getPassword() + "', " +
                "emailAddress: '" + emailAddress + "', preferredPhoneNumber: '" + preferredPhoneNumber + "'";
    }
}
