package vn.edu.hcmute.springboot3_4_12.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.hcmute.springboot3_4_12.entity.Video;
import vn.edu.hcmute.springboot3_4_12.services.Impl.CategoryService;
import vn.edu.hcmute.springboot3_4_12.services.Impl.VideoService;


@Controller
@RequestMapping("/admin/videos")
public class VideoController {


    private VideoService videoService;
    private CategoryService categoryService;

    @GetMapping
    public String list(@RequestParam(value="q", required=false) String q,
                       @RequestParam(value="page", defaultValue="0") int page,
                       Model model) {
        Page<Video> videoPage = videoService.search(q, page, 10);
        model.addAttribute("videos", videoPage.getContent()); // Extract the list
        model.addAttribute("videoPage", videoPage); // Keep the Page object for pagination
        model.addAttribute("q", q);
        return "admin/videos/list";

//        return "index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("video", new Video());
        model.addAttribute("categories", categoryService.findAll());
        return "admin/videos/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Video video, RedirectAttributes ra) {
        // validate, save
        videoService.save(video);
        ra.addFlashAttribute("success", "Lưu video thành công");
        return "redirect:/admin/videos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("video", videoService.findById(id));
        model.addAttribute("categories", categoryService.findAll());
        return "admin/videos/edit";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id, RedirectAttributes ra) {
        videoService.deleteById(id);
        ra.addFlashAttribute("success", "Xóa thành công");
        return "redirect:/admin/videos";
    }
}
