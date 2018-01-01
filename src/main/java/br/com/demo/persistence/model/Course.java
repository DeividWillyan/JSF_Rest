package br.com.demo.persistence.model;

public class Course extends AbstractEntity {

    private String nome;
    private Professor professor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public static final class Builder {
        private Course course;

        private Builder() {
            course = new Course();
        }

        public static Builder newCourse() {
            return new Builder();
        }

        public Builder nome(String nome) {
            course.setNome(nome);
            return this;
        }

        public Builder id(Long id) {
            course.setId(id);
            return this;
        }

        public Builder professor(Professor professor) {
            course.setProfessor(professor);
            return this;
        }

        public Course build() {
            return course;
        }
    }
}
