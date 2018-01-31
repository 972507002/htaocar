package study.dao.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import study.dao.entity.ContactEntity;

import java.util.List;

public interface ContactDao {

    /**添加联系建议*/
    int addAdvise(ContactEntity contactEntity);

    /**根据contactId查建议*/
    @Select("select *from contact where contactId = #{contactId} and isDelete = 0 limit 1")
    ContactEntity getcontactById(@Param("contactId") String contactId);

    @Select("select *from contact where LoginName = #{loginName} and isDelete = 0")
    List<ContactEntity> getContacts(@Param("loginName") String loginName);

    @Select("select *from contact where isDelete =0 limit #{pageIndex},5")
    List<ContactEntity> getAllContacts(@Param("pageIndex") int pageIndex);

    /**根据contactId删除建议*/
    @Delete("delete from contact where ContactId = #{contactId}")
    int deleteCont(@Param("contactId") String contactId);
}
