package org.sdgas.action;

import com.opensymphony.xwork2.ModelDriven;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.sdgas.VO.FileVO;
import org.sdgas.util.ChangeTime;
import org.sdgas.util.MysqlOperation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.io.File;

@Component("mysql")
@Scope("prototype")
public class MysqlAction extends MyActionSupport implements ModelDriven<FileVO> {
    private static MysqlOperation mo = new MysqlOperation();
    private final FileVO fileVO = new FileVO();
    private static String hostName = "localhost";
    private static String dataBase = "dormitory";
    private static String userName = "root";
    private static String password = "root";
    private static String SAVE_PATH_DIR = "D:/Dormitory/_sql/";
    private static String mysqlBinUrl = "C:/Program Files/MySQL/MySQL Server 5.5/bin/";

    private final static Logger logger = LogManager.getLogger(MysqlAction.class);

    public String backupMysqlDatabase() {

        // �õ������ļ���Ŀ¼����ʵ·��
        File dir = new File(SAVE_PATH_DIR);
        // �����Ŀ¼�����ڣ��ʹ���
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String date = ChangeTime.formatDate(ChangeTime.getCurrentDate());
        try {
            mo.backupMysqlDatabase(mysqlBinUrl, hostName, dataBase, userName,
                    password, SAVE_PATH_DIR  + date + ".sql");
        } catch (Exception e) {
            fileVO.setResultMessage("ϵͳ����ʧ�ܣ���鿴��̨��������");
            logger.error(e);
            return ERROR;
        }
        fileVO.setResultMessage("ϵͳ�ɹ��������ݿ�");
        logger.trace("the database backup success!");
        return SUCCESS;
    }

    public String resumeMysqlDatabase() {
        if (fileVO.getDbName().isEmpty()) {
            fileVO.setResultMessage("<script>alert('��ѡ���ļ���');location.href='/page/file/db.jsp';</script>");
            return ERROR;
        }
        // �õ������ļ���Ŀ¼����ʵ·��
        File dir = new File(SAVE_PATH_DIR);
        // �����Ŀ¼�����ڣ��ʹ���
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            mo.resumeMysqlDatabase(mysqlBinUrl, hostName, dataBase, userName,
                    password, fileVO.getDbName());
        } catch (Exception e) {
            fileVO.setResultMessage("ϵͳ��ԭʧ�ܣ���鿴��̨��������");
            logger.error(e);
            return ERROR;
        }
        fileVO.setResultMessage("ϵͳ�ɹ���ԭ���ݿ�");
        logger.trace("the database resume success!");
        return SUCCESS;
    }

    @Override
    public FileVO getModel() {
        return fileVO;
    }
}