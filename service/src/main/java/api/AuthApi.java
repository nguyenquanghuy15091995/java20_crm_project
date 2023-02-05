package api;

import com.google.gson.Gson;
import model.AccountModel;
import payload.BasicResponse;
import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "AuthApi", urlPatterns = {"/auth/login"})
public class AuthApi extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getServletPath();
        BasicResponse basicResponse = new BasicResponse();
        switch (url) {
            case "/auth/login":
                String email = req.getParameter("email");
                String password = req.getParameter("password");
                basicResponse = this.getToken(email, password);
                break;
            default:
                basicResponse.setStatusCode(404);
                basicResponse.setMessage("Not found!");
                break;
        }

        Gson gson = new Gson();
        String dataJson = gson.toJson(basicResponse);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter printWriter = resp.getWriter();
        printWriter.print(dataJson);
        printWriter.flush();
        printWriter.close();
    }

    private BasicResponse getToken(String email, String password) {
        BasicResponse response = new BasicResponse();
        AccountService accountService = new AccountService();
        String result = accountService.getToken(email, password);
        response.setStatusCode(200);
        response.setData(result);
        return response;
    }
}
