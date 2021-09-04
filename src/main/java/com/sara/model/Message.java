
package com.sara.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "subject",
    "body",
    "toRecipients",
    "attachments"
})

public class Message {

    @JsonProperty("subject")
    private String subject;
    @JsonProperty("body")
    private Body body;
    @JsonProperty("toRecipients")
    private List<ToRecipient> toRecipients = null;
    @JsonProperty("attachments")
    private List<Attachment> attachments = null;

    @JsonProperty("subject")
    public String getSubject() {
        return subject;
    }

    @JsonProperty("subject")
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @JsonProperty("body")
    public Body getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(Body body) {
        this.body = body;
    }

    @JsonProperty("toRecipients")
    public List<ToRecipient> getToRecipients() {
        return toRecipients;
    }

    @JsonProperty("toRecipients")
    public void setToRecipients(List<ToRecipient> toRecipients) {
        this.toRecipients = toRecipients;
    }

    @JsonProperty("attachments")
    public List<Attachment> getAttachments() {
        return attachments;
    }

    @JsonProperty("attachments")
    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

}
