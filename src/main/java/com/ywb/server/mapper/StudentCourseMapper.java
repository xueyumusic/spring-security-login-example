package com.ywb.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ywb.server.beans.Course;
import com.ywb.server.beans.Student;
import com.ywb.server.beans.StudentCourse;

public interface StudentCourseMapper {

	@Insert("INSERT INTO student_course (student_id, course_id) VALUES (#{student_id}, #{course_id})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	void insert(StudentCourse studentCourse);
	
	void insertBatch(List<StudentCourse> studentCourseList);
	
	@Select("SELECT student_id FROM student_course where course_id = #{course_id}")
	List<Integer> queryStudentIdByCourseId(@Param("course_id") int course_id);
	
	
	//删除某课程下的某学生	
	@Delete("DELETE FROM student_course WHERE course_id=#{course_id} AND student_id=#{student_id} ")
	void deleteCourseStudent(StudentCourse studentCourse);
	
	
	@Select("SELECT * FROM student_course WHERE (student_id=#{student_id} AND course_id=#{course_id})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	List<StudentCourse> queryStudentCourse(StudentCourse studentCourse);
	
	@Select("SELECT * FROM student_course WHERE (student_id=#{student_id} AND course_id=#{course_id})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	List<StudentCourse> queryByIds(@Param("student_id") int student_id, @Param("course_id") int courseid);
	
	//查询某个学生在某个教育机构所选的课程ID列表;先查该学生所选的课程，再把这些课程中属于这个教育机构的过滤出来	
	@Select("SELECT * FROM course WHERE id IN "
			+"( SELECT course_id FROM teacher_course WHERE teacher_id=#{teacherID} and course_id IN "
			+ "(SELECT course_id FROM student_course WHERE student_id=#{studentID}) )")
	List<Course> getStudentCourseList(@Param("teacherID") int teacherID, @Param("studentID") int studentID);
	
	//查询某个课程都有哪些学生,需要pageIndex吗	
	@Select("SELECT * FROM student WHERE id IN "
			+"(SELECT student_id FROM student_course WHERE course_id=#{courseID})")
	List<Student> getCourseStudentList(@Param("courseID") int courseID);
	
}
