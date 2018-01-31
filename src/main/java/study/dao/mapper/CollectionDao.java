package study.dao.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import study.dao.entity.CollectionEntity;

import java.util.List;

public interface CollectionDao {
    /**
     * 根据用户id查购物车
     */
    @Select("select *from collection where personId = #{personId} and isDelete = 0 ORDER BY addTime desc limit #{pageIndex},3")
    List<CollectionEntity> getCollection(@Param("personId") String personId,@Param("pageIndex") int pageIndex);

    /**删除购物车车辆*/
    @Update("update collection set isDelete = 1 where collectId = #{collectId}")
    int deleteColl(String collectId);

    /**根据carId和loginName删除收藏车辆*/
    @Delete("delete from collection where carId = #{carId} and personId = #{personId}")
    int delCollect(@Param("carId") String carId,@Param("personId") String personId);

    /**根据carId和loginName查找收藏车辆*/
    @Select("select * from collection where carId = #{carId} and personId = #{personId} and isDelete = 0 limit 1")
    CollectionEntity findCollect(@Param("carId") String carId,@Param("personId") String personId);

    /**
     * 添加车辆入购物车
     */
    int addColl(CollectionEntity entity);
}
