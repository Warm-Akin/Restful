package com.akin.restful.xuexin;

public class StudentSelectedCourseDto {

    private String courseCode;

    private String courseName;

    private String studentNo;

    private String studentName;

    private String orgName;
//
//    private String majorCode;
//
    private String major;

    private String retakeFlag;
    private String usualScore;
    private String middleScore;
    private String endScore;
    private String labScore;
    private String finalScore;
    private String convertScore;
    private String resitScore;
    private String resitMemo;
    private String repairScore;
    private Double gradePoint;
    private String memo;
    private String creator;
    private String parentOrgId;
    private Integer totalHours;
    private String grade;
    private String academicYear;
    private String term;
    private String teacherNo;
    private String teacherName;
    private String selectedCoureNo;
    private String courseType;
    private String credit;

    private Integer currentPage;

    private Integer pageSize;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode=courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName=courseName;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo=studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName=studentName;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear=academicYear;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term=term;
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo=teacherNo;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName=teacherName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName=orgName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major=major;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage=currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize=pageSize;
    }
}
