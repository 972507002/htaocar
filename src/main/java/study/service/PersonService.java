package study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.common.UUID;
import study.dao.dto.PersonDTO;
import study.dao.entity.PersonEntity;
import study.dao.mapper.PersonDao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/10/20.
 */
@Service
public class PersonService {

    @Autowired
    PersonDao personDao;

    public boolean getPerson(String loginName){
        PersonEntity personEntity=personDao.getPerson(loginName);
        if(personEntity==null){
            return false;
        }
        return true;
    }

    public PersonEntity getPersonEntity(String loginName){
        PersonEntity personEntity=personDao.getPerson(loginName);
        return personEntity;
    }

    /**根据用户名和密码查询用户是否存在*/
    public PersonEntity findPerson(String loginName,String type){
        int personType = Integer.parseInt(type);
        PersonEntity personEntity=personDao.findPerson(loginName,personType);
        return personEntity;
    }

    /**添加普通买家*/
    public int addUser(String loginName,String password){
        PersonEntity personEntity=new PersonEntity();
        personEntity.setPersonId(UUID.getUUID());
        personEntity.setLoginName(loginName);
        personEntity.setPassWord(password);
        personEntity.setPersonType(1);
        personEntity.setAddTime(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(System.currentTimeMillis())));
        return personDao.save(personEntity);
    }

    /**管理员查询所有用户*/
    public List<PersonDTO> getUsers(int pageIndex){
        List<PersonDTO> persons = new ArrayList<>();
        List<PersonEntity> pers = personDao.getPersons(pageIndex*5);
//        for(PersonEntity person:pers) {
        for (int i = 0; i <pers.size() ; i++) {
            PersonEntity person = pers.get(i);
            PersonDTO personDTO = new PersonDTO();
            personDTO.setLoginName(person.getLoginName());
            personDTO.setPersonType(person.getPersonType());
            personDTO.setOrd(i+1);
            persons.add(personDTO);
        }
        return persons;

    }


}
