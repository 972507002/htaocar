package study.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.common.UUID;
import study.dao.dto.ReservDto;
import study.dao.entity.ReservationEntity;
import study.dao.mapper.ReservationDao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/***
 * 预约
 */
@Service
public class ReservationService {

    @Autowired
    ReservationDao reservationDao;

    /**添加预约*/
    public int addReserv(ReservDto reservDto,String loginName){
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setReservId(UUID.getUUID());
        reservationEntity.setLoginName(loginName);
        reservationEntity.setNiChen(reservDto.getNiChen());
        reservationEntity.setPhoneNum(reservDto.getPhoneNum());
        reservationEntity.setCarId(reservDto.getCarId());
        reservationEntity.setAddTime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(System.currentTimeMillis())));
        reservationEntity.setIsDelete(0);
        int len = reservationDao.addReserv(reservationEntity);
        return len;
    }

    /**查找我的预约*/
    public List<ReservDto> getReservs(String loginName,int pageIndex){
        List<ReservDto> reservDtos = new ArrayList<>();
        List<ReservationEntity> reservEntitys;
        pageIndex = pageIndex * 5;
        if(StringUtils.isNotBlank(loginName)) {
            reservEntitys = reservationDao.getReservs(loginName,pageIndex);
        }else {
            reservEntitys = reservationDao.getAllReservs(pageIndex);
        }
        for (int i = 0; i <reservEntitys.size() ; i++) {
            ReservDto reservDto = new ReservDto();
            ReservationEntity entity = reservEntitys.get(i);
            reservDto.setReservId(entity.getReservId());
            reservDto.setCarId(entity.getCarId());
            reservDto.setPhoneNum(entity.getPhoneNum());
            reservDto.setAddTime(entity.getAddTime().toString().substring(0,10));
            reservDto.setOrd(i+1);
            reservDtos.add(reservDto);
        }
        return reservDtos;

    }

    /**查找所有预约*/


    /**删除预约*/
}
