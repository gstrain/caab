package org.habitatmclean.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "NavbarServlet", value="/navbar")
public class NavbarServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println();
        out.println("<nav class='navbar navbar-expand-lg navbar-light bg-light'>\n" +
                "  <a class='navbar-brand' href='#'>Navbar</a>\n" +
                "  <button class='navbar-toggler' type='button' data-toggle='collapse' data-target='#navbarNavDropdown' aria-controls='navbarNavDropdown' aria-expanded='false' aria-label='Toggle navigation'>\n" +
                "    <span class='navbar-toggler-icon'></span>\n" +
                "  </button>\n" +
                "  <div class='collapse navbar-collapse' id='navbarNavDropdown'>\n" +
                "    <ul class='navbar-nav'>\n" +
                "      <li class='nav-item active'>\n" +
                "        <a class='nav-link' href='#'>Home <span class='sr-only'>(current)</span></a>\n" +
                "      </li>\n" +
                "      <li class='nav-item'>\n" +
                "        <a class='nav-link' href='#'>Features</a>\n" +
                "      </li>\n" +
                "      <li class='nav-item'>\n" +
                "        <a class='nav-link' href='#'>Pricing</a>\n" +
                "      </li>\n" +
                "      <li class='nav-item dropdown'>\n" +
                "        <a class='nav-link dropdown-toggle' href='http://example.com' id='navbarDropdownMenuLink' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>\n" +
                "          Dropdown link\n" +
                "        </a>\n" +
                "        <div class='dropdown-menu' aria-labelledby='navbarDropdownMenuLink'>\n" +
                "          <a class='dropdown-item' href='#'>Action</a>\n" +
                "          <a class='dropdown-item' href='#'>Another action</a>\n" +
                "          <a class='dropdown-item' href='#'>Something else here</a>\n" +
                "        </div>\n" +
                "      </li>\n" +
                "    </ul>\n" +
                "  </div>\n" +
                "</nav>");
    }
}
