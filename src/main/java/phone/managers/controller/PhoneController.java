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

@Controller
public class PhoneController {

    @Autowired
    private PhoneService phoneService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @ModelAttribute("categories")
    public Page<Category> categories(Pageable pageable){
        return categoryService.findAll(pageable);
    }


    @GetMapping("/phone-list")
    public String phoneList(Model model, @PageableDefault(size = 10) Pageable pageable) {
        Page<Phone> phones = phoneService.findAll(pageable);
        model.addAttribute("phones",phones);
        return "phones/list";
    }
    @GetMapping("/phone-list-price")
    public String phoneListPrice(Model model, @PageableDefault(size = 10, sort = "price") Pageable pageable) {
        Page<Phone> phones = phoneService.findAll(pageable);
        model.addAttribute("phones",phones);
        return "phones/list";
    }
    @GetMapping("/phone-list-date")
    public String phoneListDate(Model model, @PageableDefault(size = 10, sort = "dateOfPurchase") Pageable pageable) {
        Page<Phone> phones = phoneService.findAll(pageable);
        model.addAttribute("phones",phones);
        return "phones/list";
    }
    @GetMapping("/phone-create")
    public String phoneCreate(Model model){
        model.addAttribute("phone", new Phone());
        return "phones/create";
    }
    @PostMapping("/phone-create")
    public String phoneCreateSave(Model model, Phone phone){
        phoneService.save(phone);
        model.addAttribute("phone", new Phone());
        model.addAttribute("message", "Created new phone");
        return "phones/create";
    }
    @GetMapping("/phone-edit/{id}")
    public String phoneEdit(Model model, @PathVariable Long id){
        Phone phone = phoneService.findById(id);
        model.addAttribute("phone",phone);
        return "phones/edit";
    }
    @PostMapping("/phone-edit")
    public String phoneEditSave(Model model, Phone phone){
        phoneService.save(phone);
        model.addAttribute("phone",phone);
        model.addAttribute("message","Saved new phone");
        return "phones/edit";
    }
    @GetMapping("/phone-delete/{id}")
    public String phoneDelete(Model model, @PathVariable Long id){
        Phone phone = phoneService.findById(id);
        model.addAttribute("phone",phone);
        return "phones/delete";
    }
    @PostMapping("/phone-delete")
    public String phoneDeleteSave(Phone phone){
        phoneService.remove(phone.getId());
        return "redirect:/phone-list";
    }
}
