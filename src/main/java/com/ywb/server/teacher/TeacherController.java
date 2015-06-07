package com.ywb.server.teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ywb.server.beans.RestResult;
import com.ywb.server.beans.Student;
import com.ywb.server.beans.Teacher;
import com.ywb.server.mapper.StudentMapper;
import com.ywb.server.mapper.TeacherBatchMapper;
import com.ywb.server.mapper.TeacherMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class TeacherController {
	private static Logger logger = LoggerFactory.getLogger(TeacherController.class);

	@Autowired
	private TeacherMapper teacherMapper;
	@Autowired
	private TeacherBatchMapper teacherBatchMapper;
	
	
	@RequestMapping("/getTeacherInfo")
	public RestResult getTeacherInfo(@RequestParam(value="teacher_id") Integer teacherid, @RequestParam(value="page_index", required=false) Integer page_index, @RequestParam(value="page_size", required=false) Integer page_size)
	{
		logger.debug("process http request getTeacherInfo, teacher_id: {}, page_index: {}, page_size: {}", 
						teacherid, page_index, page_size);
		
		RestResult restresult = new RestResult();
		
		try {
			List<Teacher> teacher = teacherMapper.queryByTeacherId(teacherid);
			
			if (teacher.size() > 0) {
				restresult.setStatus(200);
				restresult.setMsg("ok");
				restresult.setTotal(1);
				restresult.setSize(1);
				restresult.setContent(teacher);
			} else {
				restresult.setStatus(300);
				restresult.setMsg("没有该老师的相关信息");
			}
			
		} catch (Exception ex) {
			logger.error("Database Exception");
			ex.printStackTrace();
			restresult.setStatus(500);
			restresult.setMsg("内部错误");
		}
		return restresult;
	}
	
	@RequestMapping(value="/addTeacherInfo", method=RequestMethod.POST)
	public RestResult addTeacherInfo(@RequestParam(value="name") String name,
									@RequestParam(value="age") Integer age,
									@RequestParam(value="gender", required=false) String gender,
									@RequestParam(value="contact") String contact)
	{
		logger.debug("process http request addTeacherInfo, name: {}, age: {}, gender: {}, contact: {}",
						name, age, gender, contact);
		List<Teacher> curteacher = teacherMapper.queryByTeacherNameAndContact(name, contact);
		RestResult restresult = new RestResult();
		
		try {
			if (curteacher.size() == 0) {
				Teacher teacher = new Teacher(name, age, gender, contact);
				teacherMapper.insert(teacher);
				restresult.setStatus(200);
				restresult.setMsg("successful add teacher info");
			} else {
				restresult.setStatus(300);
				restresult.setMsg("已经存在这个老师");
			}
		} catch (Exception ex) {
			logger.error("DATABASE ERROR");
			ex.printStackTrace();
			restresult.setStatus(500);
			restresult.setMsg("内部错误");
		}
		return restresult;
	}
	
	@RequestMapping(value="/deleteTeacherInfo", method=RequestMethod.DELETE)
	public RestResult deleteTeacherInfo(@RequestParam(value="teacher_id") Integer teacherid) {
		teacherMapper.delete(teacherid);
		
		RestResult restresult = new RestResult();
		restresult.setStatus(200);
		restresult.setMsg("successful delete teacher info");
		return restresult;
	}
	
	@RequestMapping(value="/testteacherbatch", method=RequestMethod.POST)
	@SuppressWarnings(value = { "unchecked" })
	public String testTeacherBatchInsert(@RequestBody Map teacher) {
		logger.info("accept teachers: {}", teacher.toString());
		String name = (String)teacher.get("name");
		Integer age = (Integer)teacher.get("age");
		List<Integer> ids = (List<Integer>)teacher.get("listtest");
		
		logger.info("##name: {}", name);
		logger.info("###age: {}", age);
		for (Integer id: ids) {
			logger.info("###get id: {}", id);
		}

		return "successful";
	}

}
