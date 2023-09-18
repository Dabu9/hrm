package com.damaha.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.damaha.dto.Response;
import com.damaha.dto.ResponseDTO;
import com.damaha.entity.Staff;
import com.damaha.enums.BusinessStatusEnum;
import com.damaha.mapper.StaffMapper;
import com.damaha.util.JWTUtil;
import com.damaha.util.MD5Util;
import com.damaha.vo.StaffDeptVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 登陆服务
 */
@Service
public class LoginService extends ServiceImpl<StaffMapper, Staff> {

    @Resource
    private StaffMapper staffMapper;

    public ResponseDTO login(Staff staff) {
        String password = MD5Util.MD55(staff.getPassword());
        StaffDeptVO staffDeptVO = this.staffMapper.findStaffInfo(staff.getCode(), password);
        if (staffDeptVO != null) {
            // 验证用户状态
            if (staffDeptVO.getStatus() == 1) {
                String token = JWTUtil.generateToken(staffDeptVO.getId(),password);
                return Response.success(staffDeptVO, token); // 返回员工信息和token
            }
            return Response.error(BusinessStatusEnum.STAFF_STATUS_ERROR);
        }
        return Response.error("用户名或密码错误!");
    }
}
