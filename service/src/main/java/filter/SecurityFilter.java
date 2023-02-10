package filter;

import com.google.gson.Gson;
import config.JWT;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.json.JSONObject;
import repository.AccountRepository;
import service.AccountService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@WebFilter(filterName = "SecurityFilter", urlPatterns = "/api/*")
public class SecurityFilter extends HttpFilter {

    /* No one knows about this, only we know */

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        /* If there is no authorization header or if it doesn't have login credentials or a token
         * then there is no point of continuing */
        String authorization = req.getHeader("Authorization");
        if (authorization == null || !(authorization.matches("(Basic)|(Bearer) .+"))) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        try {
            /* Extract the token or base64encoded login credentials */
            String token = authorization.replaceAll("(Basic)|(Bearer)", "").trim();

            /* Let's find out whether it is the token or login credentials */
            if (authorization.matches("(Basic)|(Bearer) .+")) {
                String[] chunks = token.split("\\.");
                Base64.Decoder decoder = Base64.getUrlDecoder();
                String payload = new String(decoder.decode(chunks[1]));

                String email = (String) (new JSONObject(payload).get("email"));
                AccountService accountService = new AccountService();

                /* Let's check whether the username and password is correct
                 * You may need to make a call to the DB server here in a real-world application */
                if (accountService.checkToken(email)) {
                    HttpServletResponse resp = (HttpServletResponse) res;
                    resp.setHeader("Access-Control-Allow-Origin", "*");
                    resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
                    resp.setHeader("Access-Control-Allow-Headers", "authorization,withCredentials, content-type, xsrf-token, Cache-Control, Cookie");
                    resp.addHeader("Access-Control-Expose-Headers", "xsrf-token");
                    resp.addHeader("Access-Control-Allow-Credentials", "true");
                    chain.doFilter(req, res);
                    return;
                }
            }
        } catch (Exception e) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}