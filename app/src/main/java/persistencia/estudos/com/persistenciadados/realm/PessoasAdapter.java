package persistencia.estudos.com.persistenciadados.realm;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import persistencia.estudos.com.persistenciadados.R;
import persistencia.estudos.com.persistenciadados.realm.Pessoa;

/**
 * Created by ricardoogliari on 10/6/16.
 */

public class PessoasAdapter extends RecyclerView.Adapter<PessoasAdapter.MyViewHolder> {

    private List<Pessoa> pessoasList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, phone;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            phone = (TextView) view.findViewById(R.id.phone);
        }
    }


    public PessoasAdapter(List<Pessoa> pessoasList) {
        this.pessoasList = pessoasList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pessoa, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Pessoa pessoa = pessoasList.get(position);
        holder.name.setText(pessoa.name + " - ");
        holder.phone.setText(pessoa.phonenumber);
    }

    @Override
    public int getItemCount() {
        return pessoasList.size();
    }
}