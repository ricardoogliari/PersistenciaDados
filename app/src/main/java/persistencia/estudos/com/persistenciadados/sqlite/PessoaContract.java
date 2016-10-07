package persistencia.estudos.com.persistenciadados.sqlite;

import android.provider.BaseColumns;

/**
 * Created by ricardoogliari on 10/6/16.
 */

public class PessoaContract {

    public PessoaContract() {}

    public static abstract class PessoaEntry implements BaseColumns {
        public static final String TABLE = "person";
        public static final String COLUMN_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PHONE_NUMBER = "phonenumber";
    }
}
