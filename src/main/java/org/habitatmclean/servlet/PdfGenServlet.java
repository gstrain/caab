package org.habitatmclean.servlet;

import org.habitatmclean.entity.House;
import org.habitatmclean.entity.Property;
import org.habitatmclean.hibernate.Functions;
import org.habitatmclean.hibernate.HibernateAdapter;
import org.habitatmclean.hibernate.HibernateUtil;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;
import java.util.SortedSet;

@WebServlet(name = "PdfGenServlet", value="/pdfgen")
public class PdfGenServlet extends HttpServlet {
    private static String installDirectory;
    private static String tempDirectory = System.getProperty("java.io.tmpdir") + "\\";
    private static String port;

    @Override
    public void init() {
        // read PdfGeneration.properties for install directory of wkhtmltopdf
        Properties properties = new Properties();
        InputStream input = null;
        try {
            input = getClass().getClassLoader().getResourceAsStream("PdfGeneration.properties");
            properties.load(input);
            installDirectory = (String) properties.get("wkhtmltopdf_install_directory");
            port = (String) properties.get("server_port");
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // return a generated html body for the report
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        String page = Functions.hiddenInputToHTMLPage(request.getParameter("page"));
        Long pk = null;
        StringBuilder responseText = new StringBuilder("");
        try {
            pk = Long.parseLong(request.getParameter("primary_k"));
        } catch (NumberFormatException e) { }
        if(page != null && pk != null) {
            sessionFactory.getCurrentSession().beginTransaction();
            switch(page) {
                case "houses":
                    House house = (House) HibernateAdapter.getBoByEntityName("House").findByPrimaryKey(pk);
                    sessionFactory.getCurrentSession().getTransaction().commit();

                    responseText.append("<li class='list-group-item'><h5>Address: </h5><p>" + house.getHouseAddress().toString() + "</p></li>\n");
                    responseText.append("<li class='list-group-item'><h5>Property: </h5><p>" + house.getHouseProperty() + "</p></li>\n");
                    responseText.append("<li class='list-group-item'><h5>Family: </h5><p>" + house.getHouseFamily() + "</p></li>\n");
                    responseText.append("<li class='list-group-item'><h5>Construction Status: </h5><p>" + house.getHouseConstructionStatus() + "</p></li>\n");
                    responseText.append("<li class='list-group-item'><h5>Size: </h5><p>" + house.getSize() + " square ft.</p></li>\n");
                    responseText.append("<li class='list-group-item'><h5>Bedrooms: </h5><p>" + house.getBedrooms() + "</p></li>\n");
                    responseText.append("<li class='list-group-item'><h5>Bathrooms: </h5><p>" + house.getBathrooms() + "</p></li>\n");
                    responseText.append("<li class='list-group-item'><h5>Construction Cost: </h5><p>" + house.getConstruction_cost() + "</p></li>\n");
                    responseText.append("<li class='list-group-item'><h5>House Style: </h5><p>" + house.getHouse_style() + "</p></li>");
                    break;
                case "properties":
                    Property property = (Property) HibernateAdapter.getBoByEntityName("Property").findByPrimaryKey(pk);
                    HibernateUtil.initializeAndUnproxy(property.getHouses());
                    SortedSet houses = property.getHouses();
                    sessionFactory.getCurrentSession().getTransaction().commit();
                    Iterator itr = houses.iterator();

                    responseText.append("<li class='list-group-item'><h5>Address: </h5><p>" + property.getPropertyAddress().toString() + "</p></li>\n");
                    responseText.append("<li class='list-group-item'><h5>Zone: </h5><p>" + property.getZone().toString() + "</p></li>\n");
                    responseText.append("<li class='list-group-item'><h5>Owner: </h5><p>" + property.getOwner() + "</p></li>\n");
                    responseText.append("<li class='list-group-item'><h5>Property Status: </h5><p>" + property.getProperty_status() + "</p></li>\n");
                    responseText.append("<li class='list-group-item'><h5>Appraised Value: </h5><p>" + property.getAppraised_value() + " square ft.</p></li>\n");
                    responseText.append("<li class='list-group-item'><h5>Appriased Date: </h5><p>" + property.getAppraised_date() + "</p></li>\n");
                    responseText.append("<li class='list-group-item'><h5>Taxes: </h5><p>" + property.getTaxes() + "</p></li>\n");
                    responseText.append("<li class='list-group-item'><h5>Notes: </h5><p>" + property.getNotes() + "</p></li>\n");
                    while(itr.hasNext()) {
                        responseText.append("<li class='list-group-item'><h5>House: </h5><p>" + itr.next() + "</p></li>\n");
                    }
                    break;
            }
            response.getWriter().print(responseText.toString());
        }
        // cleanup just in case
        if(sessionFactory.getCurrentSession().getTransaction().getStatus() == TransactionStatus.ACTIVE)
            sessionFactory.getCurrentSession().getTransaction().commit();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long ts = System.currentTimeMillis();
        String page = Functions.hiddenInputToHTMLPage(request.getParameter("page"));
        String method = request.getParameter("method");
        String[] cmdArr = new String[]{"echo", "."};
        if(method != null) {
            if (page != null && method.equals("table")) {
                int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
                int rowsToShow = Integer.parseInt(request.getParameter("perPage"));
                switch (page) {
                    case "logs":
                        // pdf=true is a flag used in ajax.js to prevent running the resizeHeaders() function
                        String pname = request.getParameter("pname");
                        String fk = request.getParameter("fk");
                        String url = "http://localhost:" + port + "/pages/" + page + ".html?pname=" + pname + "&fk=" + fk + "&pdf=true";
                        cmdArr = new String[]{installDirectory + "bin/wkhtmltopdf.exe", "--print-media-type", "--viewport-size", "1920x1080", "-O", "landscape", url, tempDirectory + ts + ".pdf"};
                        break;
                    case "people":
                        cmdArr = new String[]{installDirectory + "bin/wkhtmltopdf.exe", "--print-media-type", "--viewport-size", "1920x1080", "-O", "landscape", "http://localhost:" + port + "/" + page + ".html?page=" + pageNumber + "&perPage=" + rowsToShow + "&pdf=true", tempDirectory + ts + ".pdf"};
                        break;
                    default: // organization, zones, properties, houses, etc. anything that is in the /web/pages/ directory
                        cmdArr = new String[]{installDirectory + "bin/wkhtmltopdf.exe", "--print-media-type", "--viewport-size", "1920x1080", "-O", "landscape", "http://localhost:" + port + "/pages/" + page + ".html?&page=" + pageNumber + "&perPage=" + rowsToShow + "&pdf=true", tempDirectory + ts + ".pdf"};
                        break;
                }
            } else if (method.equals("individual")) {
                Long pk = Long.parseLong(request.getParameter("primary_k"));
                switch (page) {
                    case "houses":
                        String options = "?page=house&method=individual"; // the page variable holds 'houses', we need 'house'
                        options += "&primary_k=" + pk;
                        cmdArr = new String[]{installDirectory + "bin/wkhtmltopdf.exe", "--print-media-type", "--viewport-size", "1920x1080", "-O", "portrait", "http://localhost:" + port + "/reports/house_report.html" + options, tempDirectory + ts + ".pdf"};
                        break;
                    case "properties":
                        options = "?page=property&method=individual"; // the page variable holds 'houses', we need 'house'
                        options += "&primary_k=" + pk;
                        cmdArr = new String[]{installDirectory + "bin/wkhtmltopdf.exe", "--print-media-type", "--viewport-size", "1080x1920", "-O", "portrait", "http://localhost:" + port + "/reports/property_report.html" + options, tempDirectory + ts + ".pdf"};
                        break;
                }
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
            out.write("file wasn't generated, parameters were probably incorrect".getBytes());
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
