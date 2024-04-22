package service;

import java.util.List;

import org.springframework.stereotype.Service;

import model.User;
import repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsersByFilter(String firstName, String lastName, String email, String city, String state, String country) {
        return userRepository.delete((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (firstName != null && !firstName.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("firstName"), firstName));
            }
            if (lastName != null && !lastName.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("lastName"), lastName));
            }
            if (email != null && !email.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("email"), email));
            }
            if (city != null && !city.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("city"), city));
            }
            if (state != null && !state.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("state"), state));
            }
            if (country != null && !country.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("country"), country));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
