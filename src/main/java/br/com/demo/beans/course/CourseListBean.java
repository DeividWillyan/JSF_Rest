package br.com.demo.beans.course;

import br.com.demo.persistence.dao.CourseDAO;
import br.com.demo.persistence.model.Course;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class CourseListBean implements Serializable {

    @Inject
    private CourseDAO courseDAO;

    private String name = "";
    private List<Course> courses;

    @PostConstruct
    public void init() {
        search();
    }

    public void search() {
        courses = this.courseDAO.getAllCourses(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
