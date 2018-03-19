import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Arrays;
import org.assertj.core.util.Lists;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Tree {
   static Map<String, String> map = new HashMap<>();
   public static void insertChild(String word, Node root) {
       char[] chars = word.toCharArray();
       for (int i =0;i< chars.length;i++) {
           int index = chars[i] - 'a';
           Node child = root.childNode[index];
           if (child == null) {
               root.childNode[index]= new Node();
           }
           if (i== chars.length -1) {
               root.childNode[index].end = true;
           }
           root = root.childNode[index];
       }
       int i = 0;
   }
   public static int search(Node root, String prefix, int d) {
        if (root.end) {
            map.put(prefix, "");
        }
        Node [] child = root.childNode;
        for (int i =0; i< child.length;i++) {
            if (child[i] != null) {
                char c = (char) (i + 'a');
                String temp = prefix + c;
                System.out.println(search(child[i], temp, d++)+";");
            }
        }
        return d;
   }

    public static void main(String[]args) {
        String a = "you,your,temp,youd";
        List<String> words = Lists.newArrayList(a.split(","));
        Node root = new Node();
        for (String word: words) {
            insertChild(word, root);
        }
        System.out.println(search(root, "", 1));
        int i = 0;
    }
}
 class Node {
     // 是否结束
     boolean end;
     Node[] childNode = new Node[26];

 }


