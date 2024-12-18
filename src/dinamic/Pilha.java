package dinamic;

public class Pilha<T> {
    private No<T> topo;

    public void add(T elem){
        No<T> temp = new No<>(elem);

        temp.proximo = this.topo;
        this.topo = temp;
    }

    public T remove(){
        if (isEmpty()) {
            System.out.println("Pilha VAZIA");
            return null;
        }
        T temp = this.topo.dado;
        this.topo = this.topo.proximo;
        return temp;
    }

    public void showPilha(){
        No<T> temp = this.topo;
        System.out.print("[");
        while (temp != null){
            System.out.print(temp.dado);
            if (temp.proximo != null) System.out.print(", ");
            temp = temp.proximo;
        }
        System.out.println("]");
    }

    public boolean isEmpty(){ return this.topo == null; }

    public int size(){
        No<T> temp = this.topo;
        int i = 0;
        while (temp != null){
            i++;
            temp = temp.proximo;
        }
        return i;
    }
}
