package persistencia.estudos.com.persistenciadados.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ricardoogliari on 10/6/16.
 */

public class PessoaDBHelper extends SQLiteOpenHelper {

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PessoaContract.PessoaEntry.TABLE + " (" +
                    PessoaContract.PessoaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    PessoaContract.PessoaEntry.COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                    PessoaContract.PessoaEntry.COLUMN_PHONE_NUMBER + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PessoaContract.PessoaEntry.TABLE;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "People.db";

    public PessoaDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void inserePessoa(Pessoa pessoa){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(PessoaContract.PessoaEntry.COLUMN_NAME, pessoa.name);
        cv.put(PessoaContract.PessoaEntry.COLUMN_PHONE_NUMBER, pessoa.phonenumber);

        db.insert(PessoaContract.PessoaEntry.TABLE, null, cv);

        db.close();
    }

    public void atualizaPessoa(Pessoa pessoa){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(PessoaContract.PessoaEntry.COLUMN_NAME, pessoa.name);
        cv.put(PessoaContract.PessoaEntry.COLUMN_PHONE_NUMBER, pessoa.phonenumber);

        db.update(PessoaContract.PessoaEntry.TABLE, cv, null, null);

        db.close();
    }

    public List<Pessoa> getPessoas(){
        List<Pessoa> pessoas = new ArrayList<Pessoa>();

        SQLiteDatabase db = getReadableDatabase();
                                               //columns select args groupby having, orderby
        Cursor cursor = db.query(PessoaContract.PessoaEntry.TABLE, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            Pessoa pessoa = new Pessoa();
            pessoa.entryid = cursor.getInt(cursor.getColumnIndex(PessoaContract.PessoaEntry._ID));
            pessoa.name = cursor.getString(cursor.getColumnIndex(PessoaContract.PessoaEntry.COLUMN_NAME));
            pessoa.phonenumber = cursor.getString(cursor.getColumnIndex(PessoaContract.PessoaEntry.COLUMN_PHONE_NUMBER));
            pessoas.add(pessoa);
            Log.e("SQLITE", "pessoa.name: " + pessoa.name);
        }

        cursor.close();

        db.close();

        return pessoas;
    }

    public void deletaPessoa(int id){
        SQLiteDatabase db = getWritableDatabase();

        db.delete(PessoaContract.PessoaEntry.TABLE, null, null);//where whereargs

        db.close();
    }

}
