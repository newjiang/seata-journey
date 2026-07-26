package org.newjiang.learning.seata.storage.service;

import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description
 *
 * @author newjiang
 * @since 2026-07-23
 */
@Service
public class XaStorageService extends AbstractStorageService {
    @Override
    public String mode() {
        return "XA";
    }

    @Override
    @Transactional
    public Object ok(int id) {
        return super.ok(id);
    }

    @Override
    @Transactional
    public Object error(int id) {
        return super.error(id);
    }
}

