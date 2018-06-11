package com.cms.service;

import com.cms.domain.CourseCodeInfo;
import com.cms.domain.CourseDtlInfo;
import com.cms.domain.CourseInfo;
import com.cms.domain.StudentInfo;
import com.cms.mapper.CourseDtlMapper;
import com.cms.vo.BasePageParameter;
import com.cms.vo.CourseDtlVO;
import com.cms.vo.CourseVO;
import com.core.exception.MyException;
import com.core.page.Pagination;

import java.util.Date;
import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/8 20:27
 */
public interface CourseDtlService extends BaseService<CourseDtlInfo, CourseDtlVO> {

    void offOrOn(Long id, Integer state);

    // FIXME remove this interface Deprecated
    @Deprecated
    public Pagination<CourseDtlInfo> getCourseListTeacher(String teacherAccount, BasePageParameter basePageParameter);
    // FIXME remove this interface Deprecated
    @Deprecated
    public Pagination<CourseDtlInfo> getCourseListStudent(StudentInfo student, BasePageParameter basePageParameter);

    public List<CourseDtlInfo> getCourseListTeacher(String teacherAccount, Long academicYearId, String semester);

    public List<CourseDtlInfo> getCourseListStudent(StudentInfo student, Long academicYearId, String semester);

    public CourseDtlVO findOneVO(Long courseDtlId);

    public void modifySingleCoursePlace(Long courseDtlId, String teacherAccount, String place) throws MyException;

    public void modifyCoursePlace(Long courseCode, String teacherAccount, String place) throws MyException;

    public void addCourseStudent(Long courseCode, String teacherAccount, String containStudent) throws MyException;

    public void addCourseStudent(Long courseCode, String teacherAccount, List<String> containsStudent) throws MyException;

    public void deleteCourseStudent(Long courseCode, String teacherAccount, String exportStudent) throws MyException;

    public void deleteCourseStudent(Long courseCode, String teacherAccount, List<String> exportsStudent) throws MyException;

    public void addSingleCourse(CourseCodeInfo courseCodeInfo, String place, Date date, Integer scheduleName) throws MyException;

    public void deleteSingleCourse(Long courseDtlId, Long courseCode, String teacherAccount) throws MyException;


}
