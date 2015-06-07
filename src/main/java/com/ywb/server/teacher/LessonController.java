package com.ywb.server.teacher;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ywb.server.beans.Lesson;
import com.ywb.server.beans.RestResult;
import com.ywb.server.beans.Student;
import com.ywb.server.beans.Teacher;
import com.ywb.server.mapper.LessonMapper;
import com.ywb.server.mapper.StudentMapper;
import com.ywb.server.mapper.TeacherBatchMapper;
import com.ywb.server.mapper.TeacherMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class LessonController {
	private static Logger logger = LoggerFactory.getLogger(LessonController.class);

	@Autowired
	private LessonMapper lessonMapper;

	/*
	 * 删除一堂课
	 */
	@RequestMapping(value="/lesson", method=RequestMethod.DELETE)
	public RestResult deleteLesson(@RequestParam(value="lessonID") int  lessonID){
		
		Lesson lesson = new Lesson();
		RestResult restResult = new RestResult();
		//补充try catch
		try{
			lessonMapper.deleteLesson(lessonID);
			logger.info("Successfully deleted lessonID: "+lessonID);
			restResult.setStatus(200);
			restResult.setMsg("ok");
		}catch (Exception ex){
			logger.error("error: {}", ex);
			restResult.setStatus(500);
			restResult.setMsg("Delete a lesson failed");
		}
		return restResult;
	}

	
	/*
	 * 查询某课程的课堂列表
	 */
	@RequestMapping(value="/lesson", method=RequestMethod.GET)
	public RestResult queryLessonList(@RequestParam(value="courseID") int  courseID,
			 						  @RequestParam("pageIndex") int pageIndex, 
			 						  @RequestParam("pageSize") int pageSize){
		logger.info("query lesson list of a course: {} ", courseID);
		
		RestResult restResult = new RestResult();
		try {
			List<Lesson> lessonList = lessonMapper.queryLessonList(courseID, pageIndex, pageSize);
			restResult.setStatus(200);
			restResult.setMsg("Success");
			int total = lessonMapper.getLessonCount(courseID);
			restResult.setTotal(total);
			restResult.setSize(lessonList.size());
			int totalPage=total/pageSize;
			if (total%pageSize != 0){
				totalPage += 1;
			}
			restResult.setTotalPage(totalPage);
			restResult.setContent(lessonList);
		} catch (Exception ex) {
			logger.error("DATABASE ERROR from querying lesson list");
			ex.printStackTrace();
			restResult.setStatus(500);
			restResult.setMsg("内部错误");
		}

		
		
		return restResult;
	}
	
	
	/*
	 * 为一堂课添加课堂精要
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/lessonSummary", method=RequestMethod.POST)
	public RestResult addLessonSummary(@RequestBody Map<String, Object> lessonSummaryRequest)
	{
		logger.info("add lessonSummary {}", lessonSummaryRequest);
		Integer courseID = (Integer) lessonSummaryRequest.get("courseID");
		String summaryText = (String) lessonSummaryRequest.get("summaryText");
		List<String> summaryPhoto = (List<String>) lessonSummaryRequest.get("summaryPhoto");
		String summaryVideo = (String) lessonSummaryRequest.get("summaryVideo");
		
		Lesson lesson = new Lesson();
		lesson.setCourse_id(courseID);
		lesson.setSummary_text(summaryText);
		lesson.setSummary_photo(summaryPhoto);
		lesson.setSummary_video(summaryVideo);
		
		RestResult restResult = new RestResult();
		
		try {
				lessonMapper.insertSummary(lesson);
				restResult.setStatus(200);
				restResult.setMsg("successfully add lesson summary info");
		} catch (Exception ex) {
			logger.error("DATABASE ERROR: {}", ex);
			ex.printStackTrace();
			restResult.setStatus(500);
			restResult.setMsg("内部错误");
		}
		return restResult;
	}


	/*
	 * update lesson summary
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/lessonSummary", method=RequestMethod.PUT)
	public RestResult updateLessonSummary(@RequestBody Map<String, Object> lessonSummary)
	{
		logger.info("add lessonSummary {}", lessonSummary);
		Integer courseID = (Integer) lessonSummary.get("courseID");
		String summaryText = (String) lessonSummary.get("summaryText");
		List<String> summaryPhoto = (List<String>) lessonSummary.get("summaryPhoto");
		String summaryVideo = (String) lessonSummary.get("summaryVideo");
		
		Lesson lesson = new Lesson();
		lesson.setCourse_id(courseID);
		lesson.setSummary_text(summaryText);
		lesson.setSummary_photo(summaryPhoto);
		lesson.setSummary_video(summaryVideo);
	
		RestResult restResult = new RestResult();
		
		try {
				lessonMapper.updateSummary(lesson);
				restResult.setStatus(200);
				restResult.setMsg("successfully update Lesson info");
		} catch (Exception ex) {
			logger.error("DATABASE ERROR: {}", ex);
			ex.printStackTrace();
			restResult.setStatus(500);
			restResult.setMsg("内部错误");
		}
		return restResult;
	}
	
	
	/*
	 * 为一堂课添加课后作业
	 */
	@RequestMapping(value="/homework", method=RequestMethod.POST)
	public RestResult addHomework(@RequestBody Map<String, Object> homeworkRequest)
	{
		logger.info("batch student course: {}", homeworkRequest);
		Integer courseID = (Integer) homeworkRequest.get("courseID");
		String homeworkText = (String) homeworkRequest.get("homeworkText");
		List<String> homeworkPhoto = (List<String>) homeworkRequest.get("homeworkPhoto");
		String homeworkVideo = (String) homeworkRequest.get("homeworkVideo");
		
		Lesson lesson = new Lesson();
		lesson.setCourse_id(courseID);
		lesson.setHomework_text(homeworkText);
		lesson.setHomework_photo(homeworkPhoto);
		lesson.setHomework_video(homeworkVideo);
		
		RestResult restresult = new RestResult();
		
		try {
				lessonMapper.insertSummary(lesson);
				restresult.setStatus(200);
				restresult.setMsg("successfully add homework info");
		} catch (Exception ex) {
			logger.error("DATABASE ERROR: {}", ex);
			ex.printStackTrace();
			restresult.setStatus(500);
			restresult.setMsg("内部错误");
		}
		return restresult;
	}	
}
