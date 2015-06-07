package com.ywb.server.teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jboss.netty.handler.codec.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ywb.server.beans.CourseCircle;
import com.ywb.server.beans.CourseCircleComment;
import com.ywb.server.beans.Favorite;
import com.ywb.server.beans.RestResult;
import com.ywb.server.beans.Teacher;
import com.ywb.server.mapper.CourseCircleMapper;
import com.ywb.server.mapper.TeacherMapper;

@RestController
public class CourseCircleController {
	private static Logger logger = LoggerFactory.getLogger(CourseCircleController.class);
	
	@Autowired
	private CourseCircleMapper courseCircleMapper;
	
	@Autowired
	private TeacherMapper teacherMapper;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/courseCircle/{course_id}", method=RequestMethod.POST)
	RestResult addCourseCircle(@PathVariable(value="course_id") int course_id, @RequestBody Map<String, Object> objmap) {
		logger.info("insert course circle: {}", objmap );
		Integer publisher_id = (Integer)objmap.get("publisher_id");
		String content = (String)objmap.get("content");
		List<String> photo_list = (List<String>)objmap.get("photo_list");
		
		CourseCircle coursecircle = new CourseCircle();
		coursecircle.setPublisher_id(publisher_id);
		coursecircle.setPhoto_list(photo_list);
		coursecircle.setContent(content);
		coursecircle.setCourse_id(course_id);
		
		RestResult restResult = new RestResult();
		try {
			courseCircleMapper.insertCourseCircle(coursecircle);
			restResult.setStatus(200);
			restResult.setMsg("successfully insert course circle");
		} catch (Exception ex) {
			logger.error("DATABASE ERROR: {}", ex);
			ex.printStackTrace();
			restResult.setStatus(500);
			restResult.setMsg("内部错误");
		}
		return restResult;
		
	}
	
	@RequestMapping(value="/favorite", method=RequestMethod.POST)
	RestResult addFavorite(@RequestBody Favorite favorite) {
		RestResult restResult = new RestResult();
		try {
			courseCircleMapper.insertFavorite(favorite);
			restResult.setStatus(200);
			restResult.setMsg("add favorite successfully");
		} catch (Exception ex) {
			logger.error("DATABASE ERROR: {}", ex);
			ex.printStackTrace();
			restResult.setStatus(500);
			restResult.setMsg("内部错误");
		}
		return restResult;
	}
	
	@RequestMapping(value="/favorite", method=RequestMethod.DELETE)
	RestResult removeFavorite(@RequestBody Favorite favorite) {
		RestResult restResult = new RestResult();
		try {
			courseCircleMapper.insertFavorite(favorite);
			restResult.setStatus(200);
			restResult.setMsg("add favorite successfully");
		} catch (Exception ex) {
			logger.error("DATABASE ERROR: {}", ex);
			ex.printStackTrace();
			restResult.setStatus(500);
			restResult.setMsg("内部错误");
		}
		return restResult;
	}
	
	@RequestMapping(value="/courseCircleComment", method=RequestMethod.POST)
	RestResult addComment(@RequestBody CourseCircleComment comment) {
		RestResult restResult = new RestResult();
		try {
			courseCircleMapper.insertComment(comment);
			restResult.setStatus(200);
			restResult.setMsg("add comment successfully");
		} catch (Exception ex) {
			logger.error("DATABASE ERROR: {}", ex);
			ex.printStackTrace();
			restResult.setStatus(500);
			restResult.setMsg("内部错误");
		}
		return restResult;
	}
	

	@RequestMapping(value="/courseCircleComment", method=RequestMethod.DELETE)
	RestResult removeComment(@RequestParam("comment_id") Integer comment_id) {
		RestResult restResult = new RestResult();
		try {
			courseCircleMapper.removeComment(comment_id);
			restResult.setStatus(200);
			restResult.setMsg("add favorite successfully");
		} catch (Exception ex) {
			logger.error("DATABASE ERROR: {}", ex);
			ex.printStackTrace();
			restResult.setStatus(500);
			restResult.setMsg("内部错误");
		}
		return restResult;
	}
	
	@RequestMapping(value="/courseCircleList") ///http://127.0.0.1:8080/courseCircleList?course_id=1
	RestResult queryCourseCircleList(@RequestParam Integer course_id) {
		RestResult restResult = new RestResult();
		List<CourseCircle> coursecircles = courseCircleMapper.queryByCourseId(course_id);
		List<CourseCircleEntry> entries = new ArrayList<CourseCircleEntry>();
		try {
			for (CourseCircle item: coursecircles) {
				CourseCircleEntry entry = new CourseCircleEntry();
				entry.setContent(item.getContent());
				entry.setPhoto_list(item.getPhoto_list());
				//Teacher teacher = teacherMapper.queryByTeacherId(item.getPublisher_id()).get(0);
				List<Teacher> teachers = teacherMapper.queryByTeacherId(item.getPublisher_id());
				if (teachers.size() > 0) {
					entry.setPublish_teacher(teachers.get(0));
				}
				List<CourseCircleComment> comments = courseCircleMapper.queryCommentByReplyId(item.getId(), "topic");
				entry.setCourse_circle_comment(comments);
				List<Integer> fav_subject_ids = courseCircleMapper.queryFavorite(item.getId());
				entry.setVote_teachers(fav_subject_ids);
				entries.add(entry);
				
			}
			restResult.setStatus(200);
			restResult.setMsg("course circle list");
			restResult.setContent(entries);
		} catch (Exception ex) {
			logger.error("DATABASE ERROR: {}", ex);
			restResult.setStatus(500);
			restResult.setMsg("内部错误");
		}
		return restResult;
		
	}
	
	private class CourseCircleEntry {
		private Teacher publish_teacher;
		private String content;
		private List<String> photo_list;
		private List<CourseCircleComment> course_circle_comment;
		private List<Integer> vote_teachers;
		public Teacher getPublish_teacher() {
			return publish_teacher;
		}
		public void setPublish_teacher(Teacher publish_teacher) {
			this.publish_teacher = publish_teacher;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public List<String> getPhoto_list() {
			return photo_list;
		}
		public void setPhoto_list(List<String> photo_list) {
			this.photo_list = photo_list;
		}
		public List<CourseCircleComment> getCourse_circle_comment() {
			return course_circle_comment;
		}
		public void setCourse_circle_comment(List<CourseCircleComment> course_circle_comment) {
			this.course_circle_comment = course_circle_comment;
		}
		public List<Integer> getVote_teachers() {
			return vote_teachers;
		}
		public void setVote_teachers(List<Integer> vote_teachers) {
			this.vote_teachers = vote_teachers;
		}
	}
}
