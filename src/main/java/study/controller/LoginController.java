package study.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import study.common.SmsInstrument;
import study.dao.dto.ResultDto;
import study.dao.entity.PersonEntity;
import study.dao.mapper.PersonDao;
import study.service.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    PersonService personService;

    @Autowired
    PersonDao personDao;

    //获取验证码
    @RequestMapping("/getCode")
    @ResponseBody
    public String getCode(String phoneNumber, HttpServletRequest request) {
        Integer code = (int) ((Math.random() * 9 + 1) * 1000);
        if (phoneNumber != null) {
            /**手机发送验证码*/
            SmsInstrument.sendCode(phoneNumber, code.toString());
            /** 验证码存入session
             */
            HttpSession session = request.getSession();
            session.setAttribute("code", code);
            logger.info("验证码：" + code + " 已经发送至：" + phoneNumber);
            return "验证码已发送";
        }
        return "获取验证码失败";
    }


    @RequestMapping("/register")
    @ResponseBody
    public ResultDto register(String loginName, String password, String code, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ResultDto resultDto = new ResultDto();
        if (StringUtils.isBlank(loginName) || StringUtils.isBlank(password)) {
            resultDto.setSuccess(false);
            resultDto.setMessage("用户名或密码不能为空!");
            return resultDto;
        }
        Object sessionCode = session.getAttribute("code");
        if (StringUtils.isBlank(code)) {
            resultDto.setSuccess(false);
            resultDto.setMessage("验证码不能为空!");
            return resultDto;
        } else if (!code.equals(sessionCode.toString())) {
            resultDto.setSuccess(false);
            resultDto.setMessage("验证码错误!");
            return resultDto;
        } else {
            /**添加账户，跳入登录页面，删除验证码*/
            session.removeAttribute("code");

//            System.out.println("loginName" + loginName + "password" + password + "code" + code);
            logger.info("用户进入注册流程 LoginName:" + loginName);

            //判断是否注册用户
            boolean isregister = personService.getPerson(loginName);
            if (isregister) {
                resultDto.setSuccess(false);
                resultDto.setMessage("用户号码已注册，请登录");
            } else {
                int len = personService.addUser(loginName, password);
                if (len > 0) {
                    resultDto.setSuccess(true);
                    resultDto.setMessage("注册成功，请登录");
                } else {
                    /**否则返回注册页面，抛出错误*/
                    resultDto.setSuccess(false);
                    resultDto.setMessage("注册失败");
                }

            }
            return resultDto;

        }

    }


    @RequestMapping("/login")
    @ResponseBody
    public ResultDto login(String loginName, String type, String password, HttpSession session) {
        /***/
        ResultDto resultDto = new ResultDto();
        if (StringUtils.isBlank(loginName) || StringUtils.isBlank(password)) {
            resultDto.setSuccess(false);
            resultDto.setMessage("用户名或密码不能为空!");
            return resultDto;
        }
        if (StringUtils.isBlank(type)) {
            resultDto.setSuccess(false);
            resultDto.setMessage("请选择用户类型!");
            return resultDto;
        }

        int personType = Integer.parseInt(type);
        PersonEntity person = personService.getPersonEntity(loginName);
        if (person == null) {
            resultDto.setSuccess(false);
            resultDto.setMessage("用户不存在!");
        } else if (!person.getPassWord().equals(password)) {
            resultDto.setSuccess(false);
            resultDto.setMessage("密码错误!");
        } else if (person.getPersonType() > personType) {
            resultDto.setSuccess(false);
            resultDto.setMessage("对不起，您不是管理员!");
        } else {
            resultDto.setSuccess(true);
            session.setAttribute("loginName", loginName);
            //普通用户
            if (type.equals("1")) {
                resultDto.setMessage("toRelease");
            } else {
                session.setAttribute("personType", type);
                resultDto.setMessage("toAudit");
            }

        }
        return resultDto;

    }

    /**
     * 退出登录
     */
    @RequestMapping("/logOff")
    public String logOff(HttpSession session) {
        session.removeAttribute("loginName");
        session.removeAttribute("personType");
        return "login";
    }

    /**
     * 修改个人信息
     */
    @RequestMapping("/changePass")
    @ResponseBody
    public ResultDto changePass(String niChen,String passWord,HttpSession session) {
        ResultDto resultDto = new ResultDto();
        String loginName= (String) session.getAttribute("loginName");
        if(StringUtils.isBlank(loginName)){
            resultDto.setSuccess(false);
            resultDto.setMessage("请登录后进行操作");
            return resultDto;
        }
        if(StringUtils.isBlank(niChen)&&StringUtils.isBlank(passWord)){
            resultDto.setSuccess(false);
            resultDto.setMessage("请填写完整信息");
            return resultDto;
        }else {
            int len = personDao.updatePer(niChen, passWord, loginName);
            if(len>0){
                resultDto.setSuccess(true);
                resultDto.setMessage("信息修改成功");
            }else {
                resultDto.setSuccess(false);
                resultDto.setMessage("修改失败");
            }
            return resultDto;
        }

    }

    /**给用户提升权限*/
    @RequestMapping("/powerPer")
    public String powerPer(String loginName,HttpSession session){
        String personType= (String) session.getAttribute("personType");
        if (StringUtils.isNotBlank(loginName)&&("0".equals(personType))) {
            personDao.powPerson(loginName);
        }
        return "redirect:toUsers";
    }


    /**封禁用户*/
    @RequestMapping("/deletePer")
    public String deletePer(String loginName,HttpSession session){
        String personType= (String) session.getAttribute("personType");
        if (StringUtils.isNotBlank(loginName)&&("0".equals(personType))) {
            personDao.delete(loginName);
        }
        return "redirect:toUsers";
    }


}
