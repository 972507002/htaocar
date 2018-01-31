package study.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import study.dao.dto.ResultDto;
import study.dao.entity.CollectionEntity;
import study.dao.mapper.CarsDao;
import study.service.CarsService;
import study.service.CollectionService;

import javax.servlet.http.HttpSession;
import java.security.PublicKey;

@Controller
public class CarController {
    @Autowired
    CarsService carsService;

    @Autowired
    CollectionService collectionService;

    @Autowired
    CarsDao carsDao;

    @RequestMapping("getCar")
    public String getCar(String carId, HttpSession session, ModelMap modelMap){
        if(StringUtils.isNotBlank(carId)){
            String loginName = (String) session.getAttribute("loginName");
            carsService.getCar(carId,loginName,modelMap);
        }
        return "car";
    }
    /**搜索车辆*/
/*    @RequestMapping("searchCar")
    public String searchCar(String keyWord,String brand,String type,double age,String price,int pageIndex,ModelMap modelMap){
        if(StringUtils.isNotBlank(keyWord)){

        }
        return "buy";
    }*/

    /**收藏车辆*/
    @RequestMapping("collectCar")
    @ResponseBody
    public ResultDto collectCar(String carId,HttpSession session){
        ResultDto resultDto = new ResultDto();
        String loginName= (String) session.getAttribute("loginName");
        if(StringUtils.isBlank(loginName)){
            resultDto.setSuccess(false);
            resultDto.setMessage("请登录后进行操作");
            return resultDto;
        }
        if(StringUtils.isNotBlank(carId)){
            CollectionEntity collect = collectionService.findCollect(carId, loginName);
            if(collect!=null){
                resultDto.setSuccess(false);
                resultDto.setMessage("您已经收藏过改车辆，请勿重复收藏");
                return resultDto;
            }

            int len = collectionService.addCollect(carId, loginName);
            if(len>0){
                resultDto.setSuccess(true);
                resultDto.setMessage("收藏成功，您可以在用户中心查看收藏的车辆");
            }else {
                resultDto.setSuccess(false);
                resultDto.setMessage("收藏失败，请稍后尝试");
            }
            return resultDto;
        }else {
            resultDto.setSuccess(false);
            resultDto.setMessage("出现些问题，稍后尝试");
            return resultDto;
        }
    }


    /**取消收藏车辆*/
    @RequestMapping("uCollectCar")
    @ResponseBody
    public ResultDto uCollectCar(String carId,HttpSession session){
        ResultDto resultDto = new ResultDto();
        String loginName= (String) session.getAttribute("loginName");
        if(StringUtils.isBlank(loginName)){
            resultDto.setSuccess(false);
            resultDto.setMessage("请登录后进行操作");
            return resultDto;
        }
        if(StringUtils.isNotBlank(carId)){
            int len = collectionService.delCollect(carId, loginName);
            if(len>0){
                resultDto.setSuccess(true);
                resultDto.setMessage("取消收藏成功");
            }else {
                resultDto.setSuccess(false);
                resultDto.setMessage("取消收藏失败，请稍后尝试");
            }
            return resultDto;
        }else {
            resultDto.setSuccess(false);
            resultDto.setMessage("出现些问题，稍后尝试");
            return resultDto;
        }
    }

    /**删除收藏*/
    @RequestMapping("deleteColl")
    public String deleteColl(String carId,HttpSession session){
        String loginName= (String) session.getAttribute("loginName");
        if(StringUtils.isNotBlank(carId)&&StringUtils.isNotBlank(loginName)){
            collectionService.delCollect(carId, loginName);
        }
        return "redirect:toCollection";
    }

    /**删除我发布的车辆*/
    @RequestMapping("deleteCar")
    public String deleteCar(String carId,HttpSession session){
        String loginName= (String) session.getAttribute("loginName");
        if(StringUtils.isNotBlank(carId)&&StringUtils.isNotBlank(loginName)){
            carsDao.deleteCar(carId);
        }
        return "redirect:toRelease";
    }


    /**审核用户发布车辆*/
    @RequestMapping("passCar")
    public String passCar(String carId,HttpSession session){
        String loginName= (String) session.getAttribute("loginName");
        if(StringUtils.isNotBlank(carId)&&StringUtils.isNotBlank(loginName)){
            carsDao.passCar(carId);
        }
        return "redirect:toAudit";
    }

    /**发布车辆审核不通过*/
    @RequestMapping("dePassCar")
    public String dePassCar(String carId,HttpSession session){
        String loginName= (String) session.getAttribute("loginName");
        if(StringUtils.isNotBlank(carId)&&StringUtils.isNotBlank(loginName)){
            carsDao.dePassCar(carId);
        }
        return "redirect:toAudit";
    }

}
