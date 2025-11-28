package vn.edu.hcmute.springboot3_4_12.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.hcmute.springboot3_4_12.entity.Category;
import vn.edu.hcmute.springboot3_4_12.entity.User;
import vn.edu.hcmute.springboot3_4_12.services.ICategoryService;
import vn.edu.hcmute.springboot3_4_12.services.IUserService;

import java.util.List;
@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IUserService userService;

    @GetMapping
    public String list(@RequestParam(value = "q", required = false) String q,
                       Model model) {
        List<Category> categories;
        if (q != null && !q.isEmpty()) {
            categories = categoryService.findByCategoryNameContaining(q);
        } else {
            categories = categoryService.findAll();
        }
        model.addAttribute("categories", categories);
        model.addAttribute("q", q);
        return "admin/categories/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("users", userService.findAll()); // chọn User cho Category
        return "admin/categories/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Category category, RedirectAttributes ra) {
        categoryService.save(category);
        ra.addFlashAttribute("success", "Lưu category thành công");
        return "redirect:/admin/categories";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("category", categoryService.findById(id));
        model.addAttribute("users", userService.findAll()); // để chọn lại User nếu cần
        return "admin/categories/edit";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam int id, RedirectAttributes ra) {
        categoryService.deleteById(id);
        ra.addFlashAttribute("success", "Xóa category thành công");
        return "redirect:/admin/categories";
    }

}