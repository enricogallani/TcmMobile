package com.example.front.db.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.front.db.DBContract;
import com.example.front.db.DBHelper;
import com.example.front.db.entity.Client;
import com.example.front.db.entity.User;

import java.util.ArrayList;
import java.util.List;

public class ClientHelper {

    private Context context;
    private SQLiteDatabase db;

    public ClientHelper(Context context) {
        this.context = context;
        db = new DBHelper(this.context).getWritableDatabase();
    }

    public String[] columns(){
        return new String[]{
                DBContract.Client._ID,
                DBContract.Client.COLUMN_NAME,
                DBContract.Client.COLUMN_EMAIL,
                DBContract.Client.COLUMN_CNPJ,
                DBContract.Client.COLUMN_PHONE,
                DBContract.Client.COLUMN_PAYMENT_METHOD,
                DBContract.Client.COLUMN_LOCATION
        };
    }

    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();

        Cursor c = db.query(DBContract.Client.TABLE_NAME, columns(),
                null, null, null, null, DBContract.Client._ID + " DESC");

        c.moveToFirst();
        while (!c.isAfterLast()) {
            Client client = fillClient(c);
            clients.add(client);
            c.moveToNext();
        }
        c.close();
        db.close();

        return clients;
     }

    public Client inserir(Client client){
        ContentValues cv = new ContentValues();
        cv.put(DBContract.Client.COLUMN_NAME, client.getName());
        cv.put(DBContract.Client.COLUMN_EMAIL, client.getMail());
        cv.put(DBContract.Client.COLUMN_CNPJ, client.getCnpj());
        cv.put(DBContract.Client.COLUMN_PHONE, client.getPhone());
        cv.put(DBContract.Client.COLUMN_PAYMENT_METHOD, client.getPaymentMethod());
        cv.put(DBContract.Client.COLUMN_LOCATION, client.getLocation());

        long id = db.insert(DBContract.Client.TABLE_NAME, null, cv);
        db.close();

        client.setId(id);
        return client;
    }

    private Client fillClient(Cursor c) {
        Client client = new Client();
        client.setId(c.getLong(0));
        client.setName(c.getString(1));
        client.setMail(c.getString(2));
        client.setCnpj(c.getString(3));
        client.setPhone(c.getString(4));
        client.setPaymentMethod(c.getString(5));
        client.setLocation(c.getString(6));

        return client;
    }
}
