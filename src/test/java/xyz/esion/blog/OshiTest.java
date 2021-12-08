package xyz.esion.blog;


import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

/**
 * @author Esion
 * @since 2021/12/8
 */
public class OshiTest {

    private static final int MB = 1024 * 1024;

    public static void main(String[] args) {
        MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
        System.out.printf("heapCurrentUsage:%d Mb %n", mem.getHeapMemoryUsage().getUsed() / MB);
        System.out.printf("heapMax:%d Mb %n", mem.getHeapMemoryUsage().getMax() / MB);
        System.out.printf("heapCommitted:%d Mb %n", mem.getHeapMemoryUsage().getCommitted() / MB);
    }

}
