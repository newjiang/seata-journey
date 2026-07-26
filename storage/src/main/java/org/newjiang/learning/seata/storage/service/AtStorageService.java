package org.newjiang.learning.seata.storage.service;

import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Description
 *
 * @author newjiang
 * @since 2026-07-23
 */
@Service
public class AtStorageService extends AbstractStorageService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String mode() {
        return "AT";
    }

    @Override
    @GlobalTransactional
    public Object ok(int id) {
        return super.ok(id);
    }

    @Override
    @GlobalTransactional
    public Object error(int id) {
        return super.error(id);
    }
}

