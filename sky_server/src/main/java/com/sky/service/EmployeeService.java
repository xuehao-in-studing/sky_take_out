package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.dto.PasswordEditDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 添加员工
     * @param employeeDTO 前端传输的员工信息
     * @return Employee 添加成功后的员工信息，存入到数据库
     */
    Employee add(EmployeeDTO employeeDTO);

    /**
     * 员工分页查询
     * @param employeePageQueryDTO 前端传参，包含员工姓名、页码、每页显示记录数
     * @return PageResult 分页查询结果
     */
    PageResult page(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 根据id更新员工账号状态
     * @param status 账号状态,1激活，0冻结
     * @param id 员工id
     */
    void update(Integer status, Long id);

    /**
     * 修改员工个人信息
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);

    /**
     * 修改密码
     * @param passwordEditDTO
     * @return
     */
    void update(PasswordEditDTO passwordEditDTO);

    Employee getById(Long id);
}
