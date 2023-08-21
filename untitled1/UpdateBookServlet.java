package servlet;

import bean.Book;
import service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.System.out;

@WebServlet(name="UpdateBookServlet",urlPatterns = "/UpdateBookServlet")
public class UpdateBookServlet extends HttpServlet {
    BookService bs =new BookService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int bookId = Integer.parseInt(request.getParameter("id"));
        Book before = bs.findBook(bookId);
        request.getSession().setAttribute("before", before);
        response.sendRedirect("bigwork/edit.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String author = request.getParameter("author");
        int sales = Integer.parseInt(request.getParameter("sales"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String imgPath = request.getParameter("imgPath");
        Book book = new Book();
        book.setBookId(id);
        book.setBookName(name);
        book.setPrice(price);
        book.setAuthor(author);
        book.setPublish(sales);
        book.setInventory(stock);
        book.setPhotoPath(imgPath);
        bs.updateBook(book);
        response.sendRedirect("/like/showServlet");
    }
}
