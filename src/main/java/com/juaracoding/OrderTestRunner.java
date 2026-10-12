
package com.juaracoding;

public class OrderTestRunner {

        public static void main(String[] args) {

                OrderTestCase case1 = new OrderTestCase(
                                "ORD-2026-001", "Deny", 10000, 10, 10);

                OrderTestCase case2 = new OrderTestCase(
                                "ORD-2026-002", "Viki", 20000, 5, 0);

                OrderTestCase case3 = new OrderTestCase(
                                "ORD-2026-003", "Iza", 50000, 2, 20);

                OrderTestCase case4 = new OrderTestCase(
                                "ORD-2026-004", "Eko", 20000, 4, 10);

                OrderTestCase[] orderSuite = {
                                case1, case2, case3, case4
                };

                case1.evaluatePayment(90000); // Pembayaran tepat
                case2.evaluatePayment(90000); // Pembayaran kurang
                case3.evaluatePayment(80000); // Diskon voucher 20%
                case4.evaluatePayment(75000); // Pembayaran berlebih

                System.out.println("========== E-COMMERCE ORDER TEST REPORT ==========");
                System.out.println();

                System.out.printf(
                                "%-15s | %-18s | %5s | %15s | %8s | %15s | %15s | %-8s | %-25s%n",
                                "Order ID",
                                "Customer",
                                "Qty",
                                "Harga Barang",
                                "Diskon",
                                "Dibayar",
                                "Grand Total",
                                "Status",
                                "Failure Reason");

                System.out.println("-".repeat(155));

                int passCount = 0;
                int failCount = 0;
                double totalOmzetBersih = 0;

                for (OrderTestCase order : orderSuite) {

                        order.displayRow();

                        if (order.getTestStatus().equals("PASS")) {
                                passCount++;
                                totalOmzetBersih += order.getGrandTotal();
                        } else if (order.getTestStatus().equals("FAIL")) {
                                failCount++;
                        }
                }

                int totalTest = passCount + failCount;

                double passPercentage = 0;

                if (totalTest > 0) {
                        passPercentage = (double) passCount / totalTest * 100;
                }

                System.out.println();
                System.out.println("=============== REKAPITULASI ===============");

                System.out.printf(
                                "Total Omzet Transaksi Bersih : Rp %,.0f%n",
                                totalOmzetBersih);

                System.out.println("Jumlah Test PASS             : " + passCount);
                System.out.println("Jumlah Test FAIL             : " + failCount);
                System.out.println("Total Test                   : " + totalTest);

                System.out.printf(
                                "Persentase Kelulusan         : %.2f%%%n",
                                passPercentage);
        }
}
