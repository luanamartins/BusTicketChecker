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
        contentValues.put(BusCardDAO.KEY_AMOUNT, cardDAO.getAmount());

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

    public void delete(String name)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(BusCardDAO.TABLE, BusCardDAO.KEY_NAME + "=?", new String[]{name});
        db.close();
    }

    public void update(BusCardDAO card)
    {

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BusCardDAO.KEY_NAME, card.getName());
        values.put(BusCardDAO.KEY_TYPE, card.getType());
        values.put(BusCardDAO.KEY_AMOUNT, card.getAmount());

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update("card", values, BusCardDAO.KEY_ID + "= ?", new String[]{String.valueOf(card.getId())});
        db.close(); // Closing busticketchecker.database connection
    }

    public ArrayList<HashMap<String, String>> getCardList()
    {
        SQLiteDatabase db = helper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                BusCardDAO.KEY_ID + "," +
                BusCardDAO.KEY_NAME + "," +
                BusCardDAO.KEY_TYPE + "," +
                BusCardDAO.KEY_AMOUNT +
                " FROM " + BusCardDAO.TABLE;

        ArrayList<HashMap<String, String>> studentList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst())
        {
            do
            {
                HashMap<String, String> cards = new HashMap<String, String>();
                cards.put(BusCardDAO.KEY_ID, cursor.getString(cursor.getColumnIndex(BusCardDAO.KEY_ID)));
                cards.put(BusCardDAO.KEY_NAME, cursor.getString(cursor.getColumnIndex(BusCardDAO.KEY_NAME)));
                cards.put(BusCardDAO.KEY_TYPE, cursor.getString(cursor.getColumnIndex(BusCardDAO.KEY_TYPE)));
                cards.put(BusCardDAO.KEY_AMOUNT, cursor.getString(cursor.getColumnIndex(BusCardDAO.KEY_AMOUNT)));
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
//        String query = "SELECT id, name, type FROM Card WHERE id=?";
        String selectQuery = "SELECT  " +
                BusCardDAO.KEY_ID + ", " +
                BusCardDAO.KEY_NAME + ", " +
                BusCardDAO.KEY_TYPE + ", " +
                BusCardDAO.KEY_AMOUNT +
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
                cardDAO.setAmount(cursor.getFloat(cursor.getColumnIndex(BusCardDAO.KEY_AMOUNT)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return cardDAO;
    }

}
