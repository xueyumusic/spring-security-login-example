package com.ywb.server.teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ywb.server.beans.CheckIn;
import com.ywb.server.beans.RestResult;
import com.ywb.server.mapper.CheckInMapper;

@RestController
public class CheckInController {
	private static Logger logger = LoggerFactory.getLogger(CheckInController.class);
	
	@Autowired
	private CheckInMapper checkInMapper;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/checkin", method=RequestMethod.POST)
	void addCheckIn(@RequestBody Map<String, Object> obj) {
		Integer course_id = (Integer)obj.get("course_id");
		List<Integer> student_ids = (List<Integer>)obj.get("student_id");
		List<CheckIn> checkins = new ArrayList<CheckIn>();
		for (Integer sid: student_ids) {
			checkins.add(new CheckIn(course_id, sid));
		}
		RestResult restResult = new RestResult();
		try {
			checkInMapper.insertBatch(checkins);
			restResult.setStatus(200);
			restResult.setMsg("successfully add check in");
		} catch (Exception ex) {
			logger.error("DATABASE ERROR: {}", ex);
			restResult.setStatus(500);
			restResult.setMsg("内部错误");
		}
	}
	
	@RequestMapping("/checkin")
	RestResult getCheckIn(@RequestParam(value="course_id") Integer course_id,
			 		@RequestParam(value="pageIndex") int pageIndex,
			 		@RequestParam(value="pageSize") int pageSize) {
		RestResult restResult = new RestResult();
		try {
			List<CheckIn> checkins = checkInMapper.queryByCourseId(course_id, pageIndex, pageSize);
			restResult.setStatus(200);
			restResult.setMsg("get checkin");
			restResult.setSize(checkins.size());
			restResult.setContent(checkins);
		} catch (Exception ex) {
			logger.error("DATABASE ERROR: {}", ex);
			restResult.setStatus(500);
			restResult.setMsg("内部错误");
		}
		return restResult;
		
	}
	
	@RequestMapping(value="/checkin", method=RequestMethod.DELETE)
	RestResult deleteCheckIn(@RequestParam(value="course_id") Integer course_id) {
		RestResult restResult = new RestResult();
		try {
			checkInMapper.deleteCheckIn(course_id);
			restResult.setStatus(200);
			restResult.setMsg("successfully delete checkin");
		} catch (Exception ex) {
			logger.error("DATABASE ERROR: {}", ex);
			restResult.setStatus(500);
			restResult.setMsg("内部错误");
		}
		return restResult;
	}
}
