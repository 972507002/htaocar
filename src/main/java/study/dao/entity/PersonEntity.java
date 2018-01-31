package study.dao.entity;

import java.sql.Timestamp;

public class PersonEntity {

    private String personId;
    /**用户登录名，默认为用户手机号码为唯一登录方式*/
    private String loginName;
    /**用户称谓，用户账户名，系统中可存在重复称呼*/
    private String niChen;
    private String passWord;
    private Timestamp addTime;
    /**用户类型，0 正常用户（买家），1 卖家*/
    private int personType;
    private int isDelete;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNiChen() {
        return niChen;
    }

    public void setNiChen(String niChen) {
        this.niChen = niChen;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    public int getPersonType() {
        return personType;
    }

    public void setPersonType(int personType) {
        this.personType = personType;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
