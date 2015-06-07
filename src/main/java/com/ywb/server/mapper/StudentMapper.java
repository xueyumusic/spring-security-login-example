package com.ywb.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ywb.server.beans.Course;
import com.ywb.server.beans.Student;

public interface StudentMapper {

	
	//某个教育机构添加学生
		@Insert("INSERT INTO student (name, age, gender, contact, second_contact, head_photo_url, teacher_id) "
				+ "VALUES (#{name}, #{age},#{gender},#{contact},#{second_contact},#{head_photo_url},#{teacher_id})")
		@Options(useGeneratedKeys=true, keyProperty="id")
		void insert(Student student);
		
	
		
		
	//查询某个机构的学生列表；
	@Select(" SELECT * FROM student WHERE teacher_id=#{teacherID} LIMIT #{pageSize} OFFSET #{pageIndex}*#{pageSize}")
	List<Student> getStudentList(@Param("teacherID") int teacherID, 
								@Param("pageIndex") int pageIndex, 
								@Param("pageSize") int pageSize);

	//查询某个机构的学生数目；先查这个机构有哪些课程 ，然后再查这些课程包含哪些学生；	
	@Select(" SELECT count(id) from student WHERE teacher_id=#{teacherID}")
	int getStudentListCount(@Param("teacherID") int teacherID);
	
	
	//查询某个学生的基本信息		
	@Select("SELECT * FROM student WHERE id=#{studentID}")
	Student getStudentInfo(@Param("studentID") int studentID);

	//更新某个学生的基本信息，头像问题,生日需要考虑了。。。		
	@Update("UPDATE student SET name=#{name}, age=#{age},gender=#{gender},contact=#{contact},"
			+ "second_contact=#{second_contact} WHERE id=#{studentID}")
	void updateStudentInfo(@Param("studentID") int studentID, @Param("name") String name,
							  @Param("age") int age,  @Param("gender") String gender, 
							  @Param("contact") String contact, @Param("second_contact") String second_contact);
	
	//删除某个学生		
	@Delete("DELETE FROM student WHERE id=#{studentID}")
	void deleteStudentInfo(@Param("studentID") int studentID);
	
	//根据姓名和联系方式查询某学生是否存在于学生表student中
	@Select("SELECT id FROM student WHERE teacher_id=#{teacher_id} and name=#{name} and contact=#{contact}")
	Integer queryIDbyTeacherIDNameandContact( @Param("teacher_id") int teacher_id, 
											  @Param("name") String name,
											  @Param("contact") String contact);
}
