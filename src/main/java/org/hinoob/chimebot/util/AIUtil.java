package org.hinoob.chimebot.util;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.models.response.OllamaAsyncResultStreamer;
import io.github.ollama4j.models.response.OllamaResult;
import io.github.ollama4j.types.OllamaModelType;
import io.github.ollama4j.utils.OptionsBuilder;
import lombok.SneakyThrows;
import okio.Options;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AIUtil {

    @SneakyThrows
    public static String getResponse(String prompt) {
        String host = "http://192.168.1.173:11434"; // Replace with your server URL
        OllamaAPI ollamaAPI = new OllamaAPI(host);
        ollamaAPI.setRequestTimeoutSeconds(60);
        OllamaResult rs = ollamaAPI.generate("qwen2.5:1.5b", "You are a discord bot. A discord user in the same server have asked you a question, answer fairly shortly, given prompt: " + prompt, false, new OptionsBuilder().build());
        return rs.getResponse();
    }

}
