package com.sky.mapper;

import com.sky.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    /**
     * 添加员工
     * @param employee
     * @return
     * create_time,create_user,id,id_number,name,password,phone,sex,status,update_time,update_user,username,create_time,create_user,id,id_number,name,password,phone,sex,status,update_time,update_user,username
     */
    @Insert("insert into employee(id,username,name,password,id_number,phone,sex,status,update_time,update_user,create_time,create_user) " +
            "values(#{id},#{username},#{name},#{password},#{idNumber},#{phone},#{sex},#{status},#{updateTime},#{updateUser},#{createTime},#{createUser})")
    int add(Employee employee);
}
