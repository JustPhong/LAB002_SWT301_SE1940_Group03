package org.example;

// 👇 DÒNG QUAN TRỌNG NHẤT: Gọi thằng LoanCalculator từ nhà bên kia sang
import com.lab02.LoanCalculator;

public class Main {
    public static void main(String[] args) {
        // Giờ thì gọi thoải mái, không bị lỗi đỏ nữa
        double result = LoanCalculator.calculateLumpSumPayment(100000, 6, 10, 1);

        System.out.println("Kết quả tính được: " + result);

        if (result == 179084.77) {
            System.out.println("✅ CHUẨN MEN! Code chạy đúng y chang web.");
        } else {
            System.out.println("❌ SAI SỐ RỒI! Kiểm tra lại.");
        }
    }
}