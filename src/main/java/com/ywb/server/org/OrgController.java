package com.ywb.server.org;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ywb.server.beans.Student;
import com.ywb.server.mapper.StudentMapper;

@RestController
public class OrgController {
//	@Autowired
//	private StudentMapper studentMapper;
//	
//	@RequestMapping("/studentlist")
//	public List<Student> getStudentList(@RequestParam(value="teacherID") String teacherID) {
////		List<Student> studentlist = new ArrayList<Student>();
////		Student s1 = new Student("wangwei1", 6, "male", "123@a.com");
////		studentlist.add(s1);
////		
////		Student s2 = new Student("wangwei2", 7, "female", "234@b.com");
////		studentlist.add(s2);
////		//studentMapper.insert(s2);
////		List<Student> listStudent = studentMapper.getStudentList();
////		return listStudent;
//// 		try int directly
//		return studentMapper.getStudentList(Integer.valueOf(teacherID));
//	}
//	
//	@RequestMapping("/studentlist1")
//	public Student getStudent(@RequestParam(value="id") int id) {
//		
//		return studentMapper.getStudent(id);
//		
//	}
	
}
