package edu.univ.erp.api.auth;
import edu.univ.erp.auth.AuthManager;
import edu.univ.erp.auth.LoginHandler;
import edu.univ.erp.data.dao.User_auth_DAO;

public class API {
    private static final AuthManager authService =
            new AuthManager();

    public static LoginHandler login(String username, String password) {
        return authService.login(username, password);
    }

    public static String changePassword(String username, String oldPass, String newPass) {
        return authService.changePassword(username, oldPass, newPass);
    }
}
