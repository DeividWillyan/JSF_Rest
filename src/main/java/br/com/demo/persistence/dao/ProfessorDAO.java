package br.com.demo.persistence.dao;

import br.com.demo.annotation.ExceptionHandler;
import br.com.demo.persistence.model.Professor;
import br.com.demo.util.JsonUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.io.Serializable;

import static org.springframework.http.HttpMethod.GET;

public class ProfessorDAO implements Serializable {

    private static final String URL = "http://localhost:8085/v1/professor";

    @Inject
    private JsonUtil jsonUtil;

    @ExceptionHandler
    public Professor getProfessorById(long id) {
        ResponseEntity<Professor> professorEntity = new RestTemplate()
                .exchange(URL+"/1", GET ,new HttpEntity<>(jsonUtil.createTokenizedHeader()), Professor.class);

        System.out.println(professorEntity);
        return professorEntity.getBody();
    }

}
