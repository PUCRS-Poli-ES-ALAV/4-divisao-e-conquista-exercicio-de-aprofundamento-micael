public class Par<U, V> {

    private U first;
    private V second;

    public Par(U first, V second) {

        this.first = first;
        this.second = second;
    }

    public U getFirst(){
        return first;
    }

    public V getSecond(){
        return second;
    }
}
   