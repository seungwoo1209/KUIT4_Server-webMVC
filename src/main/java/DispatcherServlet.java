import Response.ResponseExecutor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestMapper requestMapper = new RequestMapper(req,resp);
        String response = requestMapper.proceed();
        ResponseExecutor.sendResponse(response, req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestMapper requestMapper = new RequestMapper(req,resp);
        String response = requestMapper.proceed();
        ResponseExecutor.sendResponse(response, req, resp);
    }
}
