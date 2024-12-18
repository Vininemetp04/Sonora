package dinamic;

public class Fila<T> {
    private No<T> primeiro;
    private No<T> ultimo;

    public void enfileirar(T dado){
        No<T> temp = new No<>(dado);

        if (isEmpty()){
            this.primeiro = temp;
            this.primeiro.proximo = this.ultimo;
        } else {
            this.ultimo.proximo = temp;
        }

        this.ultimo = temp;
    }

    public T getPrimeiro(){
        if (this.primeiro == null) return null;
        return this.primeiro.dado;
    }

    public T desenfileirar(){
        if (isEmpty()){
            System.out.println("Lista vazia!");
            return null;
        }
        T a = this.primeiro.dado;
        this.primeiro = this.primeiro.proximo;
        return a;
    }

    public void mostrarFila(){
        No<T> temp = this.primeiro;
        int i = 1;
        while (temp != null){
            System.out.println(i + "º " + temp.dado + " | ");
            i++;
            temp = temp.proximo;
        }
        System.out.println();
    }

    public boolean isEmpty(){
        return this.primeiro == null;
    }

    public int size(){
        No<T> temp = this.primeiro;
        int i = 0;
        while (temp != null){
            i++;
            temp = temp.proximo;
        }
        return i;
    }
}
