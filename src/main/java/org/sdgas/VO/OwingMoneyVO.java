package org.sdgas.VO;

import org.sdgas.model.OwingMoney;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wilson.he on 2015/12/17.
 */
public class OwingMoneyVO extends BaseVO {

    private String msg;
    private String userIds;
    private List<OwingMoney> owingMoneys = new ArrayList<OwingMoney>();

    public List<OwingMoney> getOwingMoneys() {
        return owingMoneys;
    }

    public void setOwingMoneys(List<OwingMoney> owingMoneys) {
        this.owingMoneys = owingMoneys;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
