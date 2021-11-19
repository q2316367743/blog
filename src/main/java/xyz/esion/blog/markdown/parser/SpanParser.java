package xyz.esion.blog.markdown.parser;

/**
 * @author Esion
 * @since 2021/11/18
 */
public class SpanParser {

    private static final char DELETE = '~';
    private static final char STAR = '*';
    private static final char CODE = '`';
    private static final char LINK_NAME_START = '[';
    private static final char LINK_NAME_END = ']';
    private static final char LINK_HREF_START = '(';
    private static final char LINK_HREF_END = ')';

    private static int deleteStep = 0;
    private static int deleteStartIndex = 0;
    private static int deleteEndIndex = 0;

    private static int codeStep = 0;
    private static int codeIndex = 0;

    /**
     * 渲染内容<br />
     * 通过标记的方法渲染
     *
     * @param value 需要渲染的原始值
     * @return 渲染后的html
     */
    public static String render(String value) {
        char[] items = value.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char item : items) {
            if (item == DELETE) {
                deleteRender(sb);
            }else if (item == CODE) {
                codeRender(sb);
            } else {
                sb.append(item);
            }
        }
        // 收尾
        deleteEnd(sb);
        codeEnd(sb);
        return sb.toString();
    }

    public static void deleteRender(StringBuilder sb) {
        switch (deleteStep) {
            case 0:
                deleteStep = 1;
                deleteStartIndex = sb.length();
                break;
            case 1:
                if (sb.length() == deleteStartIndex) {
                    // 新的
                    deleteStep = 2;
                } else {
                    // 恢复
                    sb.insert(deleteStartIndex, DELETE);
                    deleteStep = 0;
                    deleteStartIndex = 0;
                }
                break;
            case 2:
                deleteStep = 3;
                deleteEndIndex = sb.length();
                break;
            case 3:
                if (sb.length() == deleteEndIndex) {
                    // 渲染全部
                    sb.insert(deleteStartIndex, "<s>");
                    sb.append("</s>");
                } else {
                    // 还原
                    sb.insert(deleteStartIndex, DELETE);
                    sb.insert(deleteStartIndex, DELETE);
                    sb.insert(deleteEndIndex, DELETE);
                }
                deleteStep = 0;
            default:
                deleteStep = 0;
                break;
        }
    }

    public static void deleteEnd(StringBuilder sb) {
        switch (deleteStep) {
            case 1:
                sb.insert(deleteStartIndex, DELETE);
                updateIndex(deleteStartIndex, 1);
                break;
            case 2:
                sb.insert(deleteStartIndex, DELETE);
                sb.insert(deleteStartIndex, DELETE);
                updateIndex(deleteStartIndex, 2);
                break;
            case 3:
                // 还原
                sb.insert(deleteStartIndex, DELETE);
                sb.insert(deleteStartIndex, DELETE);
                sb.insert(deleteEndIndex + 2, DELETE);
                updateIndex(deleteStartIndex, 3);
            default:
                break;
        }
    }

    public static void codeRender(StringBuilder sb) {
        if (codeStep == 0) {
            codeIndex = sb.length();
            codeStep = 1;
        }else {
            sb.insert(codeIndex, "<code>");
            sb.append("</code>");
            codeStep = 0;
        }
    }

    public static void codeEnd(StringBuilder sb) {
        if (codeStep == 1) {
            sb.insert(codeIndex, CODE);
        }
        codeStep = 0;
    }

    public static void updateIndex(int index, int length) {
        // code
        if (codeStep == 1) {
            if (codeIndex > index) {
                codeIndex += length;
            }
        }
        // 删除
        if (deleteStep == 1 || deleteStep == 2) {
            if (deleteStartIndex > index) {
                deleteStartIndex += length;
            }
        }else if (deleteStep == 3) {
            if (deleteStartIndex > index) {
                deleteStartIndex += length;
                deleteEndIndex += length;
            }
            if (deleteEndIndex > index) {
                deleteEndIndex += length;
            }
        }
    }

}
