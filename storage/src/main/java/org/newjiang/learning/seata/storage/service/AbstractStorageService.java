package org.newjiang.learning.seata.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * Description
 */
public abstract class AbstractStorageService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public abstract String mode();

    public Object ok(int id) {
        List<Map<String, Object>> oldRes = jdbcTemplate.queryForList("select * from storage_tbl where id = ?", id);
        System.out.println("id = " + id + " >> old storage_tbl result >> " + oldRes);
        int updated = jdbcTemplate.update("update storage_tbl set count = count + 1 where id = ?", id);
        System.out.println("storage_tbl updated = " + updated);
        if (updated < 1) {
            throw new RuntimeException("更新storage_tbl id=" + id + "的数据失败");
        }
        List<Map<String, Object>> newRes = jdbcTemplate.queryForList("select * from storage_tbl where  id = ?", id);
        System.out.println("id = " + id + " >> new storage_tbl result >> " + newRes);
        return "success";
    }

    public Object error(int id) {
        Map<String, Object> oldRes = jdbcTemplate.queryForMap("select * from storage_tbl where  id = ?", id);
        System.out.println("id = " + id + " >> old storage_tbl result >> " + oldRes);
        int updated = jdbcTemplate.update("update storage_tbl set count = count + 1 where id = ?", id);
        System.out.println("storage_tbl updated = " + updated);
        if (updated < 1) {
            throw new RuntimeException("更新storage_tbl id=" + id + "的数据失败");
        }
        Map<String, Object> newRes = jdbcTemplate.queryForMap("select * from storage_tbl where  id = ?", id);
        System.out.println("id = " + id + " >> new storage_tbl result >> " + newRes);
        throw new RuntimeException("更新storage_tbl id=" + id + "的数据失败，主动抛出异常");
    }
}
