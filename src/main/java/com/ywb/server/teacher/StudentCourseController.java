package com.ywb.server.teacher;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
import com.ywb.server.beans.Student;
import com.ywb.server.beans.StudentCourse;
import com.ywb.server.beans.TeacherCourse;
import com.ywb.server.mapper.StudentCourseMapper;
import com.ywb.server.mapper.TeacherCourseMapper;


@RestController
public class StudentCourseController {

	
	@Autowired
	private StudentCourseMapper studentCourseMapper;
	@Autowired
	private TeacherCourseMapper teacherCourseMapper;
	
	private static Logger logger = LoggerFactory.getLogger(StudentCourseController.class);

	//添加某学生到某课程中
	@RequestMapping(value="/studentCourse", method=RequestMethod.POST)
	public RestResult addStudenttoCourse( @RequestBody Map<String, Object> studentCourseRequest) {
		Integer studentID = (Integer) studentCourseRequest.get("studentID");
		Integer courseID = (Integer) studentCourseRequest.get("courseID");
		
		//检查老师是否有课程：courseID，后面补上这个逻辑
		
		//检查学生studentID，是否被分配了课程：courseID；未分配，插入记录；分配，就返回错误信息；
		StudentCourse studentCourse = new StudentCourse();
		studentCourse.setStudent_id(studentID);
		studentCourse.setCourse_id(courseID);
		List<StudentCourse> studentCourseList = studentCourseMapper.queryStudentCourse(studentCourse);
		
		RestResult restResult = new RestResult();
		if (studentCourseList.size() == 0) {
			studentCourseMapper.insert(studentCourse);
			restResult.setStatus(200);
			restResult.setMsg("ok");
		}else {
			restResult.setStatus(300); //内部错误状态码，查一下标准
			restResult.setMsg("该学生已被分配了这个课程");
		}
		return restResult;
	}
	
	//删除某课程下某个学生
//	@RequestMapping("/deleteCourseStudent")
	@RequestMapping(value="/studentCourse", method=RequestMethod.DELETE)
	public RestResult deleteCourseStudent( @RequestBody Map<String, Object> studentCourseRequest) {
		Integer studentID = (Integer) studentCourseRequest.get("studentID");
		Integer courseID = (Integer) studentCourseRequest.get("courseID");

		StudentCourse studentCourse = new StudentCourse();
		studentCourse.setCourse_id(courseID);
		studentCourse.setStudent_id(studentID);
		studentCourseMapper.deleteCourseStudent(studentCourse);
		RestResult restResult = new RestResult();
		restResult.setStatus(200);
		restResult.setMsg("ok");
		return restResult;	
	}
	
	
	
	/*
	 * add a student list to a course
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/studentListCourse", method=RequestMethod.POST)
	public RestResult batchStudentCourse(@RequestBody Map<String, Object> studentCourseRequest) {
		logger.info("adding student list to a course: {}", studentCourseRequest);
		Integer courseid = (Integer)studentCourseRequest.get("courseID");
		List<Integer> studentid = (List<Integer>)studentCourseRequest.get("studentID");
		
		try {
			List<Integer> existedStudentId = studentCourseMapper.queryStudentIdByCourseId(courseid);
			Set<Integer> existedStudentIdSet = new HashSet<Integer>(existedStudentId);
			
			Set<Integer> studentIdSet = new HashSet<Integer>(studentid);
			studentIdSet.removeAll(existedStudentIdSet);
			
			List<StudentCourse> studentCourseList = new ArrayList<StudentCourse>();
			Iterator<Integer> iter = studentIdSet.iterator();
			while (iter.hasNext()) {
				studentCourseList.add(new StudentCourse(iter.next(), courseid));
			}
			
			if (studentCourseList.size() > 0) {
				studentCourseMapper.insertBatch(studentCourseList);
			}
			
			RestResult restResult = new RestResult();
			if (studentCourseList.size() == studentid.size()) {
				restResult.setStatus(200);
				logger.info("insert all");
				restResult.setMsg("insert all");
			} else if (studentCourseList.size() > 0) {
				restResult.setStatus(200);
				logger.info("insert part");
				restResult.setMsg("some existed, insert part");
			} else if (studentCourseList.size() == 0) {
				restResult.setStatus(300);
				logger.info("all existed, not insert");
				restResult.setMsg("all existed");
			}
			return restResult;
		} catch (Exception ex) {
			logger.error("error: {}", ex);
			RestResult restResult = new RestResult();
			restResult.setStatus(400);
			restResult.setMsg("insert failed");
			return restResult;
		}
		
	}
	
	
	//获取某老师名下某个学生所报名的课程列表
//	@RequestMapping("/getStudentCourseList")
	@RequestMapping(value="/studentCourseList", method=RequestMethod.GET)
	public RestResult getStudentCourseList(@RequestParam(value="teacherID")int teacherID, 
										@RequestParam(value="studentID") int studentID) {
		
		List<Course> studentCourseList = studentCourseMapper.getStudentCourseList(teacherID, studentID); //获取学生基本信息

		RestResult restResult = new RestResult();
		if(studentCourseList!=null){
			restResult.setStatus(200);
			restResult.setMsg("ok");
			restResult.setTotal(studentCourseList.size());
			restResult.setSize(studentCourseList.size());
			restResult.setContent(studentCourseList);
		}else{
			restResult.setStatus(300);
			restResult.setMsg("该学生未报名任何课程");
		}
		
		return restResult;
	}

	
	//获取参加某课程的学生列表
//	@RequestMapping("/getCourseStudentList")
	@RequestMapping(value="/courseStudentList", method=RequestMethod.GET)
	public RestResult getCourseStudentList(@RequestParam(value="courseID") int courseID) {

		RestResult restResult = new RestResult();
		List<Student> studentList = studentCourseMapper.getCourseStudentList(courseID);
		restResult.setStatus(200);
		restResult.setMsg("ok");
		restResult.setTotal(studentList.size());
		restResult.setSize(studentList.size());
		restResult.setContent(studentList);

		return restResult;	
	}

	
	//查询某学生是否选了某门课程
//	@RequestMapping("/queryStudentCourse")
	@RequestMapping(value="/studentCourse", method=RequestMethod.GET)
	public RestResult queryStudentCourse(@RequestParam(value="studentID") int  studentID, 
			 							 @RequestParam(value="courseID") int  courseID){
		
		StudentCourse studentCourse = new StudentCourse();
		studentCourse.setStudent_id(studentID);
		studentCourse.setCourse_id(courseID);
		List<StudentCourse> studentCourseList = studentCourseMapper.queryStudentCourse(studentCourse);
		
		RestResult restResult = new RestResult();
		
		if (studentCourseList.size() == 0) {
			restResult.setStatus(300); //内部错误状态码，查一下标准
			restResult.setMsg("该学生没有选这个课程");
		}else {
			restResult.setStatus(200);
			restResult.setMsg("ok");
			List<StudentCourse> content = new ArrayList<StudentCourse>();
			content.add(studentCourse);
			restResult.setContent(content);
		}
		return restResult;
	}

	
 
}
