package com.lab02;

public class LoanCalculator {

    /**
     * Tính số tiền phải trả duy nhất cuối kỳ (Deferred Payment Loan).
     * Công thức: A = P * (1 + r/n)^(n*t)
     *
     * @param principal             Số tiền vay ban đầu (VDN)
     * @param annualInterestRate    Lãi suất năm (%) (0 - 100)
     * @param years                 Số năm vay (dương)
     * @param compoundPeriodPerYear Số lần ghép lãi trong một năm (vd: 1, 12)
     * @return Số tiền phải trả (làm tròn 2 chữ số thập phân)
     */
    public static double calculateLumpSumPayment(double principal, double annualInterestRate, int years, int compoundPeriodPerYear) {

        // --- BƯỚC 1: KIỂM TRA ĐẦU VÀO (VALIDATION) ---
        // Yêu cầu: "Ném exception hoặc trả về lỗi phù hợp"

        // 1. Kiểm tra tiền gốc âm
        if (principal < 0) {
            throw new IllegalArgumentException("Principal cannot be negative");
        }

        // 2. Kiểm tra lãi suất (phải từ 0 đến 100)
        if (annualInterestRate < 0 || annualInterestRate > 100) {
            throw new IllegalArgumentException("Interest rate must be between 0 and 100");
        }

        // 3. Kiểm tra số năm (phải > 0)
        if (years <= 0) {
            throw new IllegalArgumentException("Years must be greater than 0");
        }

        // 4. Kiểm tra kỳ hạn ghép lãi (phải > 0)
        if (compoundPeriodPerYear <= 0) {
            throw new IllegalArgumentException("Compound period must be greater than 0");
        }

        // Trường hợp đặc biệt: Vay 0 đồng thì trả 0 đồng
        if (principal == 0) {
            return 0.0;
        }

        // --- BƯỚC 2: TÍNH TOÁN (LOGIC) ---

        // Đổi lãi suất % ra số thập phân. Ví dụ 6% -> 0.06
        // Chia cho số kỳ ghép lãi trong năm. Ví dụ: lãi tháng = 0.06 / 12
        double ratePerPeriod = (annualInterestRate / 100) / compoundPeriodPerYear;

        // Tính tổng số lần ghép lãi toàn bộ kỳ hạn (n * t)
        // Ví dụ: 10 năm * 12 tháng = 120 lần
        double totalPeriods = (double) compoundPeriodPerYear * years;

        // Áp dụng công thức: Amount = P * (1 + r)^n
        double finalAmount = principal * Math.pow(1 + ratePerPeriod, totalPeriods);

        // --- BƯỚC 3: LÀM TRÒN (ROUNDING) ---
        // Yêu cầu: "Phải làm tròn kết quả tới 2 chữ số thập phân"
        return Math.round(finalAmount * 100.0) / 100.0;
    }
}