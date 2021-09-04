
package com.sara.model;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "@odata.type",
    "contentBytes",
    "contentType",
    "name"
})
public class Attachment {

    @JsonProperty("@odata.type")
    private String _odata_type;
    @JsonProperty("contentBytes")
    private String contentBytes;
    @JsonProperty("contentType")
    private String contentType;
    @JsonProperty("name")
    private String name;

    @JsonProperty("@odata.type")
    public String get_odata_type() {
        return _odata_type;
    }

    @JsonProperty("@odata.type")
    public void set_odata_type(String _odata_type) {
        this._odata_type = _odata_type;
    }

    @JsonProperty("contentBytes")
    public String getContentBytes() {
        return contentBytes;
    }

    @JsonProperty("contentBytes")
    public void setContentBytes(String contentBytes) {
        this.contentBytes = contentBytes;
    }

    @JsonProperty("contentType")
    public String getContentType() {
        return contentType;
    }

    @JsonProperty("contentType")
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

}
