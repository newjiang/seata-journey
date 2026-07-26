package org.newjiang.learning.seata.account.service;

import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description
 */
@Service
public class XaService extends AbstractSeataService {
    @Override
    public String mode() {
        return "XA";
    }

    @Override
    @Transactional
    public Object ok(String mode, String type, int id) {
        return super.ok(mode, type, id);
    }

    @Override
    @Transactional
    public Object error(String mode, String type, int id) {
        return super.error(mode, type, id);
    }
}
