package dinamic;

public class Lista<T> {
    private No<T> primeiro;

    public Lista() {
        this.primeiro = null;
    }

    public void add(T dado){
        No<T> novo = new No<>(dado);

        if (isEmpty()) {
            this.primeiro = novo;
            return;
        }

        No<T> temp = this.primeiro;
        while (temp.proximo != null){
            temp = temp.proximo;
        }
        temp.proximo = novo;
    }

    public void remove(int index){
        if (index < 0) return;
        if (isEmpty()) return;
        if (index >= this.size()) {
            System.out.println("Esse item não existe!");
            return;
        }

        if (index == 0) {
            this.primeiro = this.primeiro.proximo;
            return;
        }

        No<T> temp = this.primeiro;
        for (int i = 0; i <= index-2; i++){
            temp = temp.proximo;
        }

        temp.proximo = temp.proximo.proximo;
    }

    public void clearList(){
        this.primeiro = null;
    }

    public void showList(){
        No<T> temp = this.primeiro;
        int c = 0;
        while (temp != null){
            System.out.println(c + ":" + temp.dado + ", ");
            c++;
            temp = temp.proximo;
        }
        System.out.println();
    }

    public int find(T elem){
        int i = 0;
        No<T> temp = this.primeiro;
        while (temp.dado != elem){
            if (temp.proximo == null) return -1;
            temp = temp.proximo;
            i++;
        }
        return i;
    }

    public void addAt(int pos, T dado){
        No<T> temp = this.primeiro;
        No<T> novo = new No<>(dado);

        if (pos == 0){
            novo.proximo = this.primeiro;
            this.primeiro = novo;
            return;
        }

        for (int i = 0; i != pos-1; i++){
            temp = temp.proximo;
        }

        novo.proximo = temp.proximo;
        temp.proximo = novo;
    }

    public int size(){
        if (isEmpty()) return 0;
        int size = 1;
        No<T> temp = this.primeiro;
        while (temp.proximo != null){
            size++;
            temp = temp.proximo;
        }
        return size;
    }

    public T get(int index){
        No<T> temp = this.primeiro;

        for (int i = 0; i <= index-1; i++){
            temp = temp.proximo;
        }

        return temp.dado;
    }

    public boolean isEmpty(){
        return this.primeiro == null;
    }

    @Override
    public String toString() {
        String res = "[";
        No<T> temp = this.primeiro;
        while (temp != null){
            res += temp.dado + ", ";
            temp = temp.proximo;
        }
        res += "]";
        return res;
    }
}
