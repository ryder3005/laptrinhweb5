package vn.edu.hcmute.springboot3_4_12.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.hcmute.springboot3_4_12.entity.User;
import vn.edu.hcmute.springboot3_4_12.services.Impl.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class UserController {
    @Autowired
    private  UserService userService;




    @GetMapping
    public String list(@RequestParam(value = "q", required = false) String q,
                       @RequestParam(value = "page", defaultValue = "0") int page,
                       Model model) {
        List<User> userPage = userService.findAll();
        model.addAttribute("users",userPage);
        model.addAttribute("q", q);
        return "admin/users/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/users/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute User user, RedirectAttributes ra) {
        userService.save(user);
        ra.addFlashAttribute("success", "Lưu user thành công");
        return "redirect:/admin/users";
    }

    @GetMapping("/edit/{username}")
    public String edit(@PathVariable String username, Model model) {
        model.addAttribute("user", userService.findById(username));
        return "admin/users/edit";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam String username, RedirectAttributes ra) {
        userService.deleteById(username);
        ra.addFlashAttribute("success", "Xóa user thành công");
        return "redirect:/admin/users";
    }

}