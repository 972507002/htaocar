package study.dao.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import study.dao.entity.ReservationEntity;

import java.util.List;

public interface ReservationDao {

    /**添加预约*/
    int addReserv(ReservationEntity reserv);

    /**查询我的预约*/
    @Select("select *from reservation where isDelete = 0 and loginName = #{loginName} limit #{pageIndex},5")
    List<ReservationEntity> getReservs(@Param("loginName") String loginName,@Param("pageIndex") int pageIndex);

    /**查询所有预约*/
    @Select("select *from reservation where isDelete = 0 limit #{pageIndex},5")
    List<ReservationEntity> getAllReservs(@Param("pageIndex") int pageIndex);

    /**
     * 删除预约，物理删除
     */
    @Delete("delete from reservation where reservId = #{reservId}")
    int deleteRe(@Param("reservId") String reservId);
}
