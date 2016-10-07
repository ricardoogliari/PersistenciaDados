package persistencia.estudos.com.persistenciadados.realm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import persistencia.estudos.com.persistenciadados.MyApplication;
import persistencia.estudos.com.persistenciadados.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static List<Pessoa> pessoasList = new ArrayList<>();

    private RealmResults<Pessoa> results;

    private RealmChangeListener callback = new RealmChangeListener() {
        public void onChange(Object element) {
            results = (RealmResults<Pessoa>) element;
            getData();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mAdapter = new PessoasAdapter(pessoasList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        results = MyApplication.realm.where(Pessoa.class).findAllAsync();
        results.addChangeListener(callback);

        getData();
    }

    private void getData() {
        pessoasList.clear();

        Iterator<Pessoa> pessoaIterator = results.iterator();;
        while (pessoaIterator.hasNext()){
            pessoasList.add(pessoaIterator.next());
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
