package org.sdgas.service.Impl;

import org.sdgas.base.DaoSupport;
import org.sdgas.model.OwingMoney;
import org.sdgas.service.OwingMoneyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wilson.he on 2015/12/8.
 */
@Service
@Transactional
public class OwingMoneyServiceImpl extends DaoSupport<OwingMoney> implements OwingMoneyService {

    @Override
    public OwingMoney findByMsg(String userId, String content) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        params.put("content", content.trim());
        return (OwingMoney) this.findSpecialObject(OwingMoney.class, params);
    }

    @Override
    public List<OwingMoney> findByUserId(String userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        return this.findByFields(OwingMoney.class, params);
    }
}
