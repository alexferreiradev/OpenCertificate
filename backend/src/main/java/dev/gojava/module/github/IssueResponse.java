package dev.gojava.module.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IssueResponse {
    public String id;
    public String assign;
    public String title;
}
