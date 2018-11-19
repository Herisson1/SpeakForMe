package herisson.application.br.speakforme;

import java.io.Serializable;

public class Usuario implements Serializable{

    private int id;
    private String nome;
    private String email;
    private String idioma;
    private String senha;
    private Integer tipoUsuario;


    /*
    public Usuario(String nome, String email, String idioma, String senha) {
        this.nome = nome;
        this.email = email;
        this.idioma = idioma;
        this.senha = senha;
    }
    */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getTipoUsuario() { return tipoUsuario; }

    public void setTipoUsuario(Integer tipoUsuario) { this.tipoUsuario = tipoUsuario; }

    @Override
    public String toString(){
        return nome;
    }

}
