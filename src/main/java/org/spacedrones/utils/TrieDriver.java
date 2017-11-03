package org.spacedrones.utils;


public class TrieDriver {

  TrieDriver() {

    Trie trie = new Trie();
    trie.insert("work");

    trie.insert("hello::this::wont::work");
    trie.insert("hello::this");


    //System.out.println(trie.search("hello::this::wont::work"));
    System.out.println(trie.search("hello", true));
    System.out.println(trie.search("hello::this", true));
    System.out.println(trie.search("hello::this::wont", true));
    System.out.println(trie.search("hello::this::wont::work", true));
    System.out.println(trie.search("hello::this::wont::work", false));
    System.out.println(trie.search("hello::wont::work", true));

    //System.out.println(trie.searchNode("this::wont"));
  }

  public static void main(String[] args) {
    new TrieDriver();
  }

}
