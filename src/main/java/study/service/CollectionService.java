package study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.common.UUID;
import study.dao.dto.CarInfoDto;
import study.dao.entity.CarsEntity;
import study.dao.entity.CollectionEntity;
import study.dao.mapper.CarsDao;
import study.dao.mapper.CollectionDao;
import study.dao.mapper.FileDao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/20.
 */
@Service
public class CollectionService {
    @Autowired
    CollectionDao collectionDao;

    @Autowired
    CarsDao carsDao;

    @Autowired
    FileDao fileDao;

    /**添加车辆到购物车*/
    public int addCollect(String carId,String loginName){
        CollectionEntity collectionEntity = new CollectionEntity();
        collectionEntity.setCollectId(UUID.getUUID());
        collectionEntity.setCarId(carId);
        collectionEntity.setPersonId(loginName);
        collectionEntity.setAddTime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(System.currentTimeMillis())));
        collectionEntity.setIsDelete(0);
        int len = collectionDao.addColl(collectionEntity);
        return len;
    }

    /**从购物车删除（物理删除，避免控件浪费）*/
    public int delCollect(String carId,String loginName){
        return collectionDao.delCollect(carId, loginName);
    }

    /**根据carId和loginName查找收藏，避免重复收藏*/
    public CollectionEntity findCollect(String carId,String loginName){
        return collectionDao.findCollect(carId, loginName);
    }

    /**根据用户查找收藏车辆*/
    public List<CarInfoDto> getCollects(String loginName,int pageIndex){
        List<CarInfoDto> carInfoDtos = new ArrayList<>();
        List<CollectionEntity> collEntitys = collectionDao.getCollection(loginName,pageIndex*3);
        for (int i = 0; i <collEntitys.size() ; i++) {
            CarInfoDto carInfoDto = new CarInfoDto();
            CollectionEntity collEntity = collEntitys.get(i);
            /**根据收藏carId查询车辆所有信息（量少直接写在循环里、贼蠢、后期优化）*/
            CarsEntity carsEntity = carsDao.getCar(collEntity.getCarId());
            /**收藏车辆的基本信息*/
            carInfoDto.setCarId(carsEntity.getCarId());
            carInfoDto.setBrand(carsEntity.getBrand());
            carInfoDto.setSeries(carsEntity.getSeries());
            carInfoDto.setPrice(carsEntity.getPrice());
            carInfoDto.setPlateTime(carsEntity.getPlateTime());
            /**根据carId查首要图片*/
            String path = fileDao.getPath(carsEntity.getCarId());
            carInfoDto.setCarUrl(path);
            carInfoDtos.add(carInfoDto);
        }

        return carInfoDtos;
    }
}
