package persistencia.estudos.com.persistenciadados.realm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import io.realm.Realm;
import persistencia.estudos.com.persistenciadados.MyApplication;
import persistencia.estudos.com.persistenciadados.R;

public class NewPersonActivity extends AppCompatActivity {

    private EditText edtName;
    private EditText edtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_person);

        edtName = (EditText) findViewById(R.id.edtName);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
    }

    public void salvar(View view){
        MyApplication.realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Pessoa pessoa = new Pessoa();
                pessoa.name = edtName.getText().toString();
                pessoa.phonenumber = edtPhone.getText().toString();
                bgRealm.copyToRealmOrUpdate(pessoa);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.e("REALM", "onSucessssss");
                // Transaction was a success.
                setResult(RESULT_OK);
                finish();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e("REALM", "onErrrrorrrrrrr: " + error.getLocalizedMessage());
                // Transaction failed and was automatically canceled.
            }
        });
    }
}
