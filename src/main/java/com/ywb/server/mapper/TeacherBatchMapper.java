package com.ywb.server.mapper;

import java.util.List;

import com.ywb.server.beans.Teacher;

public interface TeacherBatchMapper {

	void insertBatch(List<Teacher> teachers);
}
