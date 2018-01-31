package study.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import study.dao.dto.*;
import study.dao.entity.ContactEntity;
import study.dao.mapper.ContactDao;
import study.service.CarsService;
import study.service.CollectionService;
import study.service.PersonService;
import study.service.ReservationService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    CarsService carsService;

    @Autowired
    PersonService personService;

    @Autowired
    ContactDao contactDao;

    @Autowired
    ReservationService reservationService;

    @Autowired
    CollectionService collectionService;

    //index页面
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/toIndex")
    public String toIndex() {
        return "index";
    }

    @RequestMapping("/toSell")
    public String toSell() {
        return "sell";
    }

    @RequestMapping("/toBuy")
    public String toBuy(SearchDto searchDto, ModelMap map) {
        int pageIndex = searchDto.getPageIndex();
        if (pageIndex > 0) {
            searchDto.setPageIndex(pageIndex - 1);
        }
        List<CarInfoDto> carInfoDtos = carsService.search(searchDto);
        map.addAttribute("carInfoDtos", carInfoDtos);
//        PageDto pageDto = new PageDto();
//        pageDto.set
        map.addAttribute("id", searchDto.getId());
        map.addAttribute("pageIndex", pageIndex);
        return "buy";
    }

    @RequestMapping("/toGujia")
    public String toGujia() {
        return "gujia";
    }

    @RequestMapping("/toNews")
    public String toNews() {
        return "news";
    }

    @RequestMapping("/toNew")
    public String toNew() {
        return "new";
    }

    @RequestMapping("/toContact")
    public String toContact() {
        return "contact";
    }

    //进入登录页面
    @RequestMapping("/toLogin")
    public String login() {
        return "login";
    }

    @RequestMapping("/toCar")
    public String toCar() {
        return "car";
    }

    @RequestMapping("/toUpPhoto")
    public String toUpPhoto() {
        return "upPhoto";
    }

    //进入注册页面
    @RequestMapping("/toRegister")
    public String register(HttpSession session) {
        String loginName = (String) session.getAttribute("loginName");
        if (StringUtils.isNotBlank(loginName)) {
            String personType = (String) session.getAttribute("personType");
            if (("0").equals(personType)) {   //管理员登陆进入管理员界面
                return "redirect:toAudit";
            } else {
                return "redirect:toRelease";
            }
        }
        return "register";
    }

    //测试用户中心
    @RequestMapping("/toPerson")
    public String person() {
        return "person";
    }

    @RequestMapping("/toCollection")
    public String toCollection(Integer pageIndex, HttpSession session, ModelMap map) {
        Integer theId = pageIndex;
        if (null == pageIndex) {
            pageIndex = 0;
        } else if (pageIndex > 0) {
            pageIndex = pageIndex - 1;
        }
        String loginName = (String) session.getAttribute("loginName");
        List<CarInfoDto> carInfoDtos = collectionService.getCollects(loginName, pageIndex);
        map.addAttribute("carInfoDtos", carInfoDtos);
        map.addAttribute("id", theId);
        return "collection";
    }

    //发布车辆
    @RequestMapping("/toRelease")
    public String toRelease(Integer pageIndex, HttpSession session, ModelMap map) {
        Integer theId = pageIndex;
        if (null == pageIndex) {
            pageIndex = 0;
        } else if (pageIndex > 0) {
            pageIndex = pageIndex - 1;
        }
        String loginName = (String) session.getAttribute("loginName");
        List<CarInfoDto> carInfoDtos = carsService.getInfos(loginName, pageIndex);
        map.addAttribute("carInfoDtos", carInfoDtos);
        map.addAttribute("id", theId);
        return "release";
    }

    //车辆管理
    @RequestMapping("/toAudit")
    public String toAudit(Integer pageIndex, ModelMap map) {
        Integer theId = pageIndex;
        if (null == pageIndex) {
            pageIndex = 0;
        } else if (pageIndex > 0) {
            pageIndex = pageIndex - 1;
        }
        List<CarInfoDto> carInfoDtos = carsService.getAllInfos(pageIndex);
        map.addAttribute("carInfoDtos", carInfoDtos);
        map.addAttribute("id", theId);
        return "audit";
    }

    //留言管理,直接查询留言
    @RequestMapping("/toMess")
    public String toMess(Integer pageIndex,ModelMap map) {
        Integer theId = pageIndex;
        if (null == pageIndex) {
            pageIndex = 0;
        } else if (pageIndex > 0) {
            pageIndex = pageIndex - 1;
        }

        List<ContactDto> contactDtos = new ArrayList<>();
        List<ContactEntity> contactEntities = contactDao.getAllContacts(pageIndex * 5);
        for (int i = 0; i < contactEntities.size(); i++) {
            ContactEntity contactEntity = contactEntities.get(i);
            ContactDto contactDto = new ContactDto();
            contactDto.setContactId(contactEntity.getContactId());
            contactDto.setAddTime(contactEntity.getAddTime().toString().substring(0, 10));
            contactDto.setEmail(contactEntity.getEmail());
            contactDto.setOrd(i + 1);
            contactDtos.add(contactDto);
        }
        map.addAttribute("contactDtos", contactDtos);
        map.addAttribute("id", theId);
        return "mess";
    }

    //我的预约
    @RequestMapping("/toReservation")
    public String toReservation(Integer pageIndex,ModelMap map, HttpSession session) {
        Integer theId = pageIndex;
        if (null == pageIndex) {
            pageIndex = 0;
        } else if (pageIndex > 0) {
            pageIndex = pageIndex - 1;
        }

        String loginName = (String) session.getAttribute("loginName");
        if (StringUtils.isNotBlank(loginName)) {
            List<ReservDto> reservDtos = reservationService.getReservs(loginName,pageIndex);
            map.addAttribute("reservDtos", reservDtos);
            map.addAttribute("id", theId);
        }
        return "reservation";
    }

    //预约管理
    @RequestMapping("/toReservations")
    public String toReservations(Integer pageIndex,ModelMap map, HttpSession session) {
        Integer theId = pageIndex;
        if (null == pageIndex) {
            pageIndex = 0;
        } else if (pageIndex > 0) {
            pageIndex = pageIndex - 1;
        }
        String loginName = (String) session.getAttribute("loginName");
        String personType = (String) session.getAttribute("personType");
        if ("0".equals(personType)) {
            loginName = "";
        }
        List<ReservDto> reservDtos = reservationService.getReservs(loginName,pageIndex);
        map.addAttribute("reservDtos", reservDtos);
        map.addAttribute("id", theId);
        return "reservations";
    }


    //人员管理
    @RequestMapping("/toUsers")
    public String toPers(Integer pageIndex, ModelMap map) {
        Integer theId = pageIndex;
        if (null == pageIndex) {
            pageIndex = 0;
        } else if (pageIndex > 0) {
            pageIndex = pageIndex - 1;
        }

        List<PersonDTO> personDTOS = personService.getUsers(pageIndex);
        map.addAttribute("personDtos", personDTOS);
        map.addAttribute("id", theId);
        return "users";
    }


}
