package matc89.exercicio3;

import android.support.annotation.NonNull;

public class Tarefa implements Comparable{
    private String descricao;
    private int prioridade;

    public Tarefa(String descricao, int prioridade) {
        this.descricao = descricao;
        this.prioridade = prioridade;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getPrioridade() {
        return prioridade;
    }

    @Override
    public int compareTo(@NonNull Object o) {

        int compareage=((Tarefa)o).getPrioridade();
        /* For Ascending order*/
        return this.prioridade-compareage;
    }
}
