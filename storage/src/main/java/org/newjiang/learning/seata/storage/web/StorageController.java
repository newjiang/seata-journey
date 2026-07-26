package org.newjiang.learning.seata.storage.web;

import org.newjiang.learning.seata.storage.service.AbstractStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/storage")
public class StorageController {
    @Autowired
    private List<AbstractStorageService> services;

    @RequestMapping("/{mode}/{type}/{id}")
    public Object run(@PathVariable String mode, @PathVariable String type, @PathVariable int id) {
        System.out.println("开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for (AbstractStorageService service : services) {
            String targetMode = service.mode();
            if (mode.equalsIgnoreCase(targetMode)) {
                System.out.println("匹配模式：" + targetMode);
                if ("OK".equalsIgnoreCase(type)) {
                    System.out.println("运行ok案例");
                    return service.ok(id);
                }
                if ("ERROR".equalsIgnoreCase(type)) {
                    System.out.println("运行error案例");
                    return service.error(id);
                }
                throw new IllegalArgumentException("不支持类型:" + type);
            }
        }
        System.out.println("结束<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        throw new IllegalArgumentException("不支持模式:" + mode);
    }
}
