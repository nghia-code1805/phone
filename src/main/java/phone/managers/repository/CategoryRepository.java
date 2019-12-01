package phone.managers.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import phone.managers.model.Category;
import phone.managers.model.Phone;

import java.util.List;

public interface CategoryRepository extends PagingAndSortingRepository<Category,Long> {
    List<Category> findAllByPhone(Phone phone);
}
