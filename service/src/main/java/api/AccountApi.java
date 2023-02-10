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

@WebServlet(name = "AccountApi", urlPatterns = {"/api/accounts", "/api/accounts/once"})
public class AccountApi extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getServletPath();
        BasicResponse basicResponse = new BasicResponse();
        try {
            switch (url) {
                case "/api/accounts":
                    basicResponse = this.getAllAccounts();
                    break;
                case "/api/accounts/once":
                    String email = (String) req.getParameter("email");
                    basicResponse = this.getAccountByEmail(email, req, resp);
                    break;
                default:
                    basicResponse.setStatusCode(404);
                    basicResponse.setMessage("Not found!");
                    break;
            }
        } catch (Exception e) {
            basicResponse.setStatusCode(500);
            resp.setStatus(500);
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
    private BasicResponse getAllAccounts() {
        BasicResponse response = new BasicResponse();
        AccountService accountService = new AccountService();
        List<AccountModel> list = accountService.getAllAccounts();
        response.setStatusCode(200);
        response.setData(list);
        return response;
    }

    private BasicResponse getAccountByEmail(String email, HttpServletRequest req, HttpServletResponse resp) {
        BasicResponse response = new BasicResponse();
        AccountService accountService = new AccountService();
        AccountModel account = accountService.getAccountByEmail(email);
        if(account == null || account.getEmail() == null) {
            response.setStatusCode(404);
            response.setMessage("Not found!");
            resp.setStatus(404);
        } else {
            response.setStatusCode(200);
            response.setData(account);
        }
        return response;
    }
}
