package study.service;

import com.aliyun.oss.OSSClient;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import study.common.AliyunOSSClientUtil;
import study.common.UUID;
import study.dao.entity.FileEntity;
import study.dao.mapper.FileDao;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import static study.common.OSSClientConstants.BACKET_NAME;
import static study.common.OSSClientConstants.FOLDER;

/**
 * Created by Administrator on 2017/10/20.
 */
@Service
public class FileService {
    Logger logger = Logger.getLogger(FileService.class);

    @Autowired
    FileDao fileDao;

    /**
     * 上传图片至阿里云
     */
    public String upPhoto(String carId, List<MultipartFile> files) {


        OSSClient ossClient = AliyunOSSClientUtil.getOSSClient();
        String path;
        File f;
        String md5key = null;
        String message = null;
        for (int i = 0; i <files.size(); i++) {
            MultipartFile file = files.get(i);
            String str = file.getOriginalFilename();
            String[] strArray = str.split("\\.");
            String name=getPath()+"."+strArray[1];
            path = "temp/"+name;
            f = new File(path);
            try {
                FileUtils.copyInputStreamToFile(file.getInputStream(), f);
                md5key = AliyunOSSClientUtil.uploadObject2OSS(ossClient, f,name, BACKET_NAME, FOLDER);
                if(StringUtils.isNotBlank(md5key)){
                    String url="http://htaocar.oss-cn-hangzhou.aliyuncs.com/car/" + f.getName();
                    int len = addPhoto(i,carId,f.length(),url);
                    if(len>0){
                        message="上传图片成功";
                    }
                }else {
                    message = "上传出错";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.info("http://htaocar.oss-cn-hangzhou.aliyuncs.com/car/" + f.getName());
        }


        return message;
    }


    /**
     * 批量上传图片，测试性质
     */
    public int addPhoto(int n, String carId, Long length,String url) {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileId(UUID.getUUID());
        fileEntity.setCarId(carId);
        fileEntity.setAddTime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(System.currentTimeMillis())));
        fileEntity.setPath(url);
        double size = length;
        fileEntity.setSize(size);
        fileEntity.setOrd(n);
        fileEntity.setAudit(0);
        fileEntity.setIsDelete(0);
        int len = fileDao.addPhoto(fileEntity);
        return len;
    }

    public String getPath() {
        String name = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(System.currentTimeMillis())).toString();
        name = name.replace(" ", "").replace("-", "").replace(":", "");
        name = name.replace(".", "_") + (int) ((Math.random() * 9 + 1) * 100);
//        name="images/"+name+".jpg";
        return name;
    }
}