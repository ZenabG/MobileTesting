package com.monefy.testReport;

import com.monefy.PageOperations;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * ReporterPluginSetUp class is responsible for setting test information and generating test reports.
 */
public class ReporterPluginSetUp {

    /**
     * Sets the test information by sending a POST request to the Appium server.
     *
     * @param appiumUrl the URL of the Appium server
     * @param sessionId the session ID of the test
     * @param testName the name of the test
     * @param testStatus the status of the test (PASS/FAIL)
     * @param error the error message if the test failed
     */
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
            System.out.println("Set Info Response Code: " + responseCode);
        } catch (Exception e) {
            System.out.println("Failed to set Test info");
        }
    }

    /**
     * Retrieves the test report by sending a GET request to the Appium server.
     *
     * @param appiumUrl the URL of the Appium server
     * @return the test report as a string
     * @throws IOException if an I/O error occurs
     */
    public String getReport(String appiumUrl) throws IOException {
        String url = appiumUrl + "getReport";

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

    /**
     * Creates a report file with the given data.
     *
     * @param data the data to be written to the file
     * @param fileName the name of the file
     * @throws IOException if an I/O error occurs
     */
    public void createReportFile(String data, String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName + ".html");
        fileWriter.write(data);
        fileWriter.close();
    }

    /**
     * Creates an HTTP connection to the given URL.
     *
     * @param url the URL to connect to
     * @return the HTTP connection
     * @throws IOException if an I/O error occurs
     */
    private HttpURLConnection createHttpConnection(String url) throws IOException {
        URL appiumUrl = new URL(url);
        return (HttpURLConnection) appiumUrl.openConnection();
    }
}