package com.example.administrador.myapplication.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.administrador.myapplication.model.entities.Client;
import com.example.administrador.myapplication.model.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserContract {

    public static final String TABLE = "user";
    public static final String USERID = "userid";
    public static final String PASSWORD = "password";

    public static final String[] COLUMNS = {USERID, PASSWORD};

    public static String getCreateSql() {
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append(USERID + " TEXT PRIMARY KEY, ");
        sql.append(PASSWORD + " TEXT");
        sql.append(" ); ");
        return sql.toString();
    }

    public static ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(UserContract.USERID, user.getUserid());
        values.put(UserContract.PASSWORD, user.getPassword());
        return values;
    }

    public static User bind(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            User user = new User();
            user.setUserid(cursor.getString(cursor.getColumnIndex(UserContract.USERID)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(UserContract.PASSWORD)));
            return user;
        }
        return null;
    }

    public static List<User> bindList(Cursor cursor) {
        final List<User> users = new ArrayList<User>();
        while (cursor.moveToNext()) {
            users.add(bind(cursor));
        }
        return users;
    }

}
