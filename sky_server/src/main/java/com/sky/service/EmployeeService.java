package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
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
}
