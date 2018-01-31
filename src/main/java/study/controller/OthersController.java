package study.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import study.common.BrandPrice;
import study.common.UUID;
import study.dao.dto.GuJiaDto;
import study.dao.dto.ReservDto;
import study.dao.dto.ResultDto;
import study.dao.entity.ContactEntity;
import study.dao.mapper.ContactDao;
import study.dao.mapper.ReservationDao;
import study.service.ReservationService;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
public class OthersController {
    @Autowired
    ContactDao contactDao;

    @Autowired
    ReservationService reservationService;

    @Autowired
    ReservationDao reservationDao;


    /**
     * 保存建议
     */
    @RequestMapping("/sendAdv")
    @ResponseBody
    public ResultDto sendAdv(String niChen, String email, String advise, HttpSession session) {
        ResultDto resultDto = new ResultDto();
        String loginName = (String) session.getAttribute("loginName");
        if (StringUtils.isBlank(loginName)) {
            resultDto.setSuccess(false);
            resultDto.setMessage("请登录后进行曹组");
            return resultDto;
        }

        if (StringUtils.isBlank(niChen) || StringUtils.isBlank(email) || StringUtils.isBlank(advise)) {

            resultDto.setSuccess(false);
            resultDto.setMessage("请把信息填写完整");
        } else {
            ContactEntity contactEntity = new ContactEntity();
            contactEntity.setContactId(UUID.getUUID());
            contactEntity.setLoginName(loginName);
            contactEntity.setEmail(email);
            contactEntity.setNiChen(niChen);
            contactEntity.setAdvise(advise);
            contactEntity.setIsDelete(0);
            contactEntity.setIsAudit(0);
            contactEntity.setAddTime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(System.currentTimeMillis())));
            int len = contactDao.addAdvise(contactEntity);
            if (len > 0) {
                resultDto.setSuccess(true);
                resultDto.setMessage("上传成功，管理员收到您的建议后会跟您联系");
            } else {
                resultDto.setSuccess(false);
                resultDto.setMessage("上传失败");
            }
        }
        return resultDto;
    }

    /**
     * 查询建议
     */
    @RequestMapping("/getDetail")
    @ResponseBody
    public ResultDto getDetail(String contactId) {
        ResultDto resultDto = new ResultDto();
        if (StringUtils.isNotBlank(contactId)) {
            ContactEntity contactEntity = contactDao.getcontactById(contactId);
            if (null != contactEntity) {
                resultDto.setSuccess(true);
                resultDto.setMessage(contactEntity.getNiChen() + "：" + contactEntity.getAdvise());
            } else {
                resultDto.setSuccess(false);
                resultDto.setMessage("查询失败，稍后查询试试");
            }
            return resultDto;
        } else {
            resultDto.setSuccess(false);
            resultDto.setMessage("contactId没找到，待会试试吧");
            return resultDto;
        }
    }

    /**
     * 删除建议
     */
    @RequestMapping("/deleteCont")
    public String deleteCont(String contactId, HttpSession session) {
        String loginName = (String) session.getAttribute("loginName");
        if (StringUtils.isNotBlank(contactId) && StringUtils.isNotBlank(loginName)) {
            contactDao.deleteCont(contactId);
        }
        return "redirect:toMess";
    }


    /**
     * 预约
     */
    @RequestMapping("/addReserv")
    @ResponseBody
    public ResultDto addReserv(ReservDto reservDto, HttpSession session) {

        ResultDto resultDto = new ResultDto();
        String loginName = (String) session.getAttribute("loginName");
        if (StringUtils.isBlank(loginName)) {
            resultDto.setSuccess(false);
            resultDto.setMessage("请登录后进行操作");
            return resultDto;
        }

        if (StringUtils.isBlank(reservDto.getNiChen()) || StringUtils.isBlank(reservDto.getPhoneNum())
                || StringUtils.isBlank(reservDto.getCarId())) {
            resultDto.setSuccess(false);
            resultDto.setMessage("请把信息填写完整");
            return resultDto;
        } else {
            int len = reservationService.addReserv(reservDto, loginName);
            if (len > 0) {
                resultDto.setSuccess(true);
                resultDto.setMessage("预约成功，静候佳音");
            } else {
                resultDto.setSuccess(false);
                resultDto.setMessage("预约失败，请稍后重试");
            }
            return resultDto;
        }


    }

    /**
     * 删除预约
     */
    @RequestMapping("/deleteRe")
    public String deleteRe(String reservId, HttpSession session) {
        String loginName = (String) session.getAttribute("loginName");
        if (StringUtils.isNotBlank(reservId) && StringUtils.isNotBlank(loginName)) {
            //直接用dao删了
            reservationDao.deleteRe(reservId);
        }
        return "redirect:toReservation";
    }


    /**
     * 评估
     */
    @RequestMapping("/valuebe")
    @ResponseBody
    public ResultDto toValuebe(GuJiaDto guDto) {
        ResultDto resultDto = new ResultDto();

        Calendar cal = Calendar.getInstance();
        Double nowYear = cal.get(Calendar.YEAR) * 1.0;
        Double nowMonth = cal.get(Calendar.MONTH) + 1.0;

        Double price = 0.00;
        if (StringUtils.isNotBlank(guDto.getSeries())) { //判定品牌车系
            if (guDto.getSeries().indexOf(BrandPrice.Axi.getDescription()) != -1) {
                price = BrandPrice.Axi.getValue();
            } else if (guDto.getSeries().indexOf(BrandPrice.Qxi.getDescription()) != -1) {
                price = BrandPrice.Qxi.getValue();

            } else if (guDto.getSeries().indexOf(BrandPrice.Txi.getDescription()) != -1) {
                price = BrandPrice.Txi.getValue();
            } else if (guDto.getSeries().indexOf(BrandPrice.Sxi.getDescription()) != -1) {
                price = BrandPrice.Sxi.getValue();
            }
            if (StringUtils.isNotBlank(guDto.getYear())) {
                Double pick = (nowYear - Double.parseDouble(guDto.getYear()));
                if (StringUtils.isNotBlank(guDto.getMonth())) {
                    pick = pick + ((nowMonth - Double.parseDouble(guDto.getMonth())) * 0.1);
                }
                price = price * 0.8; //第一年折价20%
                if (pick > 1)
                    price = price - pick*(Math.pow(0.9, (pick - 1))); //第一年折价20%，往后年每年折价10%，月份安小数位波动
            }
            if (StringUtils.isNotBlank(guDto.getMileage())) {
                String mile = guDto.getMileage() + "00";
                mile = mile.substring(0, 2);
                price = price - price * 0.2 * (Double.parseDouble(mile) * 0.01); //根据行驶里程折价
            }
            if (StringUtils.isNotBlank(guDto.getWron())) {
                price = price * Double.parseDouble(guDto.getWron())*0.1; //根据磨损车堵估价
            }

            price = (double) Math.round(price * 100) / 100;//价格净化为保留两个小数位

            resultDto.setSuccess(true);
            resultDto.setMessage("该车辆估值为：￥" + price + "万！  我们将会把估值信息发送至您的手机！");

        } else {
            resultDto.setSuccess(false);
            resultDto.setMessage("估值失败，请详细填写估值信息");
        }

        return resultDto;
    }


}
