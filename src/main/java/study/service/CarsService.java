package study.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import study.common.UUID;
import study.dao.dto.CarDto;
import study.dao.dto.CarInfoDto;
import study.dao.dto.SearchDto;
import study.dao.entity.CarsEntity;
import study.dao.entity.CollectionEntity;
import study.dao.entity.FileEntity;
import study.dao.entity.PersonEntity;
import study.dao.mapper.CarsDao;
import study.dao.mapper.CollectionDao;
import study.dao.mapper.FileDao;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2017/10/20.
 */
@Service
public class CarsService {

    Logger logger = Logger.getLogger(CarsService.class);

    @Autowired
    CarsDao carsDao;

    @Autowired
    FileDao fileDao;

    @Autowired
    CollectionDao collectionDao;

    /**
     * 添加车辆
     */
    public String addCar(CarDto carsDto, PersonEntity personEntity) {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;

        CarsEntity carsEntity = new CarsEntity();
        carsEntity.setCarId(UUID.getUUID());
        carsEntity.setPersonId(personEntity.getPersonId());
        carsEntity.setPhoneNumber(personEntity.getLoginName());
        carsEntity.setOwnName(carsDto.getP_username());
        carsEntity.setBrand(carsDto.getP_brand());
        carsEntity.setSeries(carsDto.getP_subbrand());
        carsEntity.setType(carsDto.getP_model());
        carsEntity.setColor(carsDto.getP_color());

        /**车龄计算，超过9个月就算1年*/
        double age = year - carsDto.getP_year();
        if ((month - carsDto.getP_month()) != 0) {
//            System.out.println(month - carsDto.getP_month());
            age += (month - carsDto.getP_month()) / 10.0;
        }
        carsEntity.setAge(age);

        carsEntity.setPlateTime(carsDto.getP_year() + "年" + carsDto.getP_month() + "月上牌");

        carsEntity.setMileage(carsDto.getP_kilometre());
        carsEntity.setGearBox(carsDto.getP_transmission());
        carsEntity.setEmission(carsDto.getP_emission());
        carsEntity.setDisplacement(carsDto.getP_gas());
        carsEntity.setCountry(carsDto.getP_country());
        carsEntity.setIntroduce(carsDto.getP_details());
        carsEntity.setAddTime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(System.currentTimeMillis())));
        double pri = Double.parseDouble(carsDto.getP_price());
        carsEntity.setPrice(pri);
        carsEntity.setIsAudit(0);
        carsEntity.setIsDelete(0);


        int len = carsDao.addCar(carsEntity);

        logger.info("插入车辆数:" + len);

//        //添加图片
//        addPhoto(5,carsEntity.getCarId());

        return carsEntity.getCarId();
    }


    /**
     * 查询车辆详情
     */
    public void getCar(String carId, String loginName, ModelMap modelMap) {
        CarDto carDto = new CarDto();
        CarsEntity carsEntity = carsDao.getCar(carId);
        if (carsEntity != null) {
            carDto.setP_model(carsEntity.getType());//车型，字段重复太多
            carDto.setP_color(carsEntity.getColor());
            carDto.setP_emission(carsEntity.getEmission()); //排放标准
            carDto.setP_gas(carsEntity.getDisplacement());//排量L 坑爹的命名
            carDto.setP_transmission(carsEntity.getGearBox()); //变速箱
            carDto.setP_kilometre(carsEntity.getMileage());//里程
            carDto.setP_country(carsEntity.getCountry());
            carDto.setP_details(carsEntity.getIntroduce());
            carDto.setP_username(carsEntity.getOwnName());//车主称谓

            modelMap.addAttribute("carDto", carDto);//加入model


            CarInfoDto carInfoDto = new CarInfoDto();
            carInfoDto.setCarId(carsEntity.getCarId());
            carInfoDto.setBrand(carsEntity.getBrand());
            carInfoDto.setSeries(carsEntity.getSeries());
            String plateTime = carsEntity.getPlateTime();
            plateTime = plateTime.replace("上牌", "");
            carInfoDto.setPlateTime(plateTime); //上牌时间
            carInfoDto.setAddTime(carsEntity.getAddTime().toString());
            carInfoDto.setPrice(carsEntity.getPrice());
            if (carsEntity.getIsAudit() == 0) {
                carInfoDto.setStyle("未审核");
            } else {
                carInfoDto.setStyle("已审核");
            }
            /**获取主图路径*/
            List<FileEntity> fileEntity = fileDao.getFile(carId);
            List<String> urls = new ArrayList<>();
            for (FileEntity f : fileEntity) {
                if (f.getOrd() == 0) {
                    carInfoDto.setCarUrl(f.getPath());
                }
                urls.add(f.getPath());
            }
            carInfoDto.setUrlList(urls); //图片
            //加入车主电话字段
            String phoneNum = carsEntity.getPhoneNumber();
            if (StringUtils.isBlank(loginName) && StringUtils.isNotBlank(phoneNum)) {
                phoneNum = phoneNum.substring(0, 9) + "**";

            }
            carInfoDto.setOwnPhone(phoneNum);
            /**根据用户和carId查找收藏车辆*/
            CollectionEntity collectionEntity = collectionDao.findCollect(carsEntity.getCarId(), loginName);
            /**车辆是否被用户收藏*/
            if (null == collectionEntity) {
                carInfoDto.setIsCollect(0);
            } else {
                carInfoDto.setIsCollect(1);
            }

            modelMap.addAttribute("carInfoDto", carInfoDto);

        }
    }

    /**
     * 根据条件查询车辆
     */
    public List<CarInfoDto> search(SearchDto searchDto) {
        String keyWord = searchDto.getKeyWord();
        int pageIndex = searchDto.getPageIndex();
        double lPrice = searchDto.getlPrice();
        double hPrice = searchDto.gethPrice();
        double lAge = searchDto.getlAge();
        double hAge = searchDto.gethAge();

        List<CarInfoDto> carInfoDtos = new ArrayList<>();
        List<CarsEntity> carsEntities = carsDao.SearchByKeyWord(keyWord, pageIndex, lPrice, hPrice, lAge, hAge);
        for (CarsEntity carsEntity : carsEntities) {
            CarInfoDto carInfoDto = new CarInfoDto();
            carInfoDto.setCarId(carsEntity.getCarId());
            carInfoDto.setBrand(carsEntity.getBrand());
            carInfoDto.setSeries(carsEntity.getSeries());
            carInfoDto.setStyle(carsEntity.getType());
            carInfoDto.setPrice(carsEntity.getPrice());
            carInfoDto.setDisplacement(carsEntity.getDisplacement());
            carInfoDto.setGearBox(carsEntity.getGearBox());
            carInfoDto.setCountry(carsEntity.getCountry());
            carInfoDto.setPlateTime(carsEntity.getPlateTime());
            carInfoDto.setAddTime(carsEntity.getAddTime().toString().substring(0, 10));
            carInfoDto.setColor(carsEntity.getColor());
            carInfoDto.setMileage(carsEntity.getMileage());
            /**根据carId查首要图片*/
            String path = fileDao.getPath(carsEntity.getCarId());
            carInfoDto.setCarUrl(path);
            /**车主介绍*/
            String introduce = carsEntity.getIntroduce();
            if (StringUtils.isNotBlank(introduce) && introduce.length() > 30) {
                introduce = introduce.substring(0, 30) + "...";
            }
            carInfoDto.setIntroduce(introduce);

            carInfoDtos.add(carInfoDto);
        }
        return carInfoDtos;
    }

    /**
     * 获取首图路径
     */
    public String getPath(String carId) {
        return fileDao.getPath(carId);
    }

    /**
     * 根据用户查找车辆返回车辆info
     */
    public List<CarInfoDto> getInfos(String loginName, int pageIndex) {
        List<CarsEntity> cars = carsDao.findCars(loginName, pageIndex * 3);
        List<CarInfoDto> carsInfo = new ArrayList<>();
        for (CarsEntity carsEntity : cars) {
            CarInfoDto carInfoDto = new CarInfoDto();
            carInfoDto.setCarId(carsEntity.getCarId());
            carInfoDto.setBrand(carsEntity.getBrand());
            carInfoDto.setSeries(carsEntity.getSeries());
            carInfoDto.setCarUrl(getPath(carsEntity.getCarId()));
            carInfoDto.setPlateTime(carsEntity.getPlateTime());
            carInfoDto.setPrice(carsEntity.getPrice());

            carInfoDto.setCountry(carsEntity.getCountry());//进口、国产

            carsInfo.add(carInfoDto);

        }
        return carsInfo;
    }

    /**
     * 查找所有用户车辆
     */
    public List<CarInfoDto> getAllInfos(int pageIndex) {
        List<CarsEntity> cars = carsDao.findAllCars(pageIndex * 5);
        List<CarInfoDto> carsInfo = new ArrayList<>();
//        for (CarsEntity carsEntity : cars) {
        for (int i = 0; i < cars.size(); i++) {
            CarsEntity carsEntity = cars.get(i);
            CarInfoDto carInfoDto = new CarInfoDto();
            carInfoDto.setIsAudit(carsEntity.getIsAudit());
            carInfoDto.setCarId(carsEntity.getCarId());
            carInfoDto.setBrand(carsEntity.getBrand());
            carInfoDto.setSeries(carsEntity.getSeries());
            Integer len = i + 1;
            carInfoDto.setStyle(len.toString());

            carsInfo.add(carInfoDto);

        }
        return carsInfo;
    }

}
