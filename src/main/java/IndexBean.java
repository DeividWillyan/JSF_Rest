import br.com.demo.persistence.dao.LoginDAO;
import br.com.demo.persistence.dao.ProfessorDAO;
import br.com.demo.persistence.model.support.Token;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class IndexBean implements Serializable {

    private String message = "Deivid Willyan";

    @Inject
    private ProfessorDAO professorDAO;

    private final LoginDAO loginDAO;

    @Inject
    public IndexBean(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    public void checkProfessor() {
        professorDAO.getProfessorById(1);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void login() {
        Token token = loginDAO.loginReturningToken("willyan", "deivid");
        System.out.println(token);
    }


}
