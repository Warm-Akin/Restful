package com.akin.restful.xuexin;

import com.akin.restful.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/xuexin/admin/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @GetMapping(value = "/findAll/{page}/{pageSize}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findAllForPageable(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        return ResponseUtil.success(teacherService.findAll(page, pageSize));
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity findAll() {
        return ResponseUtil.success(teacherService.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Teacher teacher) {
        teacherService.handleSave(teacher);
        return ResponseUtil.success(HttpStatus.OK);
    }

    @PostMapping(value = "/findByConditions")
    public ResponseEntity findStudentsByConditions(@RequestBody TeacherDto teacherDto) {
        return ResponseUtil.success(teacherService.findByConditions(teacherDto));
    }

    @PostMapping(value = "/upload")
    public ResponseEntity uploadStudentList(MultipartFile file) {
        teacherService.uploadTeacherList(file);
        return ResponseUtil.success(HttpStatus.OK);
    }



}
