package com.damaha.service;

/**
 * @author Dabu9
 * @Date 2023/9/19 14:43
 *
 */
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.damaha.dto.Response;
import com.damaha.dto.ResponseDTO;
import com.damaha.entity.Account;
import com.damaha.mapper.ForgetMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ForgetService extends ServiceImpl<ForgetMapper, Account> {
    @Resource
    StaffService staffService;
    @Resource
    ForgetMapper forgetMapper;
    public ResponseDTO addForgetAccount(Account account){
        Object data = staffService.findIdByName(account.getName()).getData();
        if(data != null){
            Integer id = (Integer) data;
            if(forgetMapper.selectById(id)==null){
                account.setId(id);
                account.setIsForget(1);

                // 将数据保存到数据库
                save(account);
                return Response.success();
            }
            return Response.error("重复提交，请等待管理员审核");
        }
        return Response.error("未找到用户名，请咨询管理员");

    }
}
