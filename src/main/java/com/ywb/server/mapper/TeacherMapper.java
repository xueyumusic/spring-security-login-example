package com.ywb.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ywb.server.beans.Teacher;

public interface TeacherMapper {

	@Select("SELECT * FROM teacher where id = #{id}")
	//@Select("SELECT * FROM teacher where id = #{id}")
	List<Teacher> queryByTeacherId(@Param("id") Integer teacher_id);
	
	@Insert("INSERT INTO teacher (name, age, gender, contact) VALUES (#{name}, #{age}, #{gender}, #{contact})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	void insert(Teacher teacher);
	
	@Delete("DELETE FROM teacher WHERE id = #{id}")
	void delete(@Param("id") Integer teacher_id);
	
	@Select("SELECT * FROM teacher where name = #{name} and contact = #{contact}")
	List<Teacher> queryByTeacherNameAndContact(@Param("name") String name, @Param("contact") String contact);
	
}
