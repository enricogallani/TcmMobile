package com.example.front.db.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.front.db.DBContract;
import com.example.front.db.DBHelper;
import com.example.front.db.entity.Activity;
import com.example.front.db.entity.User;

import java.util.ArrayList;
import java.util.List;

public class ActivityHelper {

    private Context context;
    private SQLiteDatabase db;

    public ActivityHelper(Context context) {
        this.context = context;
    }

    public String[] columns(){
        return new String[]{DBContract.Activity._ID,
                DBContract.Activity.COLUMN_NAME,
                DBContract.Activity.COLUMN_USER};
    }

    public List<Activity> getAll() {
        List<Activity> activities = new ArrayList<>();

        db = new DBHelper(this.context).getWritableDatabase();
        Cursor c = db.query(DBContract.Activity.TABLE_NAME, columns(),
                null, null, null, null, DBContract.Activity._ID + " DESC");

        UserHelper userHelper = new UserHelper(context);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Activity activity = fillActivity(c);
            User user = userHelper.findById(activity.getUserId());
            activity.setUser(user);
            activities.add(activity);
            c.moveToNext();
        }
        c.close();
        db.close();

        return activities;
    }

    public Activity inserir(Activity activity){
        ContentValues cv = new ContentValues();
        cv.put(DBContract.Activity.COLUMN_NAME, activity.getName());
        cv.put(DBContract.Activity.COLUMN_USER, activity.getUserId());

        db = new DBHelper(this.context).getWritableDatabase();
        long id = db.insert(DBContract.Activity.TABLE_NAME, null, cv);
        db.close();

        activity.setId(id);
        return activity;
    }

    private Activity fillActivity(Cursor c) {
        Activity activity = new Activity();
        activity.setId(c.getLong(0));
        activity.setName(c.getString(1));
        activity.setUserId(c.getLong(2));
        return activity;
    }
}
