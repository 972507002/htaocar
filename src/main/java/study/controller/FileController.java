package study.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import study.dao.dto.CarDto;
import study.dao.dto.ResultDto;
import study.dao.entity.PersonEntity;
import study.service.CarsService;
import study.service.FileService;
import study.service.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class FileController {
    @Autowired
    PersonService personService;

    @Autowired
    CarsService carsService;

    @Autowired
    FileService fileService;



    @RequestMapping("/addCar")
    @ResponseBody
    public ResultDto addCar(CarDto carDto, HttpSession session){
        ResultDto resultDto=new ResultDto();
        String loginName= (String) session.getAttribute("loginName");
        System.out.println(loginName);
        if(StringUtils.isBlank(loginName)){
            resultDto.setSuccess(false);
            resultDto.setMessage("没有您的登陆信息，请登录后操作");
            return resultDto;
        }
        String carId;
        PersonEntity personEntity=personService.getPersonEntity(loginName);
        if(personEntity!=null){
            carId=carsService.addCar(carDto,personEntity);
            if(StringUtils.isNotBlank(carId)){
                resultDto.setSuccess(true);
                resultDto.setMessage("上传成功，请为您的车辆上传图片");
                /**只能用session来存储carId给上传图片的用了*/
                session.setAttribute("carId",carId);
            }
        }else {
            resultDto.setSuccess(false);
            resultDto.setMessage("操作失败");
        }
        return resultDto;
    }

    @RequestMapping("/toUpload")
    public String toUpload(){
        return "upload";
    }


    @RequestMapping(value = "/uploadPhoto", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto handleFileUpload(HttpServletRequest request) {
        ResultDto resultDto = new ResultDto();
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");
        HttpSession session = request.getSession();
        String carId= (String) session.getAttribute("carId");
        if(StringUtils.isBlank(carId)){
            resultDto.setSuccess(false);
            resultDto.setMessage("请先上传车辆信息");
            return resultDto;
        }
        String result=fileService.upPhoto(carId, files);
        if("上传图片成功".equals(result)){
            resultDto.setSuccess(true);
            resultDto.setMessage(result);
        }
        return resultDto;
    }

}
