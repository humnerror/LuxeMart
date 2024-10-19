package com.luxemart.mail;


import com.luxemart.product.ProductResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender sender;

    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendPaymentSuccessMail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference
    ) throws MessagingException, UnsupportedEncodingException {

        MimeMessage mimeMessage = sender.createMimeMessage();
        Context context = new Context();
        Map<String,Object> variables = new HashMap<>();
        MimeMessageHelper messageHelper = new MimeMessageHelper(
                                                                mimeMessage,
                                                                MimeMessageHelper.MULTIPART_MODE_MIXED,
                                                                StandardCharsets.UTF_8.name());

        variables.put("customerName",customerName);
        variables.put("amount",amount);
        variables.put("orderReference",orderReference);


        context.setVariables(variables);

        messageHelper.setFrom("jadduravi0@gmail.com","LuxeMart");
        messageHelper.setSubject(EmailTemplates.PAYMENT_TEMPLATE.getSubject());

        String htmlTemplates = EmailTemplates.PAYMENT_TEMPLATE.getTemplates();

        try {

            String template = templateEngine.process(htmlTemplates, context);
            messageHelper.setText(template,true);
            messageHelper.setTo(destinationEmail);
            sender.send(messageHelper.getMimeMessage());

            log.info(String.format("INFO - Email successfully sent to %s with template %s ", destinationEmail, htmlTemplates));

        }
        catch (MessagingException e){
            log.warn(String.format("Could not share the Payment status mail to :: %s", destinationEmail));
        }


    }

    @Async
    public void sendOrderConfirmationMail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference,
            List<ProductResponse> products
    ) throws MessagingException, UnsupportedEncodingException {

        MimeMessage mimeMessage = sender.createMimeMessage();
        Context context = new Context();
        Map<String,Object> variables = new HashMap<>();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED, StandardCharsets.UTF_8.name());

        variables.put("customerName",customerName);
        variables.put("amount",amount);
        variables.put("orderReference",orderReference);
        variables.put("products",products);


        context.setVariables(variables);
        String htmlTemplates = EmailTemplates.ORDER_TEMPLATE.getTemplates();

        messageHelper.setFrom("jadduravi0@gmail.com","LuxeMart");
        messageHelper.setSubject(EmailTemplates.ORDER_TEMPLATE.getSubject());



        try {
            String template = templateEngine.process(htmlTemplates, context);
            messageHelper.setText(template,true);
            messageHelper.setTo(destinationEmail);
            sender.send(messageHelper.getMimeMessage());

            log.info(String.format("INFO - Email successfully sent to %s with template %s ", destinationEmail, htmlTemplates));

        }
        catch (MessagingException e){
            log.warn(String.format("Could not share the Order status mail to :: %s", destinationEmail));
        }


    }







}
