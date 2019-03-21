package com.dbc.exert.analyze;

import com.dbc.exert.FilePath;
import com.dbc.exert.collect.Collector;
import com.dbc.exert.model.IDProvider;
import com.dbc.exert.model.Link;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.SpeedTokenizer;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocalAnalyzer extends Analyzer {

    private String linkId;

    @Override
    String readHtml() throws IOException {

        Link link = Collector.linkEntries.poll();
        String fileName = link.getFileName();
        linkId = link.getId();
        int webSize = link.getWebSize();
        try (RandomAccessFile raf = new RandomAccessFile(new File(fileName), "r")) {
            raf.seek(0);
            byte[] head = new byte[linkId.length()];
            do {
                raf.read(head);
            } while (!Arrays.equals(linkId.getBytes(), head));
            raf.skipBytes(1);
            byte[] body = new byte[webSize];
            raf.read(body);
            return new String(body, 0, webSize);
        }
    }

    @Override
    String parsingContext(String htmlStr) {

        if (StringUtils.isEmpty(htmlStr)) {
            return null;
        }
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
    void createIndex(String context) throws IOException {
        if (StringUtils.isEmpty(context)) {
            return;
        }
        List<Term> segment = SpeedTokenizer.segment(context);

        Path tmp_index = Paths.get(FilePath.TMP_INDEX_PATH);
        Path term_id = Paths.get(FilePath.TERM_ID_PATH);
        if (!Files.exists(tmp_index)) {
            Files.createFile(tmp_index);
        }
        if (!Files.exists(term_id)) {
            Files.createFile(term_id);
        }
        try (PrintWriter tmpWriter = new PrintWriter(Files.newBufferedWriter(tmp_index, StandardOpenOption.APPEND));
             PrintWriter termWriter = new PrintWriter(Files.newBufferedWriter(term_id, StandardOpenOption.APPEND))) {
            for (Term term : segment) {
                long wordId;
                if (Analyzer.wordMap.containsKey(term.word)) {
                    wordId = Analyzer.wordMap.get(term.word);
                } else {
                    wordId = IDProvider.newWordId();
                    Analyzer.wordMap.put(term.word, wordId);
                }
                //将单词ID与对应网页链接写入文件
                StringBuffer buffer = new StringBuffer();
                buffer.append(wordId).append("\t").append(linkId);
                tmpWriter.println(buffer);
            }
            //将单词写入文件
            for (String word : Analyzer.wordMap.keySet()) {
                termWriter.println(word + "\t" + Analyzer.wordMap.get(word));
            }
        }

    }

    @Override
    public void run() {
        while (true) {
            doAnalyze();
        }
    }

}
