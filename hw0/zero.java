public class zero {
    public static void DrawTriangle(int N){
        int row = 0;
        while (row < N) {
            int col = 0;
            while (col <= row){
                System.out.print("*");
                col = col + 1;
            }
            System.out.println(" ");
            row = row + 1;
        }
    }
    public static void main(String[] args){
        zero.DrawTriangle(10);
    }
}