package TENNIS.TENNISINFO.Category.Domain;

import TENNIS.TENNISINFO.Common.domain.CategoryRapidDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_category")
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CATEGORY_ID")
    private Long categoryId;
    @Column(name="RAPID_CATEGORY_ID")
    private String rapidCategoryId;
    @Column(name="CATEGORY_NAME")
    private String categoryName;

    public Category(CategoryRapidDTO categoryRapidDTO){
        this.rapidCategoryId = categoryRapidDTO.getCategoryId();
        this.categoryName = categoryRapidDTO.getCategoryName();

    }
}
