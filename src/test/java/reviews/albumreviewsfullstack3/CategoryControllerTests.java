package reviews.albumreviewsfullstack3;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class CategoryControllerTests {

	@InjectMocks
	private CategoryController underTest;
	
	@Mock
	private Model model;
	
	@Mock
	private CategoryRepository categoryRepo;
	
	@Mock
	private Category category;
	
	@Mock
	private Category anotherCategory;
	
	
	@Mock
	private ReviewRepository reviewRepo;
	
	@Mock
	private Review review;
	
	@Mock
	private Review anotherReview;
	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAddSingleCategoryToModel() throws CategoryNotFoundException {
		long randomCategoryId = 1;
		when(categoryRepo.findById(randomCategoryId)).thenReturn(Optional.of(category));
		underTest.findOneCategory(randomCategoryId, model);
		verify(model).addAttribute("categories", category);
	}
	
	@Test
	public void shouldAddAllCategoriesToModel() {
		Collection<Category> allCategories = Arrays.asList(category, anotherCategory);
		when(categoryRepo.findAll()).thenReturn(allCategories);
		underTest.findAllCategories(model);
		verify(model).addAttribute("categories", allCategories);
	}
	
	@Test
	public void shouldAddSingleReviewToModel() throws ReviewNotFoundException {
		long randomReviewId = 12;
		when(reviewRepo.findById(randomReviewId)).thenReturn(Optional.of(review));
		underTest.findOneReview(randomReviewId, model);
		verify(model).addAttribute("reviews", review);
	}
	@Test
	public void shouldAddAllReviewsToModel() {
		Collection<Review> allReviews = Arrays.asList(review, anotherReview);
		when(reviewRepo.findAll()).thenReturn(allReviews);
		underTest.findAllReviews(model);
		verify(model).addAttribute("reviews", allReviews);
	}
}
