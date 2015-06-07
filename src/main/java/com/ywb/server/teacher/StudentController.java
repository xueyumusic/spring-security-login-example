package com.ywb.server.teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ywb.server.beans.RestResult;
import com.ywb.server.beans.Student;
import com.ywb.server.mapper.StudentMapper;

@RestController
public class StudentController {
	
	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private CourseController courseController;
	
	@Autowired
	private StudentCourseController studentCourseController;
	
	//获取某老师的学生列表
	@RequestMapping(value="/studentList", method=RequestMethod.GET)
	public  RestResult getStudentList(@RequestParam(value="teacherID") int teacherID,
									  @RequestParam(value="pageIndex") int pageIndex,
									  @RequestParam(value="pageSize") int pageSize) {

		int total = studentMapper.getStudentListCount(teacherID);
		List<Student> studentList = studentMapper.getStudentList(teacherID,pageIndex,pageSize);
		int size = studentList.size();//当前页的记录条数
		int totalPage=total/pageSize;
		if (total%pageSize != 0){
			totalPage += 1;
		}
		RestResult restresult = new RestResult();
		restresult.setStatus(200);
		restresult.setMsg("ok");
		restresult.setTotal(total);
		restresult.setSize(size);
		restresult.setTotalPage(totalPage);
		restresult.setContent(studentList);
		
		return restresult;
	}
	
	
	//某老师获取某个学生的基本信息
	@RequestMapping(value="/studentInfo", method=RequestMethod.GET)
	public RestResult getStudentInfo(@RequestParam(value="studentID") int studentID) {
			
		Student studentInfo = studentMapper.getStudentInfo(studentID); //获取学生基本信息
		RestResult restResult = new RestResult();
		restResult.setStatus(200);
		restResult.setMsg("ok");
		restResult.setTotal(1);
		restResult.setSize(1);
		List<Student> list = new ArrayList<Student>();
		list.add(studentInfo);
		restResult.setContent(list);
		return restResult;
	}
	
	//更新某学生的基本信息
	@RequestMapping(value="/studentInfo", method=RequestMethod.POST)
	public RestResult updateStudentInfo(@RequestBody Map<String, Object> studentInfo) {
		Integer studentID = (Integer) studentInfo.get("studentID");
		String name = (String) studentInfo.get("name");
		Integer age = (Integer) studentInfo.get("age");  
		String gender = (String) studentInfo.get("gender"); 
		String contact = (String) studentInfo.get("contact"); 
		String secondContact = (String) studentInfo.get("secondContact");
		RestResult restResult = new RestResult();
		studentMapper.updateStudentInfo(studentID,name,age,gender,contact,secondContact);
		restResult.setStatus(200);
		restResult.setMsg("ok");
		
		return restResult;
	}
		
	//删除某学生
	@RequestMapping(value="/studentInfo", method=RequestMethod.DELETE)
	public RestResult deleteStudentInfo(@RequestParam(value="studentID") int studentID) {
		//检查老师是否有权限修改学生信息
		RestResult restResult = new RestResult();
		studentMapper.deleteStudentInfo(studentID);
		restResult.setStatus(200);
		restResult.setMsg("ok");
		return restResult;
	}
		
	//某个老师（教育机构）添加学生
	@RequestMapping(value="/studentInfo", method=RequestMethod.PUT)
	public RestResult addFreshStudent(@RequestBody Map<String, Object> studentInfo) {
		
		Integer teacherID = (Integer) studentInfo.get("teacherID");  
		String name = (String) studentInfo.get("name");
		Integer age = (Integer) studentInfo.get("age");  
		String gender = (String) studentInfo.get("gender"); 
		String contact = (String) studentInfo.get("contact"); 
		String secondContact = (String) studentInfo.get("secondContact");
		
		
		//检查学生是否在学生表（student）中已经存在了（看第一电话号码是否已存在），如果不存在，先在学生表中插入学生记录；
		Integer studentID = null;
		studentID = studentMapper.queryIDbyTeacherIDNameandContact(teacherID, name,contact);
		RestResult restResult = new RestResult();
		
		if (studentID == null) {//学生表中没有找到该学生,在学生表中插入学生记录
			Student student = new Student();
			student.setTeacher_id(teacherID);
			student.setName(name);
			student.setAge(age);
			student.setGender(gender);
			student.setContact(contact);
			student.setSecondContact(secondContact);
			studentMapper.insert(student);
			restResult.setStatus(200);
			restResult.setMsg("ok");
		} else {
			restResult.setStatus(300);//状态码的事尽快确定一下标准
			restResult.setMsg("学生信息已经存在了");
		}
		return restResult;
	}
	
}
