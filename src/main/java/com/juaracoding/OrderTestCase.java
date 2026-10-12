
package com.juaracoding;

public class OrderTestCase {

    private String orderId;
    private String customerName;
    private double itemPrice;
    private int quantity;
    private double voucherDiscountPercent;
    private String testStatus;
    private String failureReason;
    private double actualAmountPaid;

    public OrderTestCase(
            String orderId,
            String customerName,
            double itemPrice,
            int quantity,
            double voucherDiscountPercent) {

        this.orderId = orderId;
        this.customerName = customerName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.voucherDiscountPercent = voucherDiscountPercent;

        this.testStatus = "BLOCKED";
        this.failureReason = "-";
        this.actualAmountPaid = 0.0;
    }

    // Menghitung subtotal
    public double calculateSubtotal() {
        return itemPrice * quantity;
    }

    // Menghitung nominal diskon
    public double calculateDiscountAmount() {
        return calculateSubtotal()
                * (voucherDiscountPercent / 100.0);
    }

    // Menghitung total setelah diskon
    public double calculateGrandTotal() {
        return calculateSubtotal()
                - calculateDiscountAmount();
    }

    public void evaluatePayment(double actualAmountPaid) {
        evaluatePayment(actualAmountPaid, 0.001);
    }

    public void evaluatePayment(
            double actualAmountPaid,
            double tolerance) {

        this.actualAmountPaid = actualAmountPaid;

        double grandTotal = calculateGrandTotal();
        double difference = actualAmountPaid - grandTotal;

        if (Math.abs(difference) <= tolerance) {
            testStatus = "PASS";
            failureReason = "-";
        } else {
            testStatus = "FAIL";

            if (difference < 0) {
                failureReason = String.format(
                        "Underpaid: kurang Rp %.2f",
                        Math.abs(difference));
            } else {
                failureReason = String.format(
                        "Overpaid: lebih Rp %.2f",
                        difference);
            }
        }
    }

    public String getTestStatus() {
        return testStatus;
    }

    public double getGrandTotal() {
        return calculateGrandTotal();
    }

    public void displayRow() {

        System.out.printf(
                "%-15s | %-18s | %5d | %15s | %7.1f%% | %15s | %15s | %-8s | %-25s%n",
                orderId,
                customerName,
                quantity,
                formatRupiah(itemPrice),
                voucherDiscountPercent,
                formatRupiah(actualAmountPaid),
                formatRupiah(calculateGrandTotal()),
                testStatus,
                failureReason);
    }

    private String formatRupiah(double amount) {
        return "Rp " + String.format(
                java.util.Locale.US,
                "%,.0f",
                amount);
    }
}
