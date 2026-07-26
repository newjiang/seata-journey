package org.newjiang.learning.seata.order.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description
 */
@Service
public class XaOrderService extends AbstractOrderService {
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
