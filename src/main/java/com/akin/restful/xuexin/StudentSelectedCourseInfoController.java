package com.akin.restful.xuexin;

import com.akin.restful.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/xuexin/admin/studentselectedcourse")
public class StudentSelectedCourseInfoController {

    @Autowired
    StudentSelectedCourseInfoService selectedCourseInfoService;

    @GetMapping(value = "/findAll/{page}/{pageSize}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findAllForPageable(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        return ResponseUtil.success(selectedCourseInfoService.findAll(page, pageSize));
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity findAll() {
        return ResponseUtil.success(selectedCourseInfoService.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody StudentSelectedCourseInfo selectedCourseInfo) {
        selectedCourseInfoService.handleSave(selectedCourseInfo);
        return ResponseUtil.success(HttpStatus.OK);
    }

    @PostMapping(value = "/findByConditions")
    public ResponseEntity findStudentsByConditions(@RequestBody StudentSelectedCourseDto selectedCourseDto) {
        return ResponseUtil.success(selectedCourseInfoService.findByConditions(selectedCourseDto));
    }

    @PostMapping(value = "/upload")
    public ResponseEntity uploadStudentList(MultipartFile file) {
        selectedCourseInfoService.uploadSelectedCourseList(file);
        return ResponseUtil.success(HttpStatus.OK);
    }
}
