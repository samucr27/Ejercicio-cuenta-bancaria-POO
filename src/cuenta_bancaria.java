public class cuenta_bancaria {
    private String titular;
    private double saldo;
    private String numerocuenta;

    public cuenta_bancaria() {
    }

    public cuenta_bancaria(String titular, double saldo, String numerocuenta) {
        this.titular = titular;
        this.saldo = saldo;
        this.numerocuenta = numerocuenta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNumerocuenta() {
        return numerocuenta;
    }

    public void setNumerocuenta(String numerocuenta) {
        this.numerocuenta = numerocuenta;
    }

    @Override
    public String toString() {
        return "cuenta_bancaria{" +
                "titular='" + titular + '\'' +
                ", saldo=" + saldo +
                ", numerocuenta='" + numerocuenta + '\'' +
                '}';
    }

    public void depositarDinero(double monto){
        this.saldo += monto;

    }
    public void retirarDinero(double monto){
        this.saldo -= monto;

    }
    public void mostrarInfo(){
        System.out.println("El nombre del titular es: "+ this.titular);
        System.out.println("El saldo de la cuenta es: "+ this.saldo);
        System.out.println("El numero de la cuenta es: "+ this.numerocuenta);
    }
}
