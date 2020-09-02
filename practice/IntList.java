public class IntList {
    public int first;
    public IntList rest;

    /* Initial the IntList */
    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    public int size(){
        if (rest == null){
            return 1;
        }
        return 1 + this.rest.size();
    }

    public int iterativeSize(){
        IntList p = this;
        int totalSize = 0;
        while (p != null){
            totalSize += 1;
            p = p.rest;
        }
        return totalSize;
    }

    public int get(int i){
        if (i == 0){
            return first;
        }
        return rest.get(i - 1);
    }
    public static IntList square(IntList L){
        if (L == null){
            return L;
        }
        IntList B = L.rest;
        IntList LSquared = new IntList(L.first * L.first, null);
        IntList C = LSquared;
        while (B != null){
            C.rest = new IntList(B.first * B.first, null);
            B = B.rest;
            C = C.rest;
        }
        return LSquared;
    }

    public static IntList squareDestructive(IntList L){
        if (L == null){
            return L;
        }
        else{
            L.first = L.first * L.first;
            squareDestructive(L.rest);
        }
        return L;
    }

    public static void main(String args[]){
        IntList L = new IntList(15, null);
        L = new IntList(10, L);
        L = new IntList(5, L);

        System.out.println(L.get(100));
    }


}