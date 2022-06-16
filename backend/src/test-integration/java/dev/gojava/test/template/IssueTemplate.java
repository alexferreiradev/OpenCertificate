package dev.gojava.test.template;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public interface IssueTemplate {

    static String issueValid() throws URISyntaxException, IOException {
        return Files.readString(Path.of(IssueTemplate.class.getResource("/templates/valid-issue.json").toURI()));
    }
}
