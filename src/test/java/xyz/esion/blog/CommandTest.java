package xyz.esion.blog;

import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.io.IOException;

/**
 * @author Esion
 * @since 2021/12/9
 */
public class CommandTest {

    public static void main(String[] args) throws IOException {
        System.out.println(RuntimeUtil.getResultLines(
                new ProcessBuilder(StrUtil.splitToArray("java -version", StrUtil.C_SPACE)).redirectErrorStream(true).start()));
        System.out.println(RuntimeUtil.execForLines("java -version"));
    }

}
