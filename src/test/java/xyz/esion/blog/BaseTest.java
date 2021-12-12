package xyz.esion.blog;

import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import xyz.esion.blog.domain.RssItem;
import xyz.esion.blog.util.FieldUtil;
import xyz.esion.blog.view.file.FileTreeView;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.LinkedList;
import java.util.Objects;

/**
 * @author Esion
 * @since 2021/12/9
 */
public class BaseTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "你好";
        String encode = URLEncoder.encode(s, "UTF-8");
        System.out.println(s);
        System.out.println(encode);
    }

    public static void tree() {
        FileTreeView view = new FileTreeView();
        view.setChildren(new LinkedList<>());
        view.setName("templates");
        view.setPath("C:\\Users\\Esion\\.blog\\templates");
        view.setIsDirectory(true);
        treeLoop(view.getPath(), view, "C:\\Users\\Esion\\.blog");
        System.out.println(JSONUtil.parseObj(view).toJSONString(4));
    }

    public static void treeLoop(String path, FileTreeView view, String prefix) {
        File file = new File(path);
        if (file.isDirectory()) {
            for (File listFile : Objects.requireNonNull(file.listFiles())) {
                FileTreeView item = new FileTreeView();
                item.setName(listFile.getName());
                item.setPath(FieldUtil.buildResultPath(listFile.getAbsolutePath(), prefix));
                item.setIsDirectory(listFile.isDirectory());
                view.getChildren().add(item);
                if (listFile.isDirectory()) {
                    item.setChildren(new LinkedList<>());
                    treeLoop(listFile.getAbsolutePath(), item, prefix);
                }
            }
        }
    }

    public static void rss() throws IOException {
        RssItem item = new RssItem();
        item.setTitle("标题");
        item.setDescription("描述");
        item.setAuthor("作者");
        item.setPubDate("2021-12-10 18:00:00");
        item.setLink("https://esion.xyz/article/1.html");
        item.setGuid("https://esion.xyz/article/1.html");
        RssItem item1 = new RssItem();
        item1.setTitle("标题");
        item1.setDescription("描述");
        item1.setAuthor("作者");
        item1.setPubDate("2021-12-10 18:00:00");
        item1.setLink("https://esion.xyz/article/1.html");
        item1.setGuid("https://esion.xyz/article/1.html");
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(item);
        jsonArray.add(item1);
        String itemStr = JSONUtil.toXmlStr(new JSONObject().set("item", jsonArray));
        System.out.println(itemStr);
    }

    public static void command() throws IOException {
        System.out.println(RuntimeUtil.getResultLines(
                new ProcessBuilder(StrUtil.splitToArray("java -version", StrUtil.C_SPACE)).redirectErrorStream(true).start()));
        System.out.println(RuntimeUtil.execForLines("java -version"));
    }

}
