package com.dafen.xuejie.utils;

/**
 * Created by _Ms on 2016/10/25.
 */

public class TextFormatUtils {

    /**
     * 格式化表格字符串
     * @param title          标题
     * @param message    头信息
     * @param bodys          主体信息
     * @return 格式化后字符串
     */
    public static String format2TableStr(String title, String message, String... bodys) {

        /*
        获取最大列数
         */
        int maxColumn = getMaxColumn(title, message);
        {
            if (bodys != null) {
                int bodyMaxColumn = getMaxColumn(bodys);
                if (bodyMaxColumn > maxColumn) {
                    maxColumn = bodyMaxColumn;
                }
            }
        }

        /*
        生成头
         */
        String headStr;
        if (title == null) {
            headStr = createLongRepeatString(maxColumn + 4, "-");
        } else {
            headStr = String.format(
                    "%s: %s|",
                    title,
                    createLongRepeatString(maxColumn - title.length() + 1, "-")
            );
        }

        /*
        生成信息
         */
        String messageStr;
        if (message == null) {
            messageStr = "";
        } else {
            messageStr = String.format(
                    "| %s%s |",
                    message,
                    createLongRepeatString(maxColumn - message.length(), " ")
            );
        }

        /*
        生成主题内容分割线
         */
        String cutMessageStr;
        if (message == null) {
            cutMessageStr = "";
        } else {
            cutMessageStr = String.format(
                    "|%s|",
                    createLongRepeatString(maxColumn + 2, "-")
            );
        }

        /*
        生成主题内容分割线
         */
        String cutLineStr;
        if (message == null && bodys == null) {
            cutLineStr = "";
        } else {
            cutLineStr = String.format(
                    "|%s|",
                    createLongRepeatString(maxColumn + 2 /*两边添加符号与*/, "-")
            );
        }

        /*
        生成主题内容
         */
        StringBuffer bodyBuffer = new StringBuffer();
        if (bodys != null) {

                for (int x = 0; x < bodys.length; x++) {

                    if (bodys[x] != null) {

                        if (x == 0 && message != null) {

                            // 每一块主题内容添加分割线
                            bodyBuffer
                                    .append(cutMessageStr)
                                    .append("\n");
                        }

                        if (x != bodys.length && x != 0) {
                            // 每一块主题内容添加分割线
                            bodyBuffer
                                    .append(cutLineStr)
                                    .append("\n");
                        }

                        for (String bodySubStr : bodys[x].split("\n")) {

                            String bodyStr = String.format(
                                    "| %s%s |\n",
                                    bodySubStr,
                                    createLongRepeatString(maxColumn - bodySubStr.length(), " ") // 填充空格
                            );
                            bodyBuffer.append(bodyStr);

                        }
                    }
                }
        }

        /*
        生成尾巴
         */
        String footStr = String.format(
                "|%s",
                createLongRepeatString(maxColumn + 3, "-")
        );

        /*
        生成最终String
         */
        StringBuffer finalBuffer = new StringBuffer();
        // 头
        finalBuffer
                .append(headStr)
                .append("\n");
        // 信息
        if (messageStr.length() != 0) {
            finalBuffer.append(messageStr).append("\n");
        }
        // 主题
        if (bodyBuffer.length() != 0) {
            finalBuffer.append(bodyBuffer);
        }
        finalBuffer
                // 尾巴
                .append(footStr)
                .append("\n");


        return finalBuffer.toString();
    }

    /**
     * 获取最大列数
     * @param strs 欲获取最大列数字符串数组
     * @return
     */
    private static int getMaxColumn(String... strs) {
        if (strs == null) {
            throw new NullPointerException();
        }

        int maxColumn = 0;

        for (String str : strs) {
            if (str != null) {

                for (String subStr : str.split("\n")) {
                    if (subStr.length() > maxColumn) {

                        maxColumn = subStr.length();
                    }
                }
            }
        }

        return maxColumn;
    }

    /**
     * 创建一定数量长重字符串
     *
     * @param num 数量
     * @param str 字符串
     * @return 结果
     */
    private static String createLongRepeatString(int num, String str) {
        CheckUtils.notNull(str);

        StringBuffer buffer = new StringBuffer();
        for (int x = 0; x < num; x++) {
            buffer.append(str);
        }

        return buffer.toString();
    }

}
