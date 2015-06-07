package com.ywb.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ywb.server.beans.CourseCircle;
import com.ywb.server.beans.CourseCircleComment;
import com.ywb.server.beans.Favorite;

public interface CourseCircleMapper {

	void insertCourseCircle(CourseCircle courseCircle);
	
	@Insert("INSERT INTO favorite (subject_id, course_circle_id, created_at) VALUES (#{subject_id}, #{course_circle_id}, now())")
	void insertFavorite(Favorite favorite);
	
	@Delete("DELETE FROM favorite where subject_id = #{subject_id} and course_circle_id = #{course_circle_id}")
	void removeFavorite(Favorite favorite);
	
	@Insert("INSERT INTO course_circle_comment (subject_id, reply_id, reply_type, content, created_at) VALUES (#{subject_id}, #{reply_id}, #{reply_type}, #{content}, now())")
	void insertComment(CourseCircleComment comment);
	
	@Delete("DELETE FROM course_circle_comment where id = #{comment_id}")
	void removeComment(@Param("comment_id") Integer comment_id);
	
	@Select("SELECT * FROM course_circle_comment WHERE reply_id = #{reply_id} and reply_type = #{reply_type}")
	List<CourseCircleComment> queryCommentByReplyId(@Param("reply_id") Integer reply_id, @Param("reply_type") String reply_type);
	
	@Select("SELECT * FROM course_circle WHERE course_id = #{course_id}")
	List<CourseCircle> queryByCourseId(@Param("course_id") Integer course_id);
	
	@Select("SELECT subject_id FROM favorite WHERE course_circle_id = #{course_circle_id}")
	List<Integer> queryFavorite(@Param("course_circle_id") Integer course_circle_id);
}
