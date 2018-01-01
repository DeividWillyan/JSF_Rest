package br.com.demo.persistence.dao;

import br.com.demo.annotation.ExceptionHandler;
import br.com.demo.custom.CustomRestTemplate;
import br.com.demo.custom.CustomTypeReference;
import br.com.demo.persistence.model.Course;
import br.com.demo.util.ApiUtil;
import br.com.demo.util.JsonUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;

public class CourseDAO implements Serializable {

    private final String LIST_URL = ApiUtil.BASE_URL + "/professor/course/list";

    @Inject
    private CustomRestTemplate customRestTemplate;

    @Inject
    private JsonUtil jsonUtil;

    @Inject
    private CustomTypeReference<List<Course>> listCourse;

    @ExceptionHandler
    public List<Course> getAllCourses(String name) {
        UriComponents url = UriComponentsBuilder.fromUriString(LIST_URL).queryParam("name", name).build();
        ResponseEntity<List<Course>> listResponseEntity = customRestTemplate
                .exchange(url.toUriString(), GET, jsonUtil.tokenizedHttpEntityHeader(), listCourse.typeReference());
        return listResponseEntity.getBody();
    }

}
