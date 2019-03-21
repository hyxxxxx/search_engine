package com.dbc.exert;

public class FilePath {

    private static final String ROOT = ConfigUtil.getValueStr("root");
    /**
     * 网页链接与编号文件
     * 包括网页编号、网页大小等参数
     */
    public static final String DOC_ID_PATH = ROOT + "doc_id.bin";
    /**
     * 原始网页文件
     * 网页编号 + 网页源代码
     */
    public static final String DOC_RAW_PATH = ROOT + "doc_raw_{0}.bin";
    /**
     * 临时索引文件
     */
    public static final String TMP_INDEX_PATH = ROOT + "tmp_index.bin";
    /**
     * 单词编号文件
     */
    public static final String TERM_ID_PATH = ROOT + "term_id.bin";


}
