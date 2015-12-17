package org.sdgas.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sdgas.VO.FileVO;
import org.sdgas.util.WebTool;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Component("FileDownload")
@Scope("prototype")
public class FileDownloadAction extends ActionSupport implements ModelDriven<FileVO> {

    private final FileVO fileVO = new FileVO();
    private Logger logger = LogManager.getLogger(FileDownloadAction.class);
    private final static String SAVE_PATH_DIR = "D:/arrearage/uploadFile/";

    //返回一个输入流，作为一个客户端来说是一个输入流，但对于服务器端是一个 输出流
    public InputStream getDownloadFile() throws Exception {
        String filePath;
        File file = null;

        filePath = SAVE_PATH_DIR + fileVO.getFileName();
        file = new File(filePath);
        fileVO.setFileName(WebTool.changeCharset(fileVO.getFileName(), "ISO-8859-1"));

        return new FileInputStream(file);

    }

    //文件下载
    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    @Override
    public FileVO getModel() {
        return fileVO;
    }
}