package com.paisaManagement.util;

public class OtpGenerate {
    public static String generateOTP(int lengthOfOTP) {
        StringBuilder otp = new StringBuilder(lengthOfOTP);
        for (int i = 0; i < lengthOfOTP; i++) {
            int digit = (int) (Math.random() * 10); // generates 0–9
            otp.append(digit);
        }
//        System.out.println(otp);
        return otp.toString();
    }

    // Optional: Default method with fixed 6-digit OTP
    public static String generateOTP() {
        return generateOTP(6);
    }
}
