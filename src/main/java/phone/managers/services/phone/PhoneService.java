package phone.managers.services.phone;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import phone.managers.model.Category;
import phone.managers.model.Phone;

import java.util.List;

public interface PhoneService {

    Page<Phone> findAll(Pageable pageable);
    List<Phone> findByCategory(Category category);
    Phone findById(Long id);
    void save(Phone phone);
    void remove(Long id);
}
