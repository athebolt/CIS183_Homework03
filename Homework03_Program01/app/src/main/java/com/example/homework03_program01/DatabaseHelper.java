package com.example.homework03_program01;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "Employees.db";
    private static final String TABLE_NAME = "Employees";
    public DatabaseHelper(Context context)
    {
        //creates database
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //6 attributes stored in Table: firstname, lastname, email, username, password, age
        //all attributes are TEXT and username is primary key

        db.execSQL("CREATE TABLE " + TABLE_NAME + " ( firstname TEXT, lastname TEXT, email TEXT, username TEXT PRIMARY KEY NOT NULL, password TEXT, age TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        //only used if db version is changed

        //deletes table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");

        //creates new table in replacement
        onCreate(db);
    }

    public boolean initializeDB()
    {
        //if there are no entries in the database, make some dummy entries
        if(numberOfRowsInTable() == 0)
        {
            //get a writeable database because we are writing to the database
            SQLiteDatabase db = this.getWritableDatabase();

            //dummy entries
            db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES('Timmy','Test','ttest@hotmail.com','ttest123','password','54');");
            db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES('Sammy','Sample','ssample@gmail.com','ssample321','password','32');");

            //always close
            db.close();

            return true;
        }
        else
        {
            return false;
        }
    }

    public int numberOfRowsInTable()
    {
        //get a readable instance of database
        SQLiteDatabase db = this.getReadableDatabase();

        //count the number of rows in the database and store the number
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);

        //close it
        db.close();

        //return number of rows
        return numRows;
    }

    @SuppressLint("Range")
    public ArrayList<User> getAllRows()
    {
        //used to store info that table returns 
        ArrayList<User> listUsers = new ArrayList<User>();
        
        //query to get all rows and attributes from the table
        //select * means all attributes
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " ORDER BY username;";

        //reading database so get readable version
        SQLiteDatabase db = this.getReadableDatabase();

        //cursor cycles through entries in db
        Cursor cursor = db.rawQuery(selectQuery, null);

        //variables to store attributes
        String fName;
        String lName;
        String email;
        String uName;
        String pass;
        String age;

        //if something is returned, then go to the top of the list
        if(cursor.moveToFirst())
        {
            do
            {
                //get info from entry in the list and store it in its corresponding variable
                fName = cursor.getString(cursor.getColumnIndex("firstname"));
                lName = cursor.getString(cursor.getColumnIndex("lastname"));
                email = cursor.getString(cursor.getColumnIndex("email"));
                uName = cursor.getString(cursor.getColumnIndex("username"));
                pass = cursor.getString(cursor.getColumnIndex("password"));
                age = cursor.getString(cursor.getColumnIndex("age"));

                //store info in a new user and add it to the list
                listUsers.add(new User(fName, lName, email, uName, pass, age));
            }
            while(cursor.moveToNext());

            //closed
            db.close();
        }

        //return the list of users
        return listUsers;
    }

    public void addNewEmployee(User u)
    {
        //writing to database so get writable
        SQLiteDatabase db = getWritableDatabase();

        //line of code should look as follows:
        //INSERT INTO TABLE_NAME VALUES('firstname','lastname','email','username',password','age');
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES('" + u.getfName() + "','" + u.getlName() + "','" + u.getEmail() + "','" + u.getuName() + "','" + u.getPass() + "','" + u.getAge() + "');");

        db.close();
    }

    @SuppressLint("Range")
    public ArrayList<String> getAllUsernames()
    {
        //used to store usernames that were returned from the db
        ArrayList<String> usernames = new ArrayList<String>();

        //used to get all usernames from the table
        String selectUsernames = "SELECT username FROM " + TABLE_NAME + " ORDER BY username;";

        //readable database to read all entries
        SQLiteDatabase db = getReadableDatabase();

        //execute query to selectUsernames, cursor cycling through each entry
        Cursor cursor = db.rawQuery(selectUsernames, null);

        //variable to store usernames in the entries
        String username;

        if(cursor.moveToFirst())
        {
            do
            {
                username = cursor.getString(cursor.getColumnIndex("username"));

                usernames.add(username);
            }
            while (cursor.moveToNext());
        }

        //c
        db.close();

        return usernames;
    }

    @SuppressLint("Range")
    public ArrayList<String> getAllFirstnames()
    {
        //used to store firstnames that were returned from the db
        ArrayList<String> firstnames = new ArrayList<String>();

        //used to get all firstnames from the table
        String selectUsernames = "SELECT firstname FROM " + TABLE_NAME + " ORDER BY username;";

        //readable database to read all entries
        SQLiteDatabase db = getReadableDatabase();

        //execute query to selectUsernames, cursor cycling through each entry
        Cursor cursor = db.rawQuery(selectUsernames, null);

        //variable to store usernames in the entries
        String firstname;

        if(cursor.moveToFirst())
        {
            do
            {
                firstname = cursor.getString(cursor.getColumnIndex("firstname"));

                firstnames.add(firstname);
            }
            while (cursor.moveToNext());
        }

        //c
        db.close();

        return firstnames;
    }

    public void deleteUser(String uName)
    {
        //get writeable instance of database
        SQLiteDatabase db = getWritableDatabase();

        //delete command
        //must delete off primary key
        //command should look like:
        //DELETE FROM TABLE_NAME WHERE username = 'uName';
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE username = '" + uName + "';");

        //close it up
        db.close();
    }

    public void updateUser(User u)
    {
        //we writing so writeable
        SQLiteDatabase db = getWritableDatabase();

        //update command looks like
        //UPDATE TABLE_NAME SET firstname = 'firstname' , lastname = 'lastname' , email = 'email' , password = 'password' , age = 'age' WHERE username = 'username';
        db.execSQL("UPDATE " + TABLE_NAME + " SET firstname = '" + u.getfName() + "' , lastname = '" + u.getlName() + "' , email = '" + u.getEmail() + "' , password = '" + u.getPass() + "' , age = '" + u.getAge() + "' WHERE username = '" + u.getuName() + "';");

        //closed database
        db.close();
    }
}
