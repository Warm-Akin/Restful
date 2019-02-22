package com.akin.restful.xuexin;

import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends IBaseRespository<Teacher,String>{
     Teacher findByTeacherNo(String teacherNo);
}
