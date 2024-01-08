package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    Employee getByUsername(String username);

    /**
     * 添加员工
     * @param employee
     * @return
     * create_time,create_user,id,id_number,name,password,phone,sex,status,update_time,update_user,username,create_time,create_user,id,id_number,name,password,phone,sex,status,update_time,update_user,username
     */
    int add(Employee employee);

    /**
     * 查询全部员工
     * @return List<Employee>
     */
    @Select("select * from employee")
    List<Employee> getAllEmployee();

    /**
     * 分页查询
     * @param employeePageQueryDTO
     * @return List<Employee>
     */
    Page<Employee> page(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 更新员工个人信息
     * @param employee
     */
    void update(Employee employee);

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    Employee getById(Long id);

}
