package persistencia.estudos.com.persistenciadados.firebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

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
        Pessoa pessoa = new Pessoa();
        pessoa.name = edtName.getText().toString();
        pessoa.phone = edtPhone.getText().toString();

        MyApplication.myRef.child(pessoa.name).setValue(pessoa, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null){
                    Log.e("FIREBASE", "msg error: " + databaseError.getMessage());
                } else {
                    finish();
                }
            }
        });
    }
}
