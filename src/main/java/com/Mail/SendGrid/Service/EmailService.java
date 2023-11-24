package com.Mail.SendGrid.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class EmailService {
    @Autowired
    private SendGrid sendGrid;

    @Autowired
    public void sendEmail(String to, String subject,String body) throws IOException{
        Email from= new Email("arun.adepu@theleanapps.com");
        Email toEmail=new Email(to);
        Content content=new Content("text/plain",body);

        Mail mail=new Mail();
        mail.setFrom(from);
        mail.setSubject(subject);
        mail.addContent(content);

        Personalization personalization = new Personalization();
        personalization.addTo(toEmail);
        mail.addPersonalization(personalization);

        Request request =new Request();
        try{
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            sendGrid.api(request);
        }catch (IOException ex){
            throw ex;
        }


    }
}
