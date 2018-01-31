package study.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import study.dao.dto.PersonDTO;
import study.dao.entity.PersonEntity;

import java.util.List;

public interface PersonDao {

    @Select("select *from person where loginName=#{loginName} and isDelete = 0 limit 1")
    PersonEntity getPerson(@Param("loginName") String loginName);

    @Select("select *from person where loginName = #{loginName} and PersonType = #{personType} and isDelete = 0 limit 1")
    PersonEntity findPerson(@Param("loginName") String loginName, @Param("personType") int personType);

    int save(PersonEntity personEntity);

    @Update("update person set isDelete = 1 where LoginName = #{loginName}")
    void delete(@Param("loginName") String loginName);

    @Update("update person set PersonType = 0 where LoginName = #{loginName}")
    void powPerson(@Param("loginName") String loginName);

    @Select("select *from person where isDelete = 0 limit #{pageIndex},5")
    List<PersonEntity> getPersons(@Param("pageIndex") int pageIndex);

    @Update("update person set NiChen = #{niChen},PassWord = #{passWord} where LoginName = #{loginName}")
    int updatePer(@Param("niChen") String niChen,@Param("passWord") String passWord,@Param("loginName") String loginName);
}
