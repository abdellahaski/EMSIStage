package me.aski.EMSIStage.mail;

import me.aski.EMSIStage.entities.Student;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Mailer {
    @Autowired
    private MailService mailService;
    @PostMapping(value = "/admin/mailer",produces="application/json", consumes="application/json")
    public Response postCustomer(@RequestBody String jsonStr) {
        JSONObject jObject = new JSONObject(jsonStr);
        String to=jObject.getString("to");
        String subject=jObject.getString("subject");
        String text=jObject.getString("text");
        mailService.sendSimpleMessage(to,subject,text);
        return new Response("Done", to);
    }
}
