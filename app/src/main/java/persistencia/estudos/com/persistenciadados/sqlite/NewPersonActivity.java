package persistencia.estudos.com.persistenciadados.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import persistencia.estudos.com.persistenciadados.R;
import persistencia.estudos.com.persistenciadados.sqlite.Pessoa;
import persistencia.estudos.com.persistenciadados.sqlite.PessoaDBHelper;

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
        PessoaDBHelper helper = new PessoaDBHelper(this);

        Pessoa pessoa = new Pessoa();
        pessoa.name = edtName.getText().toString();
        pessoa.phonenumber = edtPhone.getText().toString();
        helper.inserePessoa(pessoa);

        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }
}
