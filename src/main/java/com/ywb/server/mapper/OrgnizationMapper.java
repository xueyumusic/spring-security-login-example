
package com.ywb.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import com.ywb.server.beans.Orgnization;

public interface OrgnizationMapper {
	
	@Insert("INSERT INTO orgnization (orgid, headpath) VALUES (#{orgid}, #{headpath})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	void insert(Orgnization org);


	@Select("SELECT headpath FROM orgnization WHERE orgid = #{orgid}")
	List<String> queryByOrgid(@Param("orgid") int id);
}
