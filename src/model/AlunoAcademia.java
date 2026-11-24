package model;

import exceptions.MatriculaBloqueadaException;
import interfaces.Avaliavel;
import java.util.Objects;

public class AlunoAcademia implements Avaliavel, Comparable<AlunoAcademia> {
    private int matricula;
    private String nome;
    private int idade;
    private String sexo;
    private double peso;
    private double altura;
    private double mensalidade;
    private int frequencia;
    private boolean bloqueado;

    public AlunoAcademia(int matricula, String nome, int idade, String sexo, double peso, double altura, double mensalidade) {
        this.matricula = matricula;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.peso = peso;
        setAltura(altura);
        this.mensalidade = mensalidade;
        this.frequencia = 0;
        this.bloqueado = false;
    }

    public int getMatricula() {
        return matricula;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = (altura > 3.0) ? altura / 100.0 : altura;
    }

    public double getMensalidade() {
        return mensalidade;
    }
    public void setMensalidade(double mensalidade) {
        this.mensalidade = mensalidade;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    @Override
    public double calcularIndiceMassaCorporal() {
        return peso / (altura * altura);
    }

    public void registrarFrequencia() throws MatriculaBloqueadaException {
        if (bloqueado) {
            throw new MatriculaBloqueadaException("Acesso bloqueado: matrícula " + matricula +" está bloqueada por pendência.");
        }
       frequencia++;
    }

    @Override
    public int compareTo(AlunoAcademia aluno2) {
        return Integer.compare(this.matricula, aluno2.matricula);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlunoAcademia that = (AlunoAcademia) o;
        return matricula == that.matricula;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    @Override
    public String toString() {
        return String.format("Mat: %d | %s (%s, %d anos) | IMC: %.2f (%s) | Freq: %d | Status: %s",
                matricula, nome, sexo, idade, calcularIndiceMassaCorporal(), classificarImc(),
                frequencia, (bloqueado ? "BLOQUEADO" : "ATIVO"));
    }
}
