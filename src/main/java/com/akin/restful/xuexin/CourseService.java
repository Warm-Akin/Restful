package com.akin.restful.xuexin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public PageResultVO<Course> findAll(Integer page, Integer pageSize) {
        // page's value start at '0'
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, getSort());
        Page<Course> coursePage = (Page<Course>) courseRepository.findAll(new Specification<Course>() {

            @Override
            public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                Path active = root.get("active");
                Predicate p = criteriaBuilder.equal(active, Constant.ACTIVE);
                predicateList.add(p);

                Predicate[] predicates = new Predicate[predicateList.size()];
                predicateList.toArray(predicates);
                criteriaQuery.where(predicates);
                return criteriaBuilder.and(predicates);
            }
        }, pageRequest);
        PageResultVO<Course> pageResultVO = new PageResultVO<>(coursePage.getContent(), coursePage.getTotalElements());
        return pageResultVO;
    }

    @Transactional
    public void handleSave(Course course) {
        if (null != course) {
            if (StringUtils.isEmpty(course.getCourseId())) {
                // add
                String courseCode = course.getCourseCode();
                Course isCourseExist = !StringUtils.isEmpty(courseCode) ? courseRepository.findByCourseCode(courseCode) : null;
                if (isCourseExist == null) {
                    course.setCreateTime(new Date());
                    course.setActive(Constant.ACTIVE);
                } else {
                    throw new CustomException(ResultEnum.CourseCodeDuplicatedException.getMessage(), ResultEnum.CourseCodeDuplicatedException.getCode());
                }

            } else {
                // update
                Course currentCourse = courseRepository.findById(course.getCourseId()).orElse(null);
                // set value for not allow modify Columns
                course.setCourseCode(currentCourse.getCourseCode());
            }
            courseRepository.save(course);
        }

    }

    private Sort getSort() {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC, "courseCode"));
        orders.add(new Sort.Order(Sort.Direction.ASC, "courseName"));
        orders.add(new Sort.Order(Sort.Direction.ASC, "academicYear"));
        return new Sort(orders);
    }

    public PageResultVO<Course> findByConditions(CourseDto courseDto) {
        Pageable pageable = PageRequest.of(courseDto.getCurrentPage() - 1, courseDto.getPageSize(), getSort());
        Page<Course> teacherPage = courseRepository.findAll(new Specification<Course>() {

            @Override
            public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (!StringUtils.isEmpty(courseDto.getCourseCode())) {
                    Path courseCode = root.get("courseCode");
                    Predicate p = criteriaBuilder.like(criteriaBuilder.upper(courseCode), "%" + courseDto.getCourseCode().trim().toUpperCase() + "%");
                    predicateList.add(p);
                }
                if (!StringUtils.isEmpty(courseDto.getCourseName())) {
                    Path courseName = root.get("courseName");
                    Predicate p = criteriaBuilder.like(criteriaBuilder.upper(courseName), "%" + courseDto.getCourseName().trim().toUpperCase() + "%");
                    predicateList.add(p);
                }
                if (!StringUtils.isEmpty(courseDto.getTeacherNo())) {
                    Path teacherNo = root.get("teacherNo");
                    Predicate p = criteriaBuilder.like(criteriaBuilder.upper(teacherNo), "%" + courseDto.getTeacherNo().toUpperCase() + "%");
                    predicateList.add(p);
                }
                // todo upper
                if (!StringUtils.isEmpty(courseDto.getTeacherName())) {
                    Path teacherName = root.get("teacherName");
                    Predicate p = criteriaBuilder.like(criteriaBuilder.upper(teacherName), "%" + courseDto.getTeacherName().toUpperCase() + "%");
                    predicateList.add(p);
                }
                if (!StringUtils.isEmpty(courseDto.getAcademicYear())) {
                    Path academicYear = root.get("academicYear");
                    Predicate p = criteriaBuilder.equal(academicYear, courseDto.getAcademicYear());
                    predicateList.add(p);
                }
                if (!StringUtils.isEmpty(courseDto.getTerm())) {
                    Path term = root.get("term");
                    Predicate p = criteriaBuilder.equal(term, courseDto.getTerm());
                    predicateList.add(p);
                }
                if (!StringUtils.isEmpty(courseDto.getCourseType())) {
                    Path courseType = root.get("courseType");
                    Predicate p = criteriaBuilder.equal(courseType, courseDto.getCourseType().trim());
                    predicateList.add(p);
                }
                if (!StringUtils.isEmpty(courseDto.getCredit())) {
                    Path credit = root.get("credit");
                    Predicate p = criteriaBuilder.equal(credit, courseDto.getCredit());
                    predicateList.add(p);
                }
                // active = 1
                Path active = root.get("active");
                Predicate p = criteriaBuilder.equal(active, Constant.ACTIVE);
                predicateList.add(p);

                Predicate[] predicates = new Predicate[predicateList.size()];
                predicateList.toArray(predicates);
                criteriaQuery.where(predicates);
                return criteriaBuilder.and(predicates);
            }
        }, pageable);

        PageResultVO<Course> pageResultVO = new PageResultVO<>(teacherPage.getContent(), teacherPage.getTotalElements());
        return pageResultVO;
    }


    public void uploadCourseInfoList(MultipartFile file) {
//        if (null != file) {
//            Workbook wb = ExcelUtil.getWorkbookFromFile(file);
//            if (wb != null) {
//                Sheet sheet = wb.getSheetAt(0);
//                Iterator<Row> rowIterator=sheet.rowIterator();
//                // Ignore the title row
//                Row row = rowIterator.next();
//                List<Teacher> teacherList=new ArrayList<>();
//                int rowIndex=1;
//                while (rowIterator.hasNext()) {
//                    row = rowIterator.next();
//                    rowIndex += 1;
//                    // teacher's necessary information can't be null
//                    if (ExcelUtil.isFull(row, Constant.INDEX_TEACHER_NO, Constant.INDEX_TEACHER_NAME)) {
//                        Teacher teacher = new Teacher();
//                        teacher.setTeacherNo(ExcelUtil.getStringCellValue(row.getCell(Constant.INDEX_TEACHER_NO)));
//                        teacher.setTeacherName(ExcelUtil.getStringCellValue(row.getCell(Constant.INDEX_TEACHER_NAME)));
//                        String sex = ExcelUtil.getStringCellValue(row.getCell(Constant.INDEX_TEACHER_SEX));
//                        if (!StringUtils.isEmpty(sex) && "男".equals(sex)) {
//                            sex = "0";
//                        } else if (!StringUtils.isEmpty(sex) && "女".equals(sex)) {
//                            sex = "1";
//                        } else {
//                            sex = "";
//                        }
//                        teacher.setSex(sex);
//                        Date birthday = !"".equals(ExcelUtil.getStringCellValue(row.getCell(Constant.INDEX_TEACHER_BIRTHDAY))) ? DateUtil.formatDate(ExcelUtil.getStringCellValue(row.getCell(Constant.INDEX_TEACHER_BIRTHDAY)).replaceAll("-", "")) : null;
//                        teacher.setBirthday(birthday);
//                        // todo check orgName
//                        teacher.setOrgName(ExcelUtil.getStringCellValue(row.getCell(Constant.INDEX_TEACHER_ORGNAME)));
//                        teacher.setTelNo(ExcelUtil.getStringCellValue(row.getCell(Constant.INDEX_TEACHER_TELNO)));
//                        teacher.setEmail(ExcelUtil.getStringCellValue(row.getCell(Constant.INDEX_TEACHER_EAMIL)));
//                        teacher.setAddress(ExcelUtil.getStringCellValue(row.getCell(Constant.INDEX_TEACHER_ADDRESS)));
//                        teacher.setCategory(ExcelUtil.getStringCellValue(row.getCell(Constant.INDEX_TEACHER_CATEGORY)));
//                        teacher.setEducation(ExcelUtil.getStringCellValue(row.getCell(Constant.INDEX_TEACHER_EDUCATION)));
//                        teacher.setDegree(ExcelUtil.getStringCellValue(row.getCell(Constant.INDEX_TEACHER_DEGREE)));
//                        teacher.setDuty(ExcelUtil.getStringCellValue(row.getCell(Constant.INDEX_TEACHER_DUTY)));
//                        teacher.setAcademicTitle(ExcelUtil.getStringCellValue(row.getCell(Constant.INDEX_TEACHER_ACADEMICTITLE)));
//                        teacher.setInvigilatorFlag(ExcelUtil.getStringCellValue(row.getCell(Constant.INDEX_TEACHER_INVIGILATORFLAG)));
//                        teacher.setResearchDirection(ExcelUtil.getStringCellValue(row.getCell(Constant.INDEX_TEACHER_RESEARCHDIRECTION)));
//                        teacher.setIntroduce(ExcelUtil.getStringCellValue(row.getCell(Constant.INDEX_TEACHER_INTRODUCE)));
//                        teacher.setMajor(ExcelUtil.getStringCellValue(row.getCell(Constant.INDEX_TEACHER_MAJOR)));
//                        teacher.setGraduateSchool(ExcelUtil.getStringCellValue(row.getCell(Constant.INDEX_TEACHER_GRADUATE_SCHOOL)));
//                        teacher.setQualificationFlag(ExcelUtil.getStringCellValue(row.getCell(Constant.INDEX_TEACHER_QUALIFICATIONFLAG)));
//                        teacher.setJobStatus(ExcelUtil.getStringCellValue(row.getCell(Constant.INDEX_TEACHER_JOBSTATUS)));
//                        teacher.setIsLab(ExcelUtil.getStringCellValue(row.getCell(Constant.INDEX_TEACHER_ISLAB)));
//                        teacher.setIsOutHire(ExcelUtil.getStringCellValue(row.getCell(Constant.INDEX_TEACHER_ISOUTHIRE)));
//                        teacher.setPoliticalStatus(ExcelUtil.getStringCellValue(row.getCell(Constant.INDEX_TEACHER_POLITICALSTATUS)));
//                        teacher.setNation(ExcelUtil.getStringCellValue(row.getCell(Constant.INDEX_TEACHER_NATION)));
//                        teacherList.add(teacher);
//                    } else
//                        throw new CustomException(String.format(ResultEnum.TeacherUploadIncomplete.getMessage(), String.valueOf(rowIndex)), ResultEnum.TeacherUploadIncomplete.getCode());
//                }
//                // do save in jdbcTemplate -> 去重
//                saveTeacherListForUpload(teacherList);
//            } else
//                throw new CustomException(ResultEnum.FileIsNullException.getMessage(), ResultEnum.FileIsNullException.getCode());
//        }
    }
}
