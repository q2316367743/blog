package xyz.esion.blog.controller.manage;

import cn.hutool.json.JSONObject;
import cn.hutool.system.oshi.OshiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import oshi.hardware.GlobalMemory;
import xyz.esion.blog.domain.Website;
import xyz.esion.blog.global.Result;
import xyz.esion.blog.service.WebsiteService;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

/**
 * @author Esion
 * @since 2021/11/21
 */
@RestController
@RequestMapping("manage/api/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final WebsiteService websiteService;

    @GetMapping("dynamic")
    public Result<JSONObject> get(){
        JSONObject item = new JSONObject();
        JSONObject memory = new JSONObject();
        GlobalMemory globalMemory = OshiUtil.getMemory();
        memory.set("total", globalMemory.getTotal());
        memory.set("available", globalMemory.getAvailable());
        JSONObject jvm = new JSONObject();
        MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
        jvm.set("use", mem.getHeapMemoryUsage().getUsed());
        jvm.set("total", mem.getHeapMemoryUsage().getMax());
        item.set("memory", memory);
        item.set("cpu", OshiUtil.getCpuInfo());
        item.set("jvm", jvm);
        return Result.success(item);
    }

    @GetMapping("base")
    public Result<JSONObject> base(){
        JSONObject base = new JSONObject();
        base.set("os", OshiUtil.getOs());
        base.set("website", websiteService.info());
        return Result.success(base);
    }

}
