package com.Mail.SendGrid.Controller;

import com.Mail.SendGrid.EmailRequest;
import com.Mail.SendGrid.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest){
        try{
            emailService.sendEmail(emailRequest.getTo(),emailRequest.getSubject(),emailRequest.getBody());
            return new ResponseEntity<>("Email sent successfully", HttpStatus.OK);
        }catch (IOException ex){
            ex.printStackTrace();
            return new ResponseEntity<>("Failed",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
