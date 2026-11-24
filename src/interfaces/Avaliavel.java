package interfaces;

public interface Avaliavel {
    double calcularIndiceMassaCorporal();

    default String classificarImc(){
        double imc = calcularIndiceMassaCorporal();
        if (imc < 18.5) return "Abaixo do peso";
        if (imc < 25.0) return "Peso normal";
        if (imc < 30.0) return "Sobrepeso";
        return "Obesidade";
    }
}
