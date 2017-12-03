package org.habitatmclean.servlet;

import org.habitatmclean.table.TableFactory;
import org.habitatmclean.table.TableTypeNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "WriteServlet", value = "/write-servlet")
public class WriteServlet extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String table = request.getParameter("table");
            System.out.println(request.getParameter("fk"));
            if(table!= null)
                TableFactory.getTable(table).write(request);
        } catch (TableTypeNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}
