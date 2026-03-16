package Session_7.BaiTap.Bai3;

public class Main {
    public static void main(String[] args) {
        PaymentMethod cod = new CODPayment();
        PaymentProcessor processor1 = new PaymentProcessor(cod);
        processor1.process(500000);

        PaymentMethod card = new CreditCardPayment();
        PaymentProcessor processor2 = new PaymentProcessor(card);
        processor2.process(1000000);

        PaymentMethod momo = new MomoPayment();
        PaymentProcessor processor3 = new PaymentProcessor(momo);
        processor3.process(750000);

        System.out.println("---- Kiểm tra LSP ----");

        PaymentMethod payment = new CreditCardPayment();
        PaymentProcessor processor4 = new PaymentProcessor(payment);
        processor4.process(1000000);

        payment = new MomoPayment();
        processor4 = new PaymentProcessor(payment);
        processor4.process(750000);
    }
}