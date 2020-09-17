public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        int res = x - y;
        return res == 1 || res == -1;
    }
}