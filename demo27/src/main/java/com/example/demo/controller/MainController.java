package com.example.demo.controller;

import com.example.demo.ultil.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class MainController {

    @GetMapping(value = { "/", "/welcome" })
    public String welcomePage(Model model, Principal principal) {
        if (principal != null) {
            System.out.println("------username------:" + principal.getName());
        } else {
            System.out.println("------not login--------------------------------");
        }
        model.addAttribute("title", "Xin chào các bạn");
        model.addAttribute("message", "Đến với trang web của chúng tôi!");
        return "welcomePage";
    }

    @GetMapping(value = "/login")
    public String loginPage(Model model) {
        // You can add more attributes, such as a custom login error message
        model.addAttribute("title", "Login");
        return "loginPage";  // Return to the login page
    }

    @GetMapping("/logoutSuccessful")
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout Successful");
        model.addAttribute("message", "You have been successfully logged out.");
        return "logoutSuccessfulPage";
    }


    @GetMapping(value = "/userInfo")
    public String userInfo(Model model, Principal principal) {
        if (principal != null) {
            String userName = principal.getName();
            System.out.println("User Name: " + userName);

            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);
            System.out.println("-----------------detail----------");
            System.out.println(userInfo);
        } else {
            model.addAttribute("message", "Vui lòng đăng nhập để xem thông tin cá nhân.");
        }
        return "userInfoPage";
    }

    @GetMapping(value = "/admin")
    public String adminPage(Model model, Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = WebUtils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);
        }
        return "adminPage";
    }

    @GetMapping(value = "/403")
    public String accessDenied(Model model, Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = WebUtils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() + " Bạn không có tuổi truy cập vào trang web này!";
            model.addAttribute("message", message);
        } else {
            model.addAttribute("message", "Vui lòng đăng nhập để tiếp tục.");
        }
        return "403Page";  // Trả về trang lỗi 403 nếu người dùng không có quyền
    }

    // Phương thức đăng nhập được xử lý bởi Spring Security, không cần thiết phải xử lý tại đây
    // Thêm POST cho form đăng nhập nếu cần xử lý logic khác ngoài Spring Security
    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password, Model model) {
        // Xử lý đăng nhập, nhưng Spring Security đã quản lý việc này
        System.out.println("Login attempt with username: " + username);
        model.addAttribute("message", "Đăng nhập thành công với tài khoản: " + username);
        return "loginPage";  // Trả về trang loginPage sau khi xử lý POST (nếu cần)
    }

    // Ví dụ phương thức xử lý POST cho một form khác (nếu có)
    @PostMapping("/submitForm")
    public String handleFormSubmit(@RequestParam String data, Model model) {
        // Xử lý dữ liệu form
        model.addAttribute("response", "Dữ liệu đã được gửi: " + data);
        return "responsePage";  // Trả về trang kết quả sau khi xử lý form
    }
}
