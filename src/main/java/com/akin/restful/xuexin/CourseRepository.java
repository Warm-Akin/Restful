package com.akin.restful.xuexin;

import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends IBaseRespository<Course, String>{
    Course findByCourseCode(String courseCode);
}
