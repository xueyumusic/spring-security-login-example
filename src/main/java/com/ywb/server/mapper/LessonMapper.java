package com.ywb.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.ywb.server.beans.Lesson;
import com.ywb.server.utils.StringArrayTypeHandler;

public interface LessonMapper {

	//查询某课程下共有多少堂课
	@Select(" SELECT count(id) from lesson WHERE course_id=#{courseID}")
	int getLessonCount(@Param("courseID") int courseID);
	
	//查询某课程的课堂列表
	@Select("SELECT * FROM lesson WHERE course_id=#{courseID} LIMIT #{pageSize} OFFSET #{pageIndex}*#{pageSize}")
	@Results(value = {
            @Result(column="summary_photo", property="summary_photo", jdbcType=JdbcType.ARRAY, typeHandler=StringArrayTypeHandler.class)
	})
	List<Lesson> queryLessonList(@Param("courseID") int courseID,
							 	 @Param("pageIndex") int pageIndex, 
							 	 @Param("pageSize") int pageSize);

	
	
	//删除一堂课
	@Delete("DELETE FROM lesson WHERE id=#{lessonID}")
	void deleteLesson(@Param("lessonID") int lessonID);
	
	//为一堂课添加课堂精要
//	@Insert("INSERT INTO lesson (course_id, summary_text,summary_photo,summary_video) VALUES "
//			+ "( #{course_id}, #{summary_text},#{summary_photo},#{summary_video})")
//	@Options(useGeneratedKeys=true, keyProperty="id")
	void insertSummary(Lesson lesson);
//	数据库端的写法
//	INSERT INTO lesson (course_id, summary_text,summary_photo,summary_video) VALUES (2, 'this is a test','{localhost/1.jpg,localhost/2.jpg}','localhost/1.video')

	
	void insertHomework(Lesson lesson);
	
//	@Update("UPDATE lesson SET summary_text=#{summary_text},summary_photo=#{summary_photo},summary_video=#{summary_video},"
//			+ "WHERE id=#{id}")
	void updateSummary(Lesson lesson);
	
	//为一堂课添加课后作业
	
	
	
}
