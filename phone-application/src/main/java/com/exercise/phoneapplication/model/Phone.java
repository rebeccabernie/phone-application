package com.exercise.phoneapplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="phones")
public class Phone {

    public Phone() {
        this.phoneId = UUID.randomUUID();
    }

    @Id
    @Column(name = "phone_id")
    private UUID phoneId;

    @Column(name = "phone_name")
    private String phoneName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "phone_model")
    private Enum phoneModel;

    @Column(name = "belongs_to_user")
    private UUID belongsToUser;

    public UUID getPhoneId() {
        return phoneId;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Enum getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = PhoneModel.valueOf(phoneModel);
    }

    public UUID getBelongsToUser() {
        return belongsToUser;
    }

    public void setBelongsToUser(UUID belongsToUser) {
        this.belongsToUser = belongsToUser;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "phoneId=" + phoneId +
                ", phoneName='" + phoneName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", phoneModel=" + phoneModel +
                ", belongsToUser=" + belongsToUser +
                '}';
    }
}