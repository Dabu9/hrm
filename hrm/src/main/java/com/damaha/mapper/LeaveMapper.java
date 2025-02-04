package com.damaha.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.damaha.entity.Leave;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 请假表 Mapper 接口
 * </p>
 *
 * @author qiujie
 * @since 2022-03-27
 */

public interface LeaveMapper extends BaseMapper<Leave> {

    @Select("select * from att_leave where is_deleted = 0 and dept_id = #{id}")
    List<Leave> findByDeptId(@Param("id") Integer id);

}
