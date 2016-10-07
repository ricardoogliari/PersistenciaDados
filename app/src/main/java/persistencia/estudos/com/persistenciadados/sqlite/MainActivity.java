package persistencia.estudos.com.persistenciadados.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import persistencia.estudos.com.persistenciadados.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Pessoa> pessoasList = new ArrayList<>();

    private PessoaDBHelper helperDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helperDB = new PessoaDBHelper(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mAdapter = new PessoasAdapter(pessoasList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        getData();
    }

    private void getData() {
        /*Pessoa ricardo = new Pessoa();
        ricardo.name = "Ricardo Ogliari";
        ricardo.phonenumber = "54-81212042";

        Pessoa jana = new Pessoa();
        jana.name = "Janaina Antunes";
        jana.phonenumber = "54-99877665";

        pessoasList.add(ricardo);
        pessoasList.add(jana);*/

        pessoasList.clear();
        for (Pessoa pessoa : helperDB.getPessoas()){
            pessoasList.add(pessoa);
        }

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, NewPersonActivity.class);
        startActivityForResult(intent, 10);

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10){
            if (resultCode == RESULT_OK){
                getData();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
