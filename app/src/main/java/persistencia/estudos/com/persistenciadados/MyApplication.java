package persistencia.estudos.com.persistenciadados;

import android.app.Application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by ricardoogliari on 10/6/16.
 */

public class MyApplication extends Application {

    public static Realm realm;
    public static DatabaseReference myRef;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfig);
        realm = Realm.getDefaultInstance();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");
    }
}
