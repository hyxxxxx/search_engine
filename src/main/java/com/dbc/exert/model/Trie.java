package com.dbc.exert.model;

import java.util.ArrayList;
import java.util.List;


public class Trie {

    private Vertex root;    //一个Trie树有一个根节点

    public static Trie trie = new Trie();

    //内部类
    protected class Vertex {    //节点类
        protected int words;
        protected int prefixes;
        protected Vertex[] edges;   //每个节点包含26个子节点(类型为自身)

        Vertex() {
            words = 0;
            prefixes = 0;
            edges = new Vertex[26];
            for (int i = 0; i < edges.length; i++) {
                edges[i] = null;
            }
        }
    }


    public Trie() {
        root = new Vertex();
    }


    /**
     * List all words in the Trie.
     */
    public List<String> listAllWords() {

        List<String> words = new ArrayList<>();
        Vertex[] edges = root.edges;

        for (int i = 0; i < edges.length; i++) {
            if (edges[i] != null) {
                String word = "" + (char) ('a' + i);
                depthFirstSearchWords(words, edges[i], word);
            }
        }
        return words;
    }

    public List<String> search(String word) {
        List<String> words = new ArrayList<>();
        Vertex[] edges = root.edges;
        char[] chars = word.toLowerCase().toCharArray();

        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            Vertex vertex = edges[index];
            if (vertex != null) {
                edges = vertex.edges;
            } else {
                return words;
            }
        }
        for (int i = 0; i < edges.length; i++) {
            if (edges[i] != null) {
                String w = word + (char) ('a' + i);
                depthFirstSearchWords(words, edges[i], w);
            }
        }

        return words;
    }


    /**
     * Depth First Search words in the Trie and add them to the List.
     */
    private void depthFirstSearchWords(List words, Vertex vertex, String wordSegment) {
        Vertex[] edges = vertex.edges;
        boolean hasChildren = false;
        for (int i = 0; i < edges.length; i++) {
            if (edges[i] != null) {
                hasChildren = true;
                String newWord = wordSegment + (char) ('a' + i);
                depthFirstSearchWords(words, edges[i], newWord);
            }
        }
        if (!hasChildren) {
            words.add(wordSegment);
        }
    }

    public int countPrefixes(String prefix) {
        return countPrefixes(root, prefix);
    }

    private int countPrefixes(Vertex vertex, String prefixSegment) {
        if (prefixSegment.length() == 0) { //reach the last character of the word
            return vertex.prefixes;
        }

        char c = prefixSegment.charAt(0);
        int index = c - 'a';
        if (vertex.edges[index] == null) { // the word does NOT exist
            return 0;
        } else {
            return countPrefixes(vertex.edges[index], prefixSegment.substring(1));
        }

    }

    public int countWords(String word) {
        return countWords(root, word);
    }

    private int countWords(Vertex vertex, String wordSegment) {
        if (wordSegment.length() == 0) { //reach the last character of the word
            return vertex.words;
        }

        char c = wordSegment.charAt(0);
        int index = c - 'a';
        if (vertex.edges[index] == null) { // the word does NOT exist
            return 0;
        } else {
            return countWords(vertex.edges[index], wordSegment.substring(1));

        }

    }


    /**
     * Add a word to the Trie.
     *
     * @param word The word to be added.
     */
    public void addWord(String word) {
        addWord(root, word);
    }


    /**
     * Add the word from the specified vertex.
     *
     * @param vertex The specified vertex.
     * @param word   The word to be added.
     */
    private void addWord(Vertex vertex, String word) {
        if (word.length() == 0) { //if all characters of the word has been added
            vertex.words++;
        } else {
            vertex.prefixes++;
            char c = word.charAt(0);
            c = Character.toLowerCase(c);
            int index = c - 'a';
            if (vertex.edges[index] == null) { //if the edge does NOT exist
                vertex.edges[index] = new Vertex();
            }

            addWord(vertex.edges[index], word.substring(1)); //go the the next character
        }
    }

}