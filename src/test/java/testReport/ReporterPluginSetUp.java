package testReport;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class ReporterPluginSetUp {

    public void setTestInfo(String appiumUrl, String sessionId, String testName, String testStatus, String error) {
        try {
            String url = appiumUrl + "setTestInfo";
            HttpURLConnection connection = createHttpConnection(url);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            String body = "{" +
                    "\"sessionId\":\"" + sessionId + "\"," +
                    "\"testName\":\"" + testName + "\"," +
                    "\"testStatus\":\"" + testStatus + "\"," +
                    "\"error\":\"" + error + "\"" +
                    "}";
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = body.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
        } catch (Exception e) {
            System.out.println("Failed to set Test info");
        }
    }

    public String getReport() throws IOException {
        String url = "http://localhost:4723/getReport";
        HttpURLConnection connection = createHttpConnection(url);
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                return content.toString();
            }
        } else {
            throw new IOException("Failed to get report: HTTP response code " + responseCode);
        }
    }

    public void createReportFile(String data, String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter("appiumReports" + "/" + fileName + ".html");
        fileWriter.write(data);
        fileWriter.close();
    }

    private HttpURLConnection createHttpConnection(String url) throws IOException {
        URL appiumUrl = new URL(url);
        return (HttpURLConnection) appiumUrl.openConnection();
    }
}