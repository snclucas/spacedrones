package org.spacedrones.utils;

import java.util.HashMap;
import java.util.Map;

class TrieNode {
  private String s;
  HashMap<String, TrieNode> children = new HashMap<>();
  boolean isLeaf;

  TrieNode() {}

  TrieNode(String s){
    this.s = s;
  }
}

public class Trie {
  private TrieNode root;

  public Trie() {
    root = new TrieNode();
  }

  // Inserts an id into the trie.
  public void insert(String entireId) {
    HashMap<String, TrieNode> children = root.children;

    String[] ids = entireId.split("::");

    for(int i=0; i<ids.length; i++){
      String id = ids[i];

      TrieNode t;
      if(children.containsKey(id)){
        t = children.get(id);
      }else{
        t = new TrieNode(id);
        children.put(id, t);
      }

      children = t.children;

      //set leaf node
      if(i==ids.length-1)
        t.isLeaf = true;
    }
  }

  // Returns if the word is in the trie.
  public boolean search(String id, boolean partial) {
    TrieNode t = searchNode(id);
    return t != null && !(t.isLeaf && !partial);
  }

  // Returns if there is any word in the trie
  // that starts with the given prefix.
  public boolean startsWith(String prefix) {
    if(searchNode(prefix) == null)
      return false;
    else
      return true;
  }

  public TrieNode searchNode(String str){
    Map<String, TrieNode> children = root.children;
    TrieNode t = null;

    String[] ids = str.contains("::")? str.split("::") : new String[]{str};

    for(int i=0; i<ids.length; i++){
      String c = ids[i];
      if(children.containsKey(c)){
        t = children.get(c);
        children = t.children;
      }else{
        return null;
      }
    }

    return t;
  }
}
