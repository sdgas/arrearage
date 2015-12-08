package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.sdgas.VO.FileVO;
import org.sdgas.model.OwingMoney;
import org.sdgas.model.User;
import org.sdgas.service.OwingMoneyService;
import org.sdgas.util.ChangeTime;
import org.sdgas.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component("file")
@Scope("prototype")
public class FileAction extends MyActionSupport implements ModelDriven<FileVO> {

    @Qualifier("excelUtil")
    @Autowired
    private ExcelUtil excelUtil;
    private OwingMoneyService owingMoneyService;
    private final static Logger logger = LogManager.getLogger(FileAction.class);
    private final FileVO fileVO = new FileVO();
    private List<Object> obj = new ArrayList<Object>();
    private List<OwingMoney> owingMoneys = new ArrayList<OwingMoney>();
    private int count = 0;
    private int update = 0;
    private static String SAVE_PATH_DIR = "D:/arrearage/uploadFile/";

    //获取当前登陆用户
    HttpSession session = ServletActionContext.getRequest().getSession();
    User user = (User) session.getAttribute("person");
    String ip = (String) session.getAttribute("ip");

    public String recoverExcel() {
        count = 0;
        String name = uploadAttachment(fileVO.getUploadFile(), fileVO.getFileName(), SAVE_PATH_DIR);
        try {
            obj = excelUtil.readExcelByPath(SAVE_PATH_DIR + name, OwingMoney.class, 0, 0);
            for (Object anObj : obj) {
                owingMoneys.add((OwingMoney) anObj);
            }
            for (OwingMoney o : owingMoneys) {
                OwingMoney owingMoney = new OwingMoney();
                if (owingMoneyService.findByMsg(o.getUserId(), o.getContent()) == null) {
                    owingMoney.setContent(o.getContent());
                    owingMoney.setEnterDate(o.getEnterDate());
                    owingMoney.setEnterUser(o.getEnterUser());
                    owingMoney.setOwingMoney(o.getOwingMoney());
                    owingMoney.setPay(o.getPay());
                    owingMoney.setPayDate(o.getPayDate());
                    owingMoney.setRemark(o.getRemark());
                    owingMoney.setTel(o.getTel());
                    owingMoney.setUserId(o.getUserId());
                    owingMoney.setUserName(o.getUserName());
                    owingMoney.setTown(o.getTown());
                    //owingMoney.setPeriod(o.getPeriod());
                    owingMoney.setAccountPeriod(o.getAccountPeriod());
                    owingMoneyService.save(owingMoney);
                    logger.info(user.getUserName() + ",导入用户欠费信息(" + owingMoney.getUserId() + owingMoney.getUserName()
                            + ") IP:" + ip);
                    ++count;
                } else {
                    owingMoney.setContent(o.getContent());
                    owingMoney.setEnterDate(o.getEnterDate());
                    owingMoney.setEnterUser(o.getEnterUser());
                    owingMoney.setOwingMoney(o.getOwingMoney());
                    owingMoney.setPay(o.getPay());
                    owingMoney.setPayDate(o.getPayDate());
                    owingMoney.setRemark(o.getRemark());
                    owingMoney.setTel(o.getTel());
                    owingMoney.setUserId(o.getUserId());
                    owingMoney.setUserName(o.getUserName());
                    owingMoney.setTown(o.getTown());
                   // owingMoney.setPeriod(o.getPeriod());
                    owingMoney.setAccountPeriod(o.getAccountPeriod());
                    owingMoneyService.update(owingMoney);
                    logger.info(user.getUserName() + ",更新用户欠费信息(" + owingMoney.getUserId() + " " + owingMoney.getUserName()
                            + " " + owingMoney.getContent() + ") IP:" + ip);
                    ++update;
                }
            }
        } catch (RuntimeException e) {
            if (e.getMessage().contains("格式"))
                fileVO.setResultMessage("上传的EXCEL的内容格式不正确，请检查上传文件。");
            else
                fileVO.setResultMessage("EXCEL未导入成功");
            logger.error(e);
            return ERROR;
        }
        fileVO.setResultMessage("成功导入用户欠费信息" + count + "条，更新用户欠费信息" + update + "条。");
        return SUCCESS;
    }

    @Override
    public FileVO getModel() {
        return fileVO;
    }

    @Resource(name = "owingMoneyServiceImpl")
    public void setOwingMoneyService(OwingMoneyService owingMoneyService) {
        this.owingMoneyService = owingMoneyService;
    }

    public String uploadAttachment(File file, String fileName, String path) {
        // 获取上传文件的真实路径
        File dir = new File(path);
        // 若该目录不存在，则创建
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String[] temp = fileName.split("\\\\");
        fileName = temp[temp.length - 1];
        String name = ChangeTime.formatDate() + fileName;
        try {
            FileInputStream is = new FileInputStream(file);
            FileOutputStream os = new FileOutputStream(new File(dir, name));
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = is.read(buf)) != -1) {
                os.write(buf, 0, len);
            }

            is.close();
            os.close();
        } catch (FileNotFoundException f) {
            logger.error(f);
        } catch (IOException ioe) {
            logger.error(ioe);
        }
        return name;
    }

}
