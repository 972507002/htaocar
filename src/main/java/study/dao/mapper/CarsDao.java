package study.dao.mapper;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import study.dao.entity.CarsEntity;

import java.util.List;

public interface CarsDao {
    /**添加车辆*/
    int addCar(CarsEntity carsEntity);

    /**查找车辆*/
    @Select("select *from cars where carId = #{carId} limit 1")
    CarsEntity getCar(@Param("carId") String carId);

    /**根据关键字查询相关车辆*/
    List<CarsEntity> SearchByKeyWord(@Param("keyWord") String keyWord,@Param("pageIndex") int pageIndex,
                                     @Param("lPrice") double lPrice,@Param("hPrice") double hPrice,@Param("lAge") double lAge,@Param("hAge") double hAge);

    @Select("select *from cars where PhoneNumber = #{PhoneNumber} and isDelete = 0 order by AddTime limit #{pageIndex},3 ")
    List<CarsEntity> findCars(@Param("PhoneNumber") String PhoneNumber,@Param("pageIndex") int pageIndex);


    @Select("select *from cars where isDelete = 0 order by AddTime limit #{pageIndex},5 ")
    List<CarsEntity> findAllCars(@Param("pageIndex") int pageIndex);

    /**根据关键词搜索车辆*/
    List<CarsEntity> searchCars(@Param("keyWord") String keyWord,@Param("pageIndex") int pageIndex);

    @Update("update cars set isDelete = 1 where carId = #{carId}")
    int deleteCar(@Param("carId") String carId);

    /**通过用户审核*/
    @Update("update cars set IsAudit = 1 where carId = #{carId}")
    int passCar(@Param("carId") String carId);

    /**审核不通过*/
    @Update("update cars set IsAudit = 2 where carId = #{carId}")
    int dePassCar(@Param("carId") String carId);
}
