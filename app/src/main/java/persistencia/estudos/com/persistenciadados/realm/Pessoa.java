package persistencia.estudos.com.persistenciadados.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ricardoogliari on 10/6/16.
 */

public class Pessoa extends RealmObject {

    @PrimaryKey
    public String name;

    public String phonenumber;

}
