package org.habitatmclean.servlet;

import org.habitatmclean.hibernate.Functions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.Properties;

@WebServlet(name = "PdfGenServlet", value="/pdfgen")
public class PdfGenServlet extends HttpServlet {
    private static String installDirectory;
    private static String tempDirectory = System.getProperty("java.io.tmpdir") + "\\";

    @Override
    public void init() throws ServletException {
        // read PdfGeneration.properties for install directory of wkhtmltopdf
        Properties properties = new Properties();
        InputStream input = null;
        try {
            input = getClass().getClassLoader().getResourceAsStream("PdfGeneration.properties");
            properties.load(input);
            installDirectory = (String) properties.get("wkhtmltopdf_install_directory");
            System.out.println(installDirectory);
        } catch (IOException e) {
            System.err.println("pdf generation properties file wasn't located");
        } finally {
            if(input != null){
                try { input.close(); }
                catch (IOException e) { e.printStackTrace(); }
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long ts = System.currentTimeMillis();
        String type = Functions.hiddenInputToHTMLPage(request.getParameter("page"));
        String option;
        String[] cmdArr;
        if(type != null) {
            switch (type) {
                case "log":
                    //TODO log case
                    option = request.getParameter("options");
                    String url = "http://localhost:8085/" + type + ".html?type=" + option;
                    cmdArr = new String[]{installDirectory + "/bin/wkhtmltopdf.exe", "--print-media-type", "--viewport-size", "1920x1080", "-O", "landscape", url, tempDirectory + ts + ".pdf"};
                    break;
                case "people":
                    cmdArr = new String[]{installDirectory + "bin/wkhtmltopdf.exe", "--print-media-type", "--viewport-size", "1920x1080", "-O", "landscape", "http://localhost:8085/" + type + ".html", tempDirectory + ts + ".pdf"};
                    break;
                default:
                    cmdArr = new String[]{installDirectory + "bin/wkhtmltopdf.exe", "--print-media-type", "--viewport-size", "1920x1080", "-O", "landscape", "http://localhost:8085/pages/" + type + ".html", tempDirectory + ts + ".pdf"};
                    break;
            }
            Process p = new ProcessBuilder(cmdArr).start();
            try {
                System.out.println(Arrays.toString(cmdArr));
                p.waitFor();
            } catch (InterruptedException e) {
                System.err.println(e.getStackTrace());
            }
        }

        OutputStream out = response.getOutputStream();
        FileInputStream in = null;
        try {
            in = new FileInputStream(tempDirectory + ts + ".pdf");
        } catch (FileNotFoundException e) {
            System.err.println("file not found, ensure app has write permissions to the directory:\n" + tempDirectory);
            out.write("file wasn't generated".getBytes());
        }

        // file is open for sending
        if(in != null) {
            byte[] buffer = new byte[4096];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.flush();
            try {
                File f = new File(tempDirectory + ts + ".pdf");
                if(!f.delete()) System.err.println("error cleaning up files in:\n" + tempDirectory + ts + ".pdf");
            } catch (Exception e) {
                System.err.println("exception while deleteing file");
                e.printStackTrace();
            }
        }
    }
}
