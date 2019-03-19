package com.dbc.exert.analyze;

import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.SpeedTokenizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocalAnalyzer extends Analyzer {

    private static int getPre(byte[] bytes, byte b) {
        for (int i = 0; i < bytes.length; i++) {
            if (b == bytes[i]) {
                return i;
            }
        }
        return -1;
    }

    private static int getSuf(byte[] bytes, byte b) {
        for (int i = bytes.length - 1; i >= 0; i--) {
            if (b == bytes[i]) {
                return i;
            }
        }
        return -1;
    }

    @Override
    String parsingContext() {

        String htmlStr = readHtml();
        String regEx_script = "<script[^>]*?>[\\s\\S]*?</script>"; //定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?</style>"; //定义style的正则表达式
        String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); //过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); //过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); //过滤html标签

        return htmlStr.replaceAll("\\r|\\n|\\s", "");
    }

    @Override
    void createIndex(String context) {
        List<Term> segment = SpeedTokenizer.segment(context);
        for (Term term : segment) {
            System.out.println(term.word);
        }
    }

    @Override
    public void run() {
        doAnalyze();
    }

    private synchronized String readHtml() {
        try (RandomAccessFile raf = new RandomAccessFile(new File("D:\\doc_raw.txt"), "r")) {
            raf.seek(0);
            byte[] head = new byte[32];
            raf.read(head);
            int pre = getPre(head, (byte) '\t');
            int suf = getSuf(head, (byte) '\t');
            byte[] bytes = Arrays.copyOfRange(head, pre + 1, suf);
            raf.seek(suf + 1);
            int seed = byteToString(bytes);
            byte[] bs = new byte[seed];
            raf.read(bs);
            return new String(bs, 0, seed);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {

        TreeMap<String, String> treeMap = new TreeMap<String, String>() {
            {
                put("<style>", "</style>");
                put("<script>", "</script>");
                put("<option>", "</option>");
                put("</div>", "</div>");
            }
        };

        String text = "<div class=\" flex job-road\">\n" +
                "                            <div class=\"fl-img\"><img src=\"https://storage.dreambigcareer.com/dbc/websitnew/ui/img/img-qzl2.png\" alt=\"\"></div>\n" +
                "                            <div class=\"fr-txt flex1\">Leo, Deloitte Audit Intern, US</div>\n" +
                "                        </div>\n" +
                "                    </div>";
//        AhoCorasickDoubleArrayTrie<String> act = new AhoCorasickDoubleArrayTrie<>();
//        act.build(treeMap);
//        char[] chars = text.toCharArray();
//        act.parseText(chars, new AhoCorasickDoubleArrayTrie.IHit<String>() {
//            @Override
//            public void hit(int begin, int end, String value) {
//                System.out.printf("[%d:%d]=%s\n", begin, end, value);
//            }
//        });
        List<Term> segment = SpeedTokenizer.segment("中国人民解放了");
        for (Term term : segment) {
            System.out.println(term.word);
        }

    }

    private static int byteToString(byte[] byteArray) {
        StringBuilder result = new StringBuilder();
        char temp;
        int length = byteArray.length;
        for (int i = 0; i < length; i++) {
            temp = (char) byteArray[i];
            result.append(temp);
        }
        return Integer.valueOf(result.toString());
    }

}
