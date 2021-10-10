package servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Note;

public class NoteServlet extends HttpServlet {

  @Override
  protected void doGet(
    HttpServletRequest request,
    HttpServletResponse response
  )
    throws ServletException, IOException {
    String path = getServletContext().getRealPath("/WEB-INF/note.txt");
    BufferedReader br = new BufferedReader(new FileReader(new File(path)));

    String title, contents;
    title = br.readLine();
    contents = br.readLine();

    Note note = new Note(title, contents);
    System.out.println(title + " " + contents);

    request.setAttribute("note", note);

    if (request.getParameter("edit") == null) {
      getServletContext()
        .getRequestDispatcher("/WEB-INF/viewnote.jsp")
        .forward(request, response);
      return;
    } else if (request.getParameter("edit") != null) {
      getServletContext()
        .getRequestDispatcher("/WEB-INF/editnote.jsp")
        .forward(request, response);
    }
  }

  @Override
  protected void doPost(
    HttpServletRequest request,
    HttpServletResponse response
  )
    throws ServletException, IOException {
    String title = request.getParameter("title");
    String contents = request.getParameter("contents");

    Note note = new Note(title, contents);

    request.setAttribute("note", note);
    getServletContext()
      .getRequestDispatcher("/WEB-INF/viewnote.jsp")
      .forward(request, response);
    return;
  }
}
