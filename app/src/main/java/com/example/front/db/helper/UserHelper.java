package com.example.front.db.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.front.db.DBContract;
import com.example.front.db.DBHelper;
import com.example.front.db.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserHelper {

    private Context context;
    private SQLiteDatabase db;

    public UserHelper(Context context) {
        this.context = context;
        db = new DBHelper(this.context).getWritableDatabase();
    }

    public String[] columns(){
        return new String[]{"_id", "name", "email", "office", "password"};
    }

    public User login(String email, String password) {
        List<User> users = new ArrayList<>();

        String[] args = new String[]{email, password};
        Cursor c = db.query(DBContract.User.TABLE_NAME, columns(),
                "email = ? & password = ?", args, null, null, "name");

        c.moveToFirst();
        while (!c.isAfterLast()) {
            User user = fillUser(c);
            users.add(user);
            c.moveToNext();
        }
        c.close();
        db.close();
        if (users.size() > 0)
            return users.get(0);

        return null;
    }

    public User findByEmail(String email) {
        List<User> users = new ArrayList<>();

        String[] args = new String[]{email};
        Cursor c = db.query(DBContract.User.TABLE_NAME, columns(),
                "email = ?", args, null, null, "name");

        c.moveToFirst();
        while (!c.isAfterLast()) {
            User user = fillUser(c);
            users.add(user);
            c.moveToNext();
        }
        c.close();
        db.close();
        if (users.size() > 0)
            return users.get(0);

        return null;
    }

    public User inserir(User user){
        ContentValues cv = new ContentValues();
        cv.put("name", user.getName());
        cv.put("email", user.getMail());
        cv.put("office", user.getOffice());
        cv.put("password", user.getPassword());

        long id = db.insert(DBContract.User.TABLE_NAME, null, cv);
        db.close();

        user.setId(id);
        return user;
    }

    private User fillUser(Cursor c) {
        User user = new User();
        user.setId(c.getLong(0));
        user.setMail(c.getString(1));
        user.setName(c.getString(2));
        user.setOffice(c.getString(3));
        return user;
    }
}
