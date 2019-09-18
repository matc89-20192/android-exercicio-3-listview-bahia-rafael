package matc89.exercicio3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();
    private ArrayAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        atualizarListView();

        ActionBotaoRemover();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tarefas.remove(position);
                atualizarListView();
                ActionBotaoRemover();
            }
        });

    }


    public void adicionar(View view) {

        if (validacaoImput()) {

            EditText editTextDescricao = (EditText) findViewById(R.id.editDescricao);
            EditText editTextPrioridade = (EditText) findViewById(R.id.editPrioridade);

            tarefas.add(new Tarefa(editTextDescricao.getText().toString(), Integer.parseInt(editTextPrioridade.getText().toString())));

            atualizarListView();
        }

        ActionBotaoRemover();
    }

    private boolean validacaoImput() {
        try {
            EditText editTextPrioridade = (EditText) findViewById(R.id.editPrioridade);
            int prioridade = Integer.parseInt(editTextPrioridade.getText().toString());

            EditText editTextDescricao = (EditText) findViewById(R.id.editDescricao);
            String descricao = editTextDescricao.getText().toString();

            if (CheckPrioridade(prioridade) && buscaDescricao(descricao)) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean CheckPrioridade(int prioridade) {

        if(!(prioridade < 1 || prioridade > 10)) {
            return true;
        } else {
            Toast.makeText(getApplicationContext(),"A prioridade deve estar entre 1 e 10.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean buscaDescricao(String descricao) {

        boolean retorno = true;

        for (int i = 0; i < tarefas.size(); i++) {

            if(tarefas.get(i).getDescricao().equals(descricao)) {
                retorno = false;

                Toast.makeText(getApplicationContext(),"Tarefa já cadastrada.", Toast.LENGTH_SHORT).show();

                break;
            }
        }

        return retorno;
    }

    public void remover(View view) {

        tarefas.remove(0);

        atualizarListView();
    }

    private void atualizarListView(){

        Collections.sort(tarefas);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, tarefas) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(tarefas.get(position).getDescricao());
                text2.setText("Prioridade: " + String.valueOf(tarefas.get(position).getPrioridade()));
                return view;
            }
        };

        listView.setAdapter(adapter);

        ActionBotaoRemover();
    }

    private void ActionBotaoRemover(){

        Button botaoRemover = (Button) findViewById(R.id.buttonRemover);

        if (tarefas.size() == 0) {
            botaoRemover.setEnabled(false);
        } else {
            botaoRemover.setEnabled(true);
        }
    }
}
