package org.newjiang.learning.seata.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * Description
 */
public abstract class AbstractSeataService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public abstract String mode();

    public Object ok(String mode, String type, int id) {
        int updated = jdbcTemplate.update("update account_tbl set money = money + 1 where id = ?", id);
        System.out.println("account_tbl updated = " + updated);
        if (updated < 1) {
            throw new RuntimeException("更新account_tbl id=" + id + "的数据失败");
        }
        String orderUrl = String.format("http://seata-order/order/%s/%s/%d", mode, type, id);
        String orderRespose = restTemplate.postForObject(orderUrl, null, String.class);
        System.out.println("orderRespose = " + orderRespose);
        if (!"success".equalsIgnoreCase(orderRespose)) {
            throw new RuntimeException("调用seata-order异常");
        }
        String storageUrl = String.format("http://seata-storage/storage/%s/%s/%d", mode, type, id);
        String storageRespose = restTemplate.postForObject(storageUrl, null, String.class);
        System.out.println("storageRespose = " + storageRespose);
        if (!"success".equalsIgnoreCase(orderRespose)) {
            throw new RuntimeException("调用seata-storage异常");
        }
        return "success";
    }

    public Object error(String mode, String type, int id) {
        int updated = jdbcTemplate.update("update account_tbl set money = money + 1 where id = ?", id);
        System.out.println("accountMapper.update updated = " + updated);
        if (updated < 1) {
            throw new RuntimeException("更新id=" + id + "的数据失败");
        }
        String orderUrl = String.format("http://seata-order/order/%s/%s/%d", "ok", type, id);
        String orderRespose = restTemplate.postForObject(orderUrl, null, String.class);
        System.out.println("order ok Respose = " + orderRespose);
        if (!"success".equalsIgnoreCase(orderRespose)) {
            throw new RuntimeException("调用seata-order异常");
        }
        String storageUrl = String.format("http://seata-storage/storage/%s/%s/%d", "ok", type, id);
        String storageRespose = restTemplate.postForObject(storageUrl, null, String.class);
        System.out.println("storage ok Respose = " + storageRespose);
        if (!"success".equalsIgnoreCase(orderRespose)) {
            throw new RuntimeException("调用seata-storage异常");
        }
        throw new RuntimeException("主动抛出异常，更新id=" + id + "的数据失败");
    }
}
