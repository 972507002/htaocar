package study.dao.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import study.dao.entity.FileEntity;

import java.util.List;

public interface FileDao {

    /**
     * 根据车辆id查找车辆图片
     * 车主有权限在我的车辆信息中查看未认证车辆，未认证车辆不能再网站被查询
     * @param carId
     * @return
     */
    @Select("select *from file where carId = #{carId} and isDelete = 0")
    List<FileEntity> getFile(String carId);

    @Select("select Path from file where carId = #{carId} and Ord = 0 and isDelete = 0 limit 1")
    String getPath(String carId);

    /**添加图片*/
    int addPhoto(FileEntity entity);
    /**
     * 图片认证，to Do?
     */
    int auditPhoto(String carId);

    /**
     * 图片删除
     */
    @Update("update file set isDelete =1 where fileId = #{fileId}")
    int deleteFile(String fileId);
}
