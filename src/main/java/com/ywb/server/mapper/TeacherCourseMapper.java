package com.ywb.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ywb.server.beans.TeacherCourse;

public interface TeacherCourseMapper {

	//查询某个老师所管理的课程
	@Select("SELECT * FROM teacher_course WHERE teacher_id = #{teacher_id}")
	List<TeacherCourse> queryByTeacherId(@Param("teacher_id") Integer teacher_id);
	
	//查询某个老师管理的某个课程是否存在；或者某个课程是否由该老师管理
	@Select("SELECT * FROM teacher_course WHERE teacher_id=#{teacher_id} AND course_id=#{course_id}")
	TeacherCourse queryTeacherCourse(@Param("teacherCourse") TeacherCourse teacherCourse);
	
}
