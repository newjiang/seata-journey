package org.newjiang.learning.seata.account.web;

import org.newjiang.learning.seata.account.service.AbstractSeataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seata")
public class SeataController {
    @Autowired
    private List<AbstractSeataService> seataService;

    @RequestMapping("/{mode}/{type}/{id}")
    public Object run(@PathVariable String mode, @PathVariable String type, @PathVariable int id) {
        System.out.println("开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for (AbstractSeataService service : seataService) {
            String targetMode = service.mode();
            if (mode.equalsIgnoreCase(targetMode)) {
                System.out.println("匹配模式：" + targetMode);
                if ("OK".equalsIgnoreCase(type)) {
                    System.out.println("运行ok案例");
                    return service.ok(mode, type, id);
                }
                if ("ERROR".equalsIgnoreCase(type)) {
                    System.out.println("运行error案例");
                    return service.error(mode, type, id);
                }
                throw new IllegalArgumentException("不支持类型:" + type);
            }
        }
        System.out.println("结束<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        throw new IllegalArgumentException("不支持模式:" + mode);
    }
}
