package com.houlu.java.test.bean;

/**
 * 类名称: StudentBean <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 16/11/12 下午2:36
 */
public class StudentBean extends UserBean {



    private String job;

    private String school;

    public String studentId;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void goSchool(String studentName){
        System.out.println(studentName + "go to school");
    }

    private void goSchoolprivate(String studentName){
        System.out.println(studentName + "private go to school");
    }

    public StudentBean(String studentId){
        this.studentId=studentId;
    }

    public StudentBean(String job, String school) {
        this.job = job;
        this.school = school;
    }

    public StudentBean(String job, String school, String studentId) {
        this.job = job;
        this.school = school;
        this.studentId = studentId;
    }
}
