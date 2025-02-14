package com.fzakaria.mvn2nix.cmd;

import com.fzakaria.mvn2nix.model.MavenArtifact;
import com.fzakaria.mvn2nix.model.MavenNixInformation;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class Maven2nixTest {

    @Test
    void artifactsInJsonShouldBeInAlphabeticOrder() throws MalformedURLException {
        HashMap<String, MavenArtifact> dummyArtifacts = new HashMap<>();
        dummyArtifacts.put("artifact-b", new MavenArtifact(new URL("https://dummy/url/b"), "b", "sha256-b"));
        dummyArtifacts.put("artifact-a", new MavenArtifact(new URL("https://dummy/url/a"), "a", "sha256-a"));
        MavenNixInformation artifacts = new MavenNixInformation(dummyArtifacts);
        assertEquals("{\n" +
                "  \"dependencies\": {\n" +
                "    \"artifact-a\": {\n" +
                "      \"layout\": \"a\",\n" +
                "      \"sha256\": \"sha256-a\",\n" +
                "      \"url\": \"https://dummy/url/a\"\n" +
                "    },\n" +
                "    \"artifact-b\": {\n" +
                "      \"layout\": \"b\",\n" +
                "      \"sha256\": \"sha256-b\",\n" +
                "      \"url\": \"https://dummy/url/b\"\n" +
                "    }\n" +
                "  }\n" +
                "}", Maven2nix.toPrettyJson(artifacts));
    }

}
