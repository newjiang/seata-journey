package org.newjiang.learning.seata.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * Description
 */
public abstract class AbstractOrderService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public abstract String mode();

    public Object ok(int id) {
        List<Map<String, Object>> oldRes = jdbcTemplate.queryForList("select * from order_tbl where id = ?", id);
        System.out.println("id = " + id + " >> old order_tbl result >> " + oldRes);
        int updated = jdbcTemplate.update("update order_tbl set count = count + 1 where id = ?", id);
        System.out.println("order_tbl updated = " + updated);
        if (updated < 1) {
            throw new RuntimeException("更新order_tbl id=" + id + "的数据失败");
        }
        List<Map<String, Object>> newRes = jdbcTemplate.queryForList("select * from order_tbl where  id = ?", id);
        System.out.println("id = " + id + " >> new order_tbl result >> " + newRes);
        return "success";
    }

    public Object error(int id) {
        Map<String, Object> oldRes = jdbcTemplate.queryForMap("select * from order_tbl where  id = ?", id);
        System.out.println("id = " + id + " >> old order_tbl result >> " + oldRes);
        int updated = jdbcTemplate.update("update order_tbl set count = count + 1 where id = ?", id);
        System.out.println("order_tbl updated = " + updated);
        if (updated < 1) {
            throw new RuntimeException("更新order_tbl id=" + id + "的数据失败");
        }
        Map<String, Object> newRes = jdbcTemplate.queryForMap("select * from order_tbl where  id = ?", id);
        System.out.println("id = " + id + " >> new order_tbl result >> " + newRes);
        throw new RuntimeException("更新order_tbl id=" + id + "的数据失败，主动抛出异常");
    }
}
