/*
Given a 2D board and a list of words from the dictionary, find all words in the board.
Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].
Note:
You may assume that all inputs are consist of lowercase letters a-z.

You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?
If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. 
What kind of data structure could answer such query efficiently? Does a hash table work? Why or why not? How about a Trie? If you would like to learn how to implement a basic trie, please work on this problem: Implement Trie (Prefix Tree) first.

Backtracking, Trie
 */
import java.util.*;
public class LT212_Word_Search_II {
    public List<String> findWords(char[][] board, String[] words) {
        //可以对Array中的每一个单词进行查找，如果能够找到，则加入结果List中
        List<String> re=new ArrayList<String>();
        HashSet<String> result=new HashSet<String>();
        if(words==null || words.length==0) return re;
        if(board==null || board.length==0 || board[0].length==0) return re;

        Trie tr=new Trie();
        for(String word:words){
            tr.insert(word);
        }
        
        boolean[][] used=new boolean[board.length][board[0].length];
        for(int i=0; i<board.length; i++) {  
            for(int j=0; j<board[0].length; j++) {  
                search(board, used, tr, i, j, new StringBuilder(), result);  
            }  
        }  
       
        for(String hs:result)
          re.add(hs);
          
        return re;
    }
    
    
    //sb中记录深度过程中访问过的节点，然后便于前缀搜索
   public void search(char[][] board, boolean[][] used,Trie tr,int i,int j,StringBuilder sb,HashSet<String> re){
        if(i<0 || j<0 || i>=board.length || j>=board[0].length || used[i][j]) return;

        used[i][j]=true;
        sb.append(board[i][j]);
        String s=sb.toString();
        //只有存在这个单词前缀的时候，才需要进行深度下去，否则不需要深度下去了，因为没有意义，这里进行了剪枝
        if(tr.startsWith(s)){
            if(tr.search(s)) re.add(s);
            //从四个方面进行搜索
            search(board,used,tr,i-1,j,sb,re);
            search(board,used,tr,i+1,j,sb,re);
            search(board,used,tr,i,j-1,sb,re);
            search(board,used,tr,i,j+1,sb,re);
        }
        //回溯上去进行还原工作
        sb.deleteCharAt(sb.length()-1);
        used[i][j]=false;
   }
    
    
    class TrieNode {
        // Initialize your data structure here.
        
        public HashMap<Character, TrieNode> children = new HashMap<>();
        //end of word
        public boolean terminable; 
        //constructor
    
        public TrieNode() {
            children = new HashMap<Character, TrieNode>();
            terminable = false; 
        }
    }

    class Trie {
        private TrieNode root;
    
        public Trie() {
            root = new TrieNode();
        }
        // Inserts a word into the trie.
        public void insert(String word) {
            TrieNode node = root;
            
            for(int i=0;i<word.length();i++){
                //求字符地址，方便将该字符放入到26叉树中的哪一叉中
                if (!node.children.containsKey(word.charAt(i)))
                    node.children.put(word.charAt(i), new TrieNode());
                
                node = node.children.get(word.charAt(i));
            }
            
            node.terminable = true;
        }
        
        public boolean search(String word) {
            TrieNode node = root;
            for(int i = 0; i < word.length(); i++) {
                if(!(node.children.containsKey(word.charAt(i))))
                    return false;
                else
                    node = node.children.get(word.charAt(i));
            }
                
            return node.terminable;  // 即便该字符串在Trie路径中，也不能说明该单词已存在，因为它有可能是某个子串  
        }
        
        public boolean startsWith(String prefix) {
            TrieNode node = root;
            for(int i = 0; i < prefix.length(); i++) {
                if(!(node.children.containsKey(prefix.charAt(i))))
                    return false;
                else
                    node = node.children.get(prefix.charAt(i));
            }
            
            return true;
        }
    }
    
}
