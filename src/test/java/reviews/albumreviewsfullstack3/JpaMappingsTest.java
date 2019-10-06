package reviews.albumreviewsfullstack3;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JpaMappingsTest {

	@Resource
	private TestEntityManager entityManager;
	
	@Resource
	private CategoryRepository categoryRepo;
	
	@Resource 
	private ReviewRepository reviewRepo;
	
	@Test 
	public void shouldSaveAndLoadCategory() {
		Category category = categoryRepo.save(new Category("90s Albums"));
		long categoryId = category.getId();
		
		entityManager.flush(); 
		entityManager.clear(); 
		
		Optional<Category> result = categoryRepo.findById(categoryId);
		category = result.get();
		assertThat(category.getGenre(), is("90s Albums"));
	}
	
	@Test 
	public void shouldGenerateCategoryId() {
		Category category = categoryRepo.save(new Category("90s Albums"));
		long categoryId = category.getId();
		
		entityManager.flush(); 
		entityManager.clear();
		
		assertThat(categoryId, is(greaterThan(0L)));
	}
	
	@Test 
	public void shouldSaveAndLoadReview() {
		Review review = new Review("review1", "description", "imageUrl");
		review = reviewRepo.save(review);
		long reviewId = review.getId();
		
		entityManager.flush(); 
		entityManager.clear(); 
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		assertThat(review.getName(), is("review1"));
	}
	
	@Test
	public void shouldGenerateReviewId() {
		Review review = reviewRepo.save(new Review("Name", "Description", "imgUrl"));
		long reviewId = review.getId();
		
		entityManager.flush(); 
		entityManager.clear();
		
		assertThat(reviewId, is(greaterThan(0L)));
		
	}
	
	@Test
	public void shouldEstablishCategoryToReviewRelationship() {
		Review loveless = reviewRepo.save(new Review("Loveless", "Shoegaze", "imageUrl"));
		Review okComputer = reviewRepo.save(new Review("OK Computer", "The Perfect Album", "imageUrl"));
		
		Category category1 = new Category("90s Albums", loveless, okComputer);
		category1 = categoryRepo.save(category1);
		long categoryId = category1.getId();
		
		entityManager.flush(); 
		entityManager.clear();
		
		Optional<Category> result = categoryRepo.findById(categoryId);
		category1 = result.get();
		
		assertThat(category1.getReviews(), containsInAnyOrder(loveless, okComputer));
		
	}
	
	
}
