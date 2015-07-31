package com.example.administrador.myapplication.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.administrador.myapplication.model.persistence.SQLiteUserRepository;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable, Parcelable {

    private String userid;
    private String password;

    public User() {
        super();
    }
    public User(String userid, String password) {
        super();
        this.userid = userid;
        this.password = password;
    }
    public User(Parcel in) {
        super();
        readToParcel(in);
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid='" + userid + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userid != null ? !userid.equals(user.userid) : user.userid != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = userid != null ? userid.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userid == null ? "" : userid);
        dest.writeString(password == null ? "" : password);
    }

    public void readToParcel(Parcel in) {
        userid = in.readString();
        password = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public void save() {
        SQLiteUserRepository.getInstance().save(this);
    }

    public static List<User> getAll() {
        return SQLiteUserRepository.getInstance().getAll();
    }

    public void delete() {
        SQLiteUserRepository.getInstance().delete(this);
    }

    public static User getUser(String userid, String password) {
        return SQLiteUserRepository.getInstance().getUser(userid,password);
    }

    public static User registerUser(String userid, String password) {
        User user = new User(userid,password);
        return SQLiteUserRepository.getInstance().save(user) ? user : null;
    }
}
