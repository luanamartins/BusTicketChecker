package busticketchecker.database.SQLite.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

import busticketchecker.database.SQLite.DBHelper;
import busticketchecker.database.dao.BusCardDAO;

public class CardRepo
{
    private DBHelper helper;

    public CardRepo(Context context)
    {
        helper = new DBHelper(context);
    }

    public int insert(BusCardDAO cardDAO)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BusCardDAO.KEY_NAME, cardDAO.getName());
        contentValues.put(BusCardDAO.KEY_TYPE, cardDAO.getType());

        long id = db.insert(BusCardDAO.TABLE, null, contentValues);
        db.close();

        return (int) id;
    }

    public void delete(int id)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string

        db.delete(BusCardDAO.TABLE, BusCardDAO.KEY_ID + "= ?", new String[]{String.valueOf(id)});

        db.close();
    }

    public void update(BusCardDAO card)
    {

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BusCardDAO.KEY_NAME, card.getName());
        values.put(BusCardDAO.KEY_TYPE, card.getType());

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update("card", values, BusCardDAO.KEY_ID + "= ?", new String[]{String.valueOf(card.getId())});
        db.close(); // Closing busticketchecker.database connection
    }

    public ArrayList<HashMap<String, String>> getCardList()
    {
        //Open connection to read only
        SQLiteDatabase db = helper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                BusCardDAO.KEY_ID + "," +
                BusCardDAO.KEY_NAME + "," +
                BusCardDAO.KEY_TYPE +
                " FROM " + BusCardDAO.TABLE;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> studentList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst())
        {
            do
            {
                HashMap<String, String> cards = new HashMap<String, String>();
                cards.put(BusCardDAO.KEY_ID, cursor.getString(cursor.getColumnIndex(BusCardDAO.KEY_ID)));
                cards.put(BusCardDAO.KEY_NAME, cursor.getString(cursor.getColumnIndex(BusCardDAO.KEY_NAME)));
                cards.put(BusCardDAO.KEY_TYPE, cursor.getString(cursor.getColumnIndex(BusCardDAO.KEY_TYPE)));
                studentList.add(cards);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return studentList;

    }

    public BusCardDAO getCardById(int id)
    {
        SQLiteDatabase db = helper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                BusCardDAO.KEY_ID + "," +
                BusCardDAO.KEY_NAME + "," +
                BusCardDAO.KEY_TYPE +
                " FROM " + BusCardDAO.TABLE + " WHERE " +
                BusCardDAO.KEY_ID + "=?";
        // It's a good practice to use parameter ?, instead of concatenate string

        int iCount = 0;
        BusCardDAO cardDAO = new BusCardDAO();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst())
        {
            do
            {
                cardDAO.setId(cursor.getInt(cursor.getColumnIndex(BusCardDAO.KEY_ID)));
                cardDAO.setName(cursor.getString(cursor.getColumnIndex(BusCardDAO.KEY_NAME)));
                cardDAO.setType(cursor.getString(cursor.getColumnIndex(BusCardDAO.KEY_TYPE)));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return cardDAO;
    }

}
