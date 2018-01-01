package br.com.demo.beans.course;

import br.com.demo.persistence.dao.CourseDAO;
import br.com.demo.persistence.model.Course;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CourseListBean implements Serializable {

    @Inject
    private CourseDAO courseDAO;

    private String name;

    @PostConstruct
    public void init() {
        List<Course> courses = this.courseDAO.getAllCourses("");
        System.out.println(courses);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
