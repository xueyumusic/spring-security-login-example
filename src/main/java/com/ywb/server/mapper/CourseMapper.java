package com.ywb.server.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ywb.server.beans.Course;

public interface CourseMapper {

	@Select("SELECT id FROM course WHERE course_name= #{courseName} AND teacher_id = #{teacherID}")
	Integer queryCourseIDbyCourseName(@Param("courseName") String courseName, @Param("teacherID")int teacherID );//课程名如果重名怎么办？需要让老师命名时必须加入日期？每个表都要有一个真正的唯一标示
	
	void insert(Course course);
	
	void update(Course course);
	
	@Delete("DELETE FROM student WHERE id=#{courseID}")
	void delete(@Param("courseID") int courseID);
		
	
}
