package org.newjiang.learning.seata.account.service;

import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

/**
 * Description
 */
@Service
public class TccService extends AbstractSeataService {
    @Override
    public String mode() {
        return "TCC";
    }

    @Override
    @GlobalTransactional
    public Object ok(String mode, String type, int id) {
        return super.ok(mode, type, id);
    }

    @Override
    @GlobalTransactional
    public Object error(String mode, String type, int id) {
        return super.error(mode, type, id);
    }
}
