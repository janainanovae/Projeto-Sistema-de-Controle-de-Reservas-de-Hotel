import java.util.Locale;

public class Reserva {

    private String nomeHospede;
    private String tipoQuarto;
    private int numeroDias;
    private double valorDiaria;

    // Construtor completo: usa os setters para aproveitar as validações
    public Reserva(String nomeHospede, String tipoQuarto, int numeroDias, double valorDiaria) {
        this.nomeHospede = nomeHospede;
        this.tipoQuarto = tipoQuarto;
        setNumeroDias(numeroDias);
        setValorDiaria(valorDiaria);
    }

    public String getNomeHospede() {
        return nomeHospede;
    }

    public void setNomeHospede(String nomeHospede) {
        this.nomeHospede = nomeHospede;
    }

    public String getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(String tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public int getNumeroDias() {
        return numeroDias;
    }

    // Validação: o número de dias não pode ser menor que 1
    public void setNumeroDias(int numeroDias) {
        if (numeroDias < 1) {
            throw new IllegalArgumentException("O número de dias deve ser no mínimo 1.");
        }
        this.numeroDias = numeroDias;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    // Validação: a diária deve ser maior que zero
    public void setValorDiaria(double valorDiaria) {
        if (valorDiaria <= 0) {
            throw new IllegalArgumentException("O valor da diária deve ser maior que zero.");
        }
        this.valorDiaria = valorDiaria;
    }

    // Valor total da hospedagem = dias x diária
    public double calcularValorTotal() {
        return this.numeroDias * this.valorDiaria;
    }

    @Override
    public String toString() {
        return String.format(Locale.forLanguageTag("pt-BR"),
                "Hóspede: %s | Quarto: %s | Dias: %d | Diária: R$ %.2f | Total: R$ %.2f",
                this.nomeHospede, this.tipoQuarto, this.numeroDias,
                this.valorDiaria, calcularValorTotal());
    }
}
