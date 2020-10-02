import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class MyTrieSet implements TrieSet61B{

    private Node root;
    private int n;

    private class Node {
        private char nodeChar;
        private boolean isKey;
        private Map<Character, Node> children;

        public Node(char nodeChar, boolean isKey) {
            this.nodeChar = nodeChar;
            children = new HashMap<>();
            this.isKey = isKey;
        }
    }

    public MyTrieSet() {
        root = new Node('0', false);
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean contains(String key) {
        if (key == null || key.length() == 0 || root == null) {
            return false;
        }
        Node current = root;
        Node next = null;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            next = current.children.get(c);
            if (next == null) {
                return false;
            }
            current = next;
        }
        return current.isKey;
    }

    @Override
    public void add(String key) {
        if (key == null || key.length() == 0 || root == null) {
            return;
        }
        Node current = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!current.children.containsKey(c)) {
                current.children.put(c, new Node(c, false));
            }
            current = current.children.get(c);
        }
        current.isKey = true;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        if (prefix == null || prefix.length() == 0 || root == null) {
            throw new IllegalArgumentException();
        }
        Node start = root;
        List<String> result = new ArrayList<>();
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            start = start.children.get(c);
        }

        if (start.isKey) {
            result.add(prefix);
        }

        for (Node child : start.children.values()) {
            if (child != null) {
                colHelp(prefix, result, child);
            }
        }
        return result;
    }

    private void colHelp(String prefix, List<String> result, Node node) {
        if (node.isKey) {
            result.add(prefix + node.nodeChar);
        }
        for (Node next : node.children.values()) {
            if (next != null) {
                colHelp(prefix + node.nodeChar, result, next);
            }
        }
    }

    @Override
    public String longestPrefixOf(String key) {
        int length = search(root, key, 0, 0);
        return key.substring(0, length);
    }

    private int search(Node node, String key, int d, int length) {
        if (node == null) return length;
        if (node.nodeChar != '0') {
            length = d;
        }
        if (d == key.length()) return length;
        char c = key.charAt(d);
        return search(node.children.get(c), key, d + 1, length);
    }

  /*  public static void main(String[] args) {
        MyTrieSet trie = new MyTrieSet();
        trie.add("h");
        trie.add("hi");
        trie.add("hello");
        trie.add("help");
        trie.add("zebra");
        trie.add("homonym");
        trie.add("homophone");
        trie.add("homosexual");

        System.out.println(trie.contains("hello")); // expect true
        System.out.println(trie.keysWithPrefix("h")); // expect [help, hello, hi, homophone, homosexual, homonym]
        System.out.println(trie.keysWithPrefix("homo")); // expect [homophone, homosexual, homonym]
        //System.out.println(trie.longestCommonPrefixOf("homophone")); // expect homo
        System.out.println(trie.longestPrefixOf("helpful")); // expect help
        System.out.println(trie.longestPrefixOf("homogeneous")); // expect homo
    }
*/
}
