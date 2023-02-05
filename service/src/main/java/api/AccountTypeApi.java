package api;

import com.google.gson.Gson;
import model.AccountTypeModel;
import payload.BasicResponse;
import service.AccountTypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "AccountTypeApi", urlPatterns = {"/api/account-types"})
public class AccountTypeApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getServletPath();
        BasicResponse basicResponse = new BasicResponse();
        switch (url) {
            case "/api/account-types":
                basicResponse = this.getAllAccountTypes();
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

    private BasicResponse getAllAccountTypes() {
        BasicResponse response = new BasicResponse();
        AccountTypeService accountTypeService = new AccountTypeService();
        List<AccountTypeModel> list = accountTypeService.getAllAccountTypes();
        response.setStatusCode(200);
        response.setData(list);
        return response;
    }
}
