package com.sara.mail.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sara.model.Attachment;
import com.sara.model.Body;
import com.sara.model.EmailAddress;
import com.sara.model.Message;
import com.sara.model.SendMail;
import com.sara.model.ToRecipient;

@RestController
public class SendMailController {

	@Value("${email.to.address}")
	private String[] emailToAddress;

	@Value("${graph.send.mail.url}")
	private String graphSendMailUrl;
	
	@Value("${access.token}")
	private String accessToken;

	@GetMapping("/sendMail")
	public String sendMail() {

		ResponseEntity<String> response = null;
		
		String mailRequestJSON = getMailJSON();

		if (!ObjectUtils.isEmpty(mailRequestJSON)) {

			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();

			headers.set("Authorization", "Bearer " + accessToken); // accessToken can be the secret key you generate.
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<>(mailRequestJSON, headers);
			response = restTemplate.exchange(graphSendMailUrl, HttpMethod.POST, entity, String.class);
			

		}
		return "Response: " + response.getStatusCode().toString();
	}

	public String getMailJSON() {

		SendMail sendmail = new SendMail();

		Body body = new Body();
		body.setContent("This is the body message");
		body.setContentType("Text");

		List<ToRecipient> recipientList = new ArrayList<>();

		if (!ObjectUtils.isEmpty(emailToAddress)) {

			Arrays.asList(emailToAddress).forEach(toaddr -> {

				ToRecipient recipient = new ToRecipient();
				EmailAddress toAddress = new EmailAddress();
				toAddress.setAddress(toaddr);
				recipient.setEmailAddress(toAddress);

				recipientList.add(recipient);
			});
		}

		Attachment attachment = new Attachment();
		attachment.set_odata_type("#microsoft.graph.fileAttachment");
		attachment.setContentBytes("SGVsbG8gV29ybGQ=");
		attachment.setContentType("String");
		attachment.setName("atachment.txt");

		List<Attachment> attachmentList = new ArrayList<>();
		attachmentList.add(attachment);

		Message message = new Message();
		message.setBody(body);
		message.setToRecipients(recipientList);
		message.setSubject("Test Mail");
		message.setAttachments(attachmentList);
		sendmail.setMessage(message);

		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sendmail);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(json);

		return json;
	}
}
