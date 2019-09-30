package be.jkin.kudosusuariosrest.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.*;


/*
@Entity
@Table(name = "kudos_users")
 */

public class User {

    @Id
    private ObjectId _id;
    private String nickname;
    private String firstName;
    private String lastName;
    private long totalKudos;
    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;


    public ObjectId getId() {
        return _id;
    }
    public void setId(ObjectId id) {
        this._id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void increaseKudos()
    {
        this.totalKudos++;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public long getTotalKudos() {
        return totalKudos;
    }
    public void setTotalKudos(long totalKudos) {
        this.totalKudos = totalKudos;
    }

    @CreatedDate
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @CreatedBy
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }


    @LastModifiedDate
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


    @LastModifiedBy
    public String getUpdatedby() {
        return updatedBy;
    }
    public void setUpdatedby(String updatedby) {
        this.updatedBy = updatedby;
    }

}
