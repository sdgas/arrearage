package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.OwingMoneyVO;
import org.sdgas.base.PageView;
import org.sdgas.model.OwingMoney;
import org.sdgas.model.User;
import org.sdgas.service.OwingMoneyService;
import org.sdgas.util.UserUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by wilson.he on 2015/12/17.
 */

@Component("OwingMoney")
@Scope("prototype")
public class OwingMoneyAction extends MyActionSupport implements ModelDriven<OwingMoneyVO> {

    private OwingMoneyService owingMoneyService;
    private final OwingMoneyVO owingMoneyVO = new OwingMoneyVO();

    //获取当前登陆用户
    HttpSession session = ServletActionContext.getRequest().getSession();
    User user = (User) session.getAttribute("person");
    String ip = (String) session.getAttribute("ip");

    public String execute() {
        if (UserUtil.checkUserLogIn(user)) {
            owingMoneyVO.setResultMessage("<script>alert('请登录！');location.href='/login.jsp';</script>");
            return ERROR;
        }
        /** 每页显示的结果数 **/
        int maxResult = 20;
        /** 封装的页面数据 **/
        PageView<OwingMoney> pageView = new PageView<OwingMoney>(maxResult,
                owingMoneyVO.getPage());
        int firstIndex = ((pageView.getCurrentPage() - 1) * pageView
                .getMaxResult());// 开始索引

        // 按照条件排序
        LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
        orderBy.put("enterDate", "DESC");
        /** 列表条件 **/
        StringBuffer jpql = new StringBuffer("pay=0");

        /** 列表条件的值 **/
        List<Object> params = new ArrayList<Object>();
        pageView.setQueryResult(owingMoneyService.getScrollData(OwingMoney.class, firstIndex, maxResult, jpql.toString(),
                params.toArray(), orderBy));
        owingMoneyVO.setPageView(pageView);

        view = "/page/core/findOwingMoney.jsp";
        return VIEW;
    }

    public String findByUserId() {
        List<OwingMoney> owingMoneys = owingMoneyService.findByUserId(owingMoneyVO.getMsg());
        owingMoneyVO.setOwingMoneys(owingMoneys);
        view = "/page/core/find.jsp";
        return VIEW;
    }

    public String due() {
        System.out.printf(owingMoneyVO.getUserIds());
        /*String[] userIds = owingMoneyVO.getUserIds().trim().split(",");
        for(String userId : userIds){
            OwingMoney = owingMoneyService.
        }*/

        return SUCCESS;
    }

    @Override
    public OwingMoneyVO getModel() {
        return owingMoneyVO;
    }

    @Resource(name = "owingMoneyServiceImpl")
    public void setOwingMoneyService(OwingMoneyService owingMoneyService) {
        this.owingMoneyService = owingMoneyService;
    }
}
