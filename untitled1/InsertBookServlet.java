package servlet;

import bean.Book;
import service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "InsertBookServlet", urlPatterns = "/InsertBookServlet")
public class InsertBookServlet extends HttpServlet {
    BookService bs = new BookService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String author = request.getParameter("author");
        int sales = Integer.parseInt(request.getParameter("sales"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String imgPath = request.getParameter("imgPath");
        System.out.println(request.getParameter("author"));

        if (isBookIdExists(id)) {
            // ���ID�Ѵ��ڣ������ڴ˴������ظ�ID�������������ʾ������Ϣ����ת�ر�ҳ��
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('���鱾 ID �Ѵ��ڣ����������� ID��');");
            out.println("window.location.href='/like/bigwork/addbook.jsp';"); // �ض����ʵ���ҳ��
            out.println("</script>");
        }else{
            // ���� Book ������������ֵ
            Book book = new Book();
            book.setBookId(id);
            book.setBookName(name);
            book.setPrice(price);
            book.setAuthor(author);
            book.setPublish(sales);
            book.setInventory(stock);
            book.setPhotoPath(imgPath);
            bs.addBook(book);
            response.sendRedirect("/like/showServlet");
        }
    }
    private boolean isBookIdExists(int id) {
        if(bs.findBook(id)==null)
            return false;
        else
            return true;
    }
}
