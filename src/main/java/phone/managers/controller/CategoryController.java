package phone.managers.controller;


import phone.managers.model.Category;
import phone.managers.model.Phone;
import phone.managers.services.category.CategoryService;
import phone.managers.services.phone.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    PhoneService phoneService;
    @ModelAttribute("categories")
    public Page<Category> categories(Pageable pageable){
        return categoryService.findAll(pageable);
    }
    @GetMapping("/category-list")
    public String categoryList(Model model, @PageableDefault(size = 10) Pageable pageable){
        Page<Category> categories = categoryService.findAll(pageable);
        model.addAttribute("categories",categories);
        return "categories/list";
    }
    @GetMapping("/category-create")
    public String categoryCreate(Model model){
        model.addAttribute("category",new Category());
        return "categories/create";
    }
    @PostMapping("/category-create")
    public String categoryCreateSave(Model model, Category category){
        categoryService.save(category);
        model.addAttribute("category",new Category());
        model.addAttribute("message","Created new category");
        return "categories/create";
    }
    @GetMapping("/category-edit/{id}")
    public String categoryEdit(Model model, @PathVariable Long id){
        Category category = categoryService.findById(id);
        model.addAttribute("category",category);
        return "categories/edit";
    }
    @PostMapping("/category-edit")
    public String categoryEditSave(Model model, Category category){
        categoryService.save(category);
        model.addAttribute("category",category);
        model.addAttribute("message","Saved new category");
        return "categories/edit";
    }
    @GetMapping("/category-delete/{id}")
    public String categoryDelete(Model model, @PathVariable Long id){
        Category category = categoryService.findById(id);
        model.addAttribute("category",category);
        return "categories/delete";
    }
    @PostMapping("/category-delete")
    public String categoryDeleteSave(Category category){
        categoryService.remove(category.getId());
        return "redirect:/category-list";
    }
    @GetMapping("/phone-by-category/{id}")
    public String phoneByCategory(@PathVariable Long id, Model model){
        Category category = categoryService.findById(id);
        List<Phone> phones = phoneService.findByCategory(category);
        model.addAttribute("phones",phones);
        return "categories/listPhone";
    }
}
