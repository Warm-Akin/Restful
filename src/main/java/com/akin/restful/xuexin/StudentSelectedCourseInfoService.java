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

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentSelectedCourseInfoService {

    @Autowired
    StudentSelectedCourseInfoRepository selectedCourseInfoRepository;

    public PageResultVO<StudentSelectedCourseInfo> findAll(Integer page, Integer pageSize) {
        // page's value start at '0'
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, getSort());
        Page<StudentSelectedCourseInfo> resultPage = (Page<StudentSelectedCourseInfo>) selectedCourseInfoRepository.findAll(new Specification<StudentSelectedCourseInfo>() {

            @Override
            public Predicate toPredicate(Root<StudentSelectedCourseInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
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
        PageResultVO<StudentSelectedCourseInfo> pageResultVO = new PageResultVO<>(resultPage.getContent(), resultPage.getTotalElements());
        return pageResultVO;
    }

    public List<StudentSelectedCourseInfo> findAll() {
        return selectedCourseInfoRepository.findAll();
    }

    private Sort getSort() {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC, "courseCode"));
        orders.add(new Sort.Order(Sort.Direction.ASC, "courseName"));
        orders.add(new Sort.Order(Sort.Direction.ASC, "orgName"));
        return new Sort(orders);
    }

    @Transactional
    public void handleSave(StudentSelectedCourseInfo selectedCourseInfo) {
        if (null != selectedCourseInfo) {
            if (StringUtils.isEmpty(selectedCourseInfo.getId())) {
                // add
//                String teacherNo = selectedCourseInfo.getTeacherNo();
//                StudentSelectedCourseInfo isTeacherExist = !StringUtils.isEmpty(teacherNo) ? selectedCourseInfoRepository.findByTeacherNo(teacherNo) : null;
//                if (isTeacherExist == null) {
//                    selectedCourseInfo.setCreateTime(new Date());
//                    selectedCourseInfo.setActive(Constant.ACTIVE);
//                } else {
//                    throw new CustomException(ResultEnum.TeacherNoDuplicatedException.getMessage(), ResultEnum.TeacherNoDuplicatedException.getCode());
//                }

            } else {
//                // update
//                StudentSelectedCourseInfo currentSeletedCourseInfo = selectedCourseInfoRepository.findById(selectedCourseInfo.getId()).orElse(null);
//                // set value for not allow modify Columns
//                selectedCourseInfo.setTeacherNo(currentTeacher.getTeacherNo());
            }
            selectedCourseInfoRepository.save(selectedCourseInfo);
        }
    }

    public PageResultVO<StudentSelectedCourseInfo> findByConditions(StudentSelectedCourseDto selectedCourseDto) {
        Pageable pageable = PageRequest.of(selectedCourseDto.getCurrentPage() - 1, selectedCourseDto.getPageSize(), getSort());
        Page<StudentSelectedCourseInfo> resultPage = selectedCourseInfoRepository.findAll(new Specification<StudentSelectedCourseInfo>() {

            @Override
            public Predicate toPredicate(Root<StudentSelectedCourseInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (!StringUtils.isEmpty(selectedCourseDto.getCourseCode())) {
                    Path courseCode = root.get("courseCode");
                    Predicate p = criteriaBuilder.like(criteriaBuilder.upper(courseCode), "%" + selectedCourseDto.getCourseCode().toUpperCase() + "%");
                    predicateList.add(p);
                }
                if (!StringUtils.isEmpty(selectedCourseDto.getCourseName())) {
                    Path courseName = root.get("courseName");
                    Predicate p = criteriaBuilder.like(criteriaBuilder.upper(courseName), "%" + selectedCourseDto.getCourseName().toUpperCase() + "%");
                    predicateList.add(p);
                }
                if (!StringUtils.isEmpty(selectedCourseDto.getTeacherNo())) {
                    Path teacherNo = root.get("teacherNo");
                    Predicate p = criteriaBuilder.like(criteriaBuilder.upper(teacherNo), "%" + selectedCourseDto.getTeacherNo().toUpperCase() + "%");
                    predicateList.add(p);
                }
                if (!StringUtils.isEmpty(selectedCourseDto.getTeacherName())) {
                    Path teacherName = root.get("teacherName");
                    Predicate p = criteriaBuilder.like(criteriaBuilder.upper(teacherName), "%" + selectedCourseDto.getTeacherName().toUpperCase() + "%");
                    predicateList.add(p);
                }
                if (!StringUtils.isEmpty(selectedCourseDto.getOrgName())) {
                    Path orgName = root.get("orgName");
                    Predicate p = criteriaBuilder.equal(orgName, selectedCourseDto.getOrgName());
                    predicateList.add(p);
                }
                if (!StringUtils.isEmpty(selectedCourseDto.getMajor())) {
                    Path major = root.get("major");
                    Predicate p = criteriaBuilder.equal(major, selectedCourseDto.getMajor());
                    predicateList.add(p);
                }
                if (!StringUtils.isEmpty(selectedCourseDto.getAcademicYear())) {
                    Path academicYear = root.get("academicYear");
                    Predicate p = criteriaBuilder.equal(academicYear, selectedCourseDto.getAcademicYear());
                    predicateList.add(p);
                }
                if (!StringUtils.isEmpty(selectedCourseDto.getTerm())) {
                    Path term = root.get("term");
                    Predicate p = criteriaBuilder.equal(term, selectedCourseDto.getTerm());
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

        PageResultVO<StudentSelectedCourseInfo> pageResultVO = new PageResultVO<>(resultPage.getContent(), resultPage.getTotalElements());
        return pageResultVO;
    }




}
