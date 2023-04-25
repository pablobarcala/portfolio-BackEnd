package com.portfolio.pruebajwt.controller;

import com.portfolio.pruebajwt.entity.FormContacto;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contacto")
@CrossOrigin
public class ContactoController {
    
    @PostMapping("/enviar")
    public String sendEmail(@RequestBody FormContacto form) {
        Email from = new Email(form.getEmail());
        String subject = "Mensaje de " + form.getNombre();
        Email to = new Email("pablo.d.barcala@gmail.com");
        Content content = new Content("text/plain", form.getMensaje());
        Mail mail = new Mail(from, subject, to, content);
        
        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            return "Mensaje enviado con éxito";
        } catch (IOException e) {
            return "Error al enviar el mensaje " + e.getMessage();
        }
    }
}
