package TENNIS.TENNISINFO.Category.Repository;

import TENNIS.TENNISINFO.Category.Domain.Category;
import TENNIS.TENNISINFO.Player.Domain.Career;
import TENNIS.TENNISINFO.Player.Domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByRapidCategoryId(String rapidCategoryId);
}
