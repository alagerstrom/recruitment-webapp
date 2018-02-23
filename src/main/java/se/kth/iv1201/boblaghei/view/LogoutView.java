package se.kth.iv1201.boblaghei.view;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller responsible for providing mappings used when logging out.
 */
@Controller
public class LogoutView {

    /**
     * Invocated on any request to "/logout". Logs out the currently logged in user from Spring Security and redirects
     * the user to the login page.
     * @param request from which to obtain a HTTP session (cannot be null)
     * @param response not used (can be <code>null</code>)
     * @return the login.html page
     */
    @RequestMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
}
