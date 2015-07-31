package com.example.administrador.myapplication.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrador.myapplication.model.entities.User;
import com.example.administrador.myapplication.util.AppUtil;

import java.util.List;

public class SQLiteUserRepository implements UserRepository {

    private static class Singleton {
        private static SQLiteUserRepository INSTANCE = new SQLiteUserRepository();
    }

    private SQLiteUserRepository() {
        super();
    }

    public static UserRepository getInstance() {
        return SQLiteUserRepository.Singleton.INSTANCE;
    }

    @Override
    public boolean save(User user) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = UserContract.getContentValues(user);
        try {
            if (user.getUserid() == null) {
                db.insert(UserContract.TABLE, null, values);
            } else {
                String where = UserContract.USERID + " = ?";
                String[] args = {user.getUserid().toString()};
                db.insertOrThrow(UserContract.TABLE, null, values);
            }
        } catch(SQLException e) {
            return false;
        }
        db.close();
        helper.close();

        return true;
    }

    @Override
    public List<User> getAll() {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(UserContract.TABLE, UserContract.COLUMNS, null, null, null, null, UserContract.USERID);

        List<User> users = UserContract.bindList(cursor);

        db.close();
        helper.close();
        return users;
    }

    public User getUser(String userid, String password) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(UserContract.TABLE, UserContract.COLUMNS, UserContract.COLUMNS[0] + " = '" + userid.toString() + "' AND " + UserContract.COLUMNS[1] + " = '" + password.toString() + "'", null, null, null, UserContract.USERID);
        User user = UserContract.bind(cursor);

        return user;
    }

    @Override
    public void delete(User user) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();

        String where = UserContract.USERID + " = ?";
        String[] args = {user.getUserid().toString()};
        db.delete(UserContract.TABLE, where, args);

        db.close();
        helper.close();
    }

}
