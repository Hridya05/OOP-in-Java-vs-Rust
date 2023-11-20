package Game;


public class Pair<T1, T2> {
    public T1 first;
    public T2 second;

    Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    public T2 getValue() {
        return second;
    }

    public T1 getKey() {
        return first;
    }

}
