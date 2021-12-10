package xyz.esion.blog;

import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import xyz.esion.blog.domain.RssItem;

import java.io.IOException;

/**
 * @author Esion
 * @since 2021/12/9
 */
public class BaseTest {

    public static void main(String[] args) throws IOException {
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
