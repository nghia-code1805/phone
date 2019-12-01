package phone.managers.services.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import phone.managers.model.Category;

public interface CategoryService {
    Page<Category> findAll(Pageable pageable);
    Category findById(Long id);
    void save(Category category);
    void remove(Long id);
}
