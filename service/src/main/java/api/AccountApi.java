package api;

import com.google.gson.Gson;
import model.AccountModel;
import org.json.JSONObject;
import payload.BasicResponse;
import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
                    basicResponse = this.getAllAccounts(req, resp);
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

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getServletPath();
        BasicResponse basicResponse = new BasicResponse();
        BufferedReader br =
                new BufferedReader(new InputStreamReader(req.getInputStream()));
        String json = "";
        try {
            switch (url) {
                case "/api/accounts/once":
                    if (br != null) {
                        json = br.readLine();
                        JSONObject jsonObject = new JSONObject(json);
                        String email = (String) jsonObject.get("email");
                        String password = jsonObject.has("password") ? (String) jsonObject.get("password") : null;
                        String fullName = (String) jsonObject.get("fullName");
                        String address = (String) jsonObject.get("address");
                        String phoneNumber = (String) jsonObject.get("phoneNumber");
                        int accountTypeId = Integer.parseInt(jsonObject.has("password") ? (String) jsonObject.get("accountTypeId") : "-1");

                        AccountModel accountModel = new AccountModel();
                        accountModel.setEmail(email);
                        accountModel.setFullName(fullName);
                        accountModel.setAddress(address);
                        accountModel.setPhoneNumber(phoneNumber);
                        basicResponse = this.updateAccount(email, password, fullName, address, phoneNumber, accountTypeId, req, resp);
                    }
                    break;
                default:
                    basicResponse.setStatusCode(404);
                    basicResponse.setMessage("Not found!");
                    break;
            }
        } catch (Exception e) {
            basicResponse.setStatusCode(500);
            resp.setStatus(500);
            System.err.println(e.getMessage());
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

    private BasicResponse getAllAccounts(HttpServletRequest req, HttpServletResponse resp) {
        BasicResponse response = new BasicResponse();
        AccountService accountService = new AccountService();
        String strPage = req.getParameter("page");
        String strLimit = req.getParameter("limit");
        int page = Integer.parseInt(strPage == null ? "1" : strPage);
        int limit = Integer.parseInt(strLimit == null ? "10" : strLimit);
        List<AccountModel> list = accountService.getAllAccounts(page, limit);
        response.setStatusCode(200);
        response.setData(list);
        return response;
    }

    private BasicResponse getAccountByEmail(String email, HttpServletRequest req, HttpServletResponse resp) {
        BasicResponse response = new BasicResponse();
        AccountService accountService = new AccountService();
        AccountModel account = accountService.getAccountByEmail(email);
        if (account == null || account.getEmail() == null) {
            response.setStatusCode(404);
            response.setMessage("Not found!");
            resp.setStatus(404);
        } else {
            response.setStatusCode(200);
            response.setData(account);
        }
        return response;
    }

    private BasicResponse updateAccount(String email, String password, String fullName, String address, String phoneNumber, int accountTypeId, HttpServletRequest req, HttpServletResponse resp) {
        BasicResponse response = new BasicResponse();
        AccountService accountService = new AccountService();
        AccountModel account = accountService.updateAccount(email, password, fullName, address, phoneNumber, accountTypeId);
        if (account == null || account.getEmail() == null) {
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
