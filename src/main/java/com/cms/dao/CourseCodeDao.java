package com.cms.dao;

import com.cms.domain.CourseCodeInfo;
import com.cms.mapper.CourseCodeMapper;
import com.core.dao.BaseDao;
import com.core.dao.query.FilterMap;
import com.core.exception.MyException;
import com.core.util.enums.ConstantEnum;
import org.springframework.stereotype.Repository;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/5/1 12:57
 */
@Repository
public class CourseCodeDao extends BaseDao<CourseCodeInfo, CourseCodeMapper> {

    public  CourseCodeInfo findOne(Long courseCode, String teacherAccount){
        FilterMap map = new FilterMap();
        map.eq("course_code", courseCode);
        map.eq("teacher_account", teacherAccount);
        return super.findOne(map);
    }

    /**
     * 更新某一课程的 上课学生 增删
     * @param courseCode 课程代码
     * @param contains 包含学生   （额外）
     * @param exports  排除学生
     */
    public void updateAllStudent(Long courseCode, String contains, String exports) throws MyException {
        try{
            mapper.updateAllStudent(courseCode, contains, exports);
        }catch(Exception e){
            throw new MyException(ConstantEnum.SYS_ERROR, e.getMessage());
        }
    }

    @Override
    public CourseCodeInfo findOne(Long courseCode) {
        FilterMap filterMap = new FilterMap();
        filterMap.eq("course_code", courseCode);
        return findOne(filterMap);
    }
}
