package org.newjiang.learning.seata.order.service;

import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

/**
 * Description
 */
@Service
public class AtOrderService extends AbstractOrderService {
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
