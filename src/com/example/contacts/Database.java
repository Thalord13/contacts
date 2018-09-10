package com.example.contacts;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class Database extends SQLiteOpenHelper {
	
	static String DB = "db_contact";
	static String CONTACT = "tbl_contact";
	
	public Database(Context context) {
		super(context, DB, null, 1);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String sql="CREATE TABLE "+CONTACT+"(id integer primary key autoincrement, number int, name varchar(25), image varchar(50))";
		db.execSQL(sql);
		
	}
	
	public long addContact(int number, String name, Uri image){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
			cv.put("con", number);
			cv.put("name", name);
			cv.put("image", image.toString());
		return db.insert(CONTACT, null, cv);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
