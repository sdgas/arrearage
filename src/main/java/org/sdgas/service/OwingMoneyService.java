package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.OwingMoney;

import java.util.List;

/**
 * Created by wilson.he on 2015/12/8.
 */
public interface OwingMoneyService extends DAO {

    OwingMoney findByMsg(String userId, String content);

    List<OwingMoney> findByUserId(String userId);
}
