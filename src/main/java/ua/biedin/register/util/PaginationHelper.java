package ua.biedin.register.util;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@UtilityClass
public class PaginationHelper {

    public static Pageable createPagination(int size, int page, String sort, String direction) {
        if (direction.equals("asc")) {
            return PageRequest.of(page, size, Sort.by(sort).ascending());
        } else {
            return PageRequest.of(page, size, Sort.by(sort).descending());
        }
    }
}
