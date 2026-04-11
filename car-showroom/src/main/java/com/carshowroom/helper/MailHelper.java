package com.carshowroom.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailHelper {

    private final JavaMailSender mailSender;

    // Send a simple plain text email
    public void sendEmail(String toEmail, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    // Send sale confirmation email to customer
    public void sendSaleConfirmationEmail(String toEmail,
                                          String customerName,
                                          String carMake,
                                          String carModel,
                                          Double salePrice) {

        String subject = "Car Purchase Confirmation - " + carMake + " " + carModel;

        String body = "Dear " + customerName + ",\n\n"
                + "Thank you for your purchase!\n\n"
                + "Car: " + carMake + " " + carModel + "\n"
                + "Sale Price: " + salePrice + "\n\n"
                + "We hope you enjoy your new car.\n\n"
                + "Regards,\n"
                + "Car Showroom Team";

        sendEmail(toEmail, subject, body);
    }

}