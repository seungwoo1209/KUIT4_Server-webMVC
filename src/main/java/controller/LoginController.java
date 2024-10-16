package controller;

import Response.Constants.ResponseJSPFile;
import Response.Constants.ResponseType;
import Response.Constants.ResponseURL;
import Response.ResponseStringCreator;
import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LoginController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User userFromRequest = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));
        if (isInvalidUser(req, userFromRequest)) {
            return ResponseStringCreator.create(ResponseType.REDIRECT, ResponseJSPFile.LOGIN_FAILED);
        }

        // 로그인 성공
        req.getSession().setAttribute("user", userFromRequest);
        return ResponseStringCreator.create(ResponseType.REDIRECT, ResponseURL.HOME);
    }

    private boolean isInvalidUser(HttpServletRequest req, User userFromRequest) {
        if(!isIdExist(userFromRequest)) {
            return true;
        }

        if(!isPasswordMatchUser(req, userFromRequest)) {
            return true;
        }
        return false;
    }

    private boolean isIdExist(User userFromRequest) {
        return userFromRequest != null;
    }

    private boolean isPasswordMatchUser(HttpServletRequest req, User userFromRequest) {
        return userFromRequest.getPassword().equals(req.getParameter("password"));
    }
}
