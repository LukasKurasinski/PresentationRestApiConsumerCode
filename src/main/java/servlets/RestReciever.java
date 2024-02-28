package servlets;

import models.Students;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class is an example of fetching Json data from API
 * It fetches Json data and shows it in a webpage
 */
@WebServlet("")
public class RestReciever extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //TODO check if valid url parameter
        String urlString = req.getParameter("url");
        StringBuilder response=null;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println("API return OK");
        } else {
            System.out.println("API ERROR");
        }

        /* This part can be used to put the fetched Json data into a Students class
        sonb jsonb = JsonbBuilder.create();
        Students students = jsonb.fromJson(response.toString(), Students.class);
        try {
            jsonb.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        */
        req.setAttribute("apiURL", url.toString());
        req.setAttribute("response", response.toString());
        req.getRequestDispatcher("JsonResult.jsp").forward(req,resp);
    }
}

