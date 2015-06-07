package com.ywb.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ywb.server.beans.CheckIn;

public interface CheckInMapper {

	void insertBatch(List<CheckIn> checkins);
	
	@Select("SELECT * FROM checkin WHERE id=#{course_id} LIMIT #{pageSize} OFFSET #{pageIndex}*#{pageSize}")
	List<CheckIn> queryByCourseId(@Param("course_id") Integer course_id,
									@Param("pageIndex") int pageIndex, 
									@Param("pageSize") int pageSize);
	
	@Delete("DELETE FROM checkin where course_id = #{course_id}")
	void deleteCheckIn(@Param("course_id") Integer course_id);
}
