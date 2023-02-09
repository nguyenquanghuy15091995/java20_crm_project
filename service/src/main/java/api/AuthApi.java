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

@WebServlet(name = "AuthApi", urlPatterns = {"/auth/login"})
public class AuthApi extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getServletPath();
        BasicResponse basicResponse = new BasicResponse();

        switch (url) {
            case "/auth/login":
                try {
                    BufferedReader br =
                            new BufferedReader(new InputStreamReader(req.getInputStream()));
                    String json = "";
                    if (br != null) {
                        json = br.readLine();
                        JSONObject jsonObject = new JSONObject(json);
                        String email = (String) jsonObject.get("email");
                        String password = (String) jsonObject.get("password");
                        basicResponse = this.getToken(email, password, req, resp);
                    }
                } catch (Exception e) {
                    basicResponse.setStatusCode(500);
                    basicResponse.setMessage("Not found!");
                    resp.setStatus(500);
                }

                break;
            default:
                basicResponse.setStatusCode(404);
                basicResponse.setMessage("Not found!");
                resp.setStatus(404);
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

    private BasicResponse getToken(String email, String password, HttpServletRequest req, HttpServletResponse resp) {
        BasicResponse response = new BasicResponse();
        AccountService accountService = new AccountService();
        String result = accountService.getToken(email, password);
        if (result.equals("")) {
            response.setStatusCode(401);
            resp.setStatus(401);
        } else {
            response.setStatusCode(200);
            resp.setStatus(200);
        }
        response.setData(result);
        return response;
    }
}
