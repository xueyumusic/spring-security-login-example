package com.ywb.server.teacher;

import java.sql.Date;
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

import com.ywb.server.beans.Course;
import com.ywb.server.beans.RestResult;
import com.ywb.server.mapper.CourseMapper;


@RestController
	
	
public class CourseController {

	private static Logger logger = LoggerFactory.getLogger(CourseController.class);
	
	@Autowired
	private CourseMapper courseMapper;
	
	@RequestMapping("/queryCourseIDbyCourseName")
	public Integer queryCourseIDbyCourseName(@RequestParam(value="courseName") String courseName,
									 @RequestParam(value="teacherID") int teacherID){
		Integer id = courseMapper.queryCourseIDbyCourseName(courseName,teacherID);
		return id;
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/course", method=RequestMethod.POST)
	public RestResult addCourse(@RequestBody Map<String, Object> courseRequest)
	{
		logger.info("add course {}", courseRequest);
		
	    String course_name = (String) courseRequest.get("courseName");
	    Integer principal_id = (Integer) courseRequest.get("principalID");
	    List<Integer> teacher_id = (List<Integer>) courseRequest.get("principalID");
	    Double price = (Double) courseRequest.get("price");
	    Date   begin_time = (Date) courseRequest.get("beginTime");
	    Date    end_time = (Date) courseRequest.get("endTime");
	    String head_photo = (String) courseRequest.get("headPhoto");
	    Integer class_hours = (Integer) courseRequest.get("classHours");
	    String class_description = (String) courseRequest.get("classDescription");
	    List<String> photos = (List<String>) courseRequest.get("photos");		
		
		Course course = new Course();
		course.setCourse_name(course_name);
		course.setPrincipal_id(principal_id);
		course.setTeacher_id(teacher_id);
		course.setPrice(price);
		course.setBegin_time(begin_time);
		course.setEnd_time(end_time);
		course.setHead_photo(head_photo);
		course.setClass_hours(class_hours);
		course.setClass_description(class_description);
		course.setPhotos(photos);
		
		RestResult restResult = new RestResult();
		
		try {
				courseMapper.insert(course);
				restResult.setStatus(200);
				restResult.setMsg("successfully add course");
		} catch (Exception ex) {
			logger.error("DATABASE ERROR: {}", ex);
			ex.printStackTrace();
			restResult.setStatus(500);
			restResult.setMsg("内部错误");
		}
		return restResult;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/course", method=RequestMethod.PUT)
	public RestResult updateCourse(@RequestBody Map<String, Object> courseRequest)
	{
		logger.info("update course {}", courseRequest);
		
	    String course_name = (String) courseRequest.get("courseName");//如果为空需要try catch吧？？？
	    Integer principal_id = (Integer) courseRequest.get("principalID");
	    List<Integer> teacher_id = (List<Integer>) courseRequest.get("principalID");
	    Double price = (Double) courseRequest.get("price");
	    Date   begin_time = (Date) courseRequest.get("beginTime");
	    Date    end_time = (Date) courseRequest.get("endTime");
	    String head_photo = (String) courseRequest.get("headPhoto");
	    Integer class_hours = (Integer) courseRequest.get("classHours");
	    String class_description = (String) courseRequest.get("classDescription");
	    List<String> photos = (List<String>) courseRequest.get("photos");		
		
		Course course = new Course();
		course.setCourse_name(course_name);
		course.setPrincipal_id(principal_id);
		course.setTeacher_id(teacher_id);
		course.setPrice(price);
		course.setBegin_time(begin_time);
		course.setEnd_time(end_time);
		course.setHead_photo(head_photo);
		course.setClass_hours(class_hours);
		course.setClass_description(class_description);
		course.setPhotos(photos);
		
		RestResult restResult = new RestResult();
		
		try {
				courseMapper.update(course);
				restResult.setStatus(200);
				restResult.setMsg("successfully update course");
		} catch (Exception ex) {
			logger.error("DATABASE ERROR: {}", ex);
			ex.printStackTrace();
			restResult.setStatus(500);
			restResult.setMsg("内部错误");
		}
		return restResult;
	}

	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/course", method=RequestMethod.DELETE)
	public RestResult deleteCourse(@RequestParam int courseID)
	{
		logger.info("delete course {}", courseID);
		RestResult restResult = new RestResult();
		try {
				courseMapper.delete(courseID);
				restResult.setStatus(200);
				restResult.setMsg("successfully delete courseID: "+courseID);
		} catch (Exception ex) {
			logger.error("DATABASE ERROR: {}", ex);
			ex.printStackTrace();
			restResult.setStatus(500);
			restResult.setMsg("内部错误");
		}
		return restResult;
	}
	
}
