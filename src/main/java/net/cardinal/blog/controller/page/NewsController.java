package net.cardinal.blog.controller.page;

import net.cardinal.blog.controller.AbstractController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet ({"/news","/news/*"})
public class NewsController extends AbstractController {

    private static final long serialVersionUID = -4981694778818286255L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardToPage("news.jsp",req,resp);
    }
}
