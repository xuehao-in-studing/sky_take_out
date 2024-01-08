package com.sky.controller.admin;

import com.sky.aop.FormatValidator;
import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @Operation(summary = "员工登录")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    @Operation(summary = "员工退出")
    public Result<String> logout() {
        return Result.success();
    }

    @PostMapping()
    @Operation(summary = "添加员工")
    public Result<Employee> add(@RequestBody EmployeeDTO employeeDTO) {
        log.info("添加员工：{}", employeeDTO);
        Employee employee = employeeService.add(employeeDTO);
        if(employee == null) {
            return Result.error("添加失败");
        }
        return Result.success(employee);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询员工")
    @Parameters({
            @Parameter(name = "name", description = "员工姓名", required = false),
            @Parameter(name = "page", description = "页码", required = true),
            @Parameter(name = "pageSize", description = "每页显示记录数", required = true)
    })
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("分页查询员工");
        PageResult pageResult = employeeService.page(employeePageQueryDTO);
        if(pageResult == null) {
            return Result.error("查询失败");
        }
        return Result.success(pageResult);
    }

    @PostMapping("/status/{status}")
    @Operation(summary = "更新员工信息")
    public Result<String> update(@PathVariable("status") Integer status,Long id) {
        log.info("更新员工信息：{}", id);
        employeeService.update(status,id);
        return Result.success();
    }

}
