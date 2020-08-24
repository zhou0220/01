public class Exercise2 {
    public static int max(int[] m){
        int res = m[0];
        int i = 1;
        while (i < m.length){
            if (m[i] > res) res = m[i];
            i += 1;
        }
        return res;
    }
    public static int formax(int[] m){
        int res = m[0];
        for (int i = 0; i < m.length; i++){
            if (m[i] > res) res = m[i];
        }
        return res;
    }
    public static void main(String[] args){
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        System.out.println(max(numbers));
        System.out.println(formax(numbers));
    }
}