package org.habitatmclean.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "PdfGenServlet", value="/pdfgen")
public class PdfGenServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] cmdArr = {"C:\\Program Files\\wkhtmltopdf\\bin\\wkhtmltopdf", "localhost:8085/dbservlet", "table2.pdf"};
        Process p = new ProcessBuilder(cmdArr).start();
        try {
            p.waitFor();
        } catch (InterruptedException e) {
            System.err.println(e.getStackTrace().toString());
        }
        OutputStream out = response.getOutputStream();
        FileInputStream in = null;
        try {
            in = new FileInputStream("C:\\Program Files\\wkhtmltopdf\\bin\\table2.pdf");
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            out.write("file wasn't generated".getBytes());
        }
        byte[] buffer = new byte[1024];
        int length;
        while ((length = in.read(buffer)) > 0){
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
    }
}
