package com.shrmus.mapper;

import com.shrmus.pojo.UserPrivilege;
import com.shrmus.pojo.UserPrivilegeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserPrivilegeMapper {
    long countByExample(UserPrivilegeExample example);

    int deleteByExample(UserPrivilegeExample example);

    int deleteByPrimaryKey(Integer userPrivilegeId);

    int insert(UserPrivilege record);

    int insertSelective(UserPrivilege record);

    List<UserPrivilege> selectByExample(UserPrivilegeExample example);

    UserPrivilege selectByPrimaryKey(Integer userPrivilegeId);

    int updateByExampleSelective(@Param("record") UserPrivilege record, @Param("example") UserPrivilegeExample example);

    int updateByExample(@Param("record") UserPrivilege record, @Param("example") UserPrivilegeExample example);

    int updateByPrimaryKeySelective(UserPrivilege record);

    int updateByPrimaryKey(UserPrivilege record);
}