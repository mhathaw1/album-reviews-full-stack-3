package reviews.albumreviewsfullstack3;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerMvcTest {

	@Resource
	private MockMvc mvc;
	
	@MockBean
	private CategoryRepository categoryRepo;
	
	@MockBean
	private ReviewRepository reviewRepo;
	
	@Mock
	private Category category;
	
	@Mock
	private Category anotherCategory;
	
	@Mock
	private Review review;
	
	@Mock 
	private Review anotherReview;
	
	
	
	@Test
	public void shouldRouteToSingleCategoryView() throws Exception {
		long randomCategoryId = 1;
		when(categoryRepo.findById(randomCategoryId)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=1")).andExpect(status().isOk());
	}
	
	@Test //not sure why this passes already...
	public void shouldBeOkForSingleCategory() throws Exception {
		long randomCategoryId = 1;
		when(categoryRepo.findById(randomCategoryId)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=1")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldNotBeOkForSingleCategory() throws Exception {
		mvc.perform(get("/category?id=1")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldPutSingleCategoryIntoModel() throws Exception {
		when(categoryRepo.findById(1L)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=1")).andExpect(model().attribute("categories",  is(category)));
	}
	
	@Test
	public void shouldRouteToAllCategoriesView() throws Exception {
		mvc.perform(get("/categories")).andExpect(view().name(is("categories")));
	}
	
	@Test
	public void shouldBeOkForAllCategories() throws Exception {
		mvc.perform(get("/categories")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldPutAllCategoriesIntoModel() throws Exception {
		Collection<Category> allCategories = Arrays.asList(category, anotherCategory);
		when(categoryRepo.findAll()).thenReturn(allCategories);

		mvc.perform(get("/categories")).andExpect(model().attribute("categories", is(allCategories)));
	}
	
	@Test
	public void shouldRouteToSingleReviewView() throws Exception {
		long arbitraryReviewId = 42;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=42")).andExpect(view().name(is("review")));
	}
	
	@Test
	public void shouldBeOkForSingleReview() throws Exception {
		long arbitraryReviewId = 42;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=42")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldNotBeOkForSingleReview() throws Exception {
		mvc.perform(get("/review?id=42")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldPutSingleReviewIntoModel() throws Exception {
		when(reviewRepo.findById(42L)).thenReturn(Optional.of(review));

		mvc.perform(get("/review?id=42")).andExpect(model().attribute("reviews", is(review)));
	}
	
	public void shouldRouteToAllReviewssView() throws Exception {
		mvc.perform(get("/reviews")).andExpect(view().name(is("reviews")));
	}
	
	@Test
	public void shouldBeOkForAllReviews() throws Exception {
		mvc.perform(get("/reviews")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldPutAllReviewsIntoModel() throws Exception {
		Collection<Review> allReviews = Arrays.asList(review, anotherReview);
		when(reviewRepo.findAll()).thenReturn(allReviews);

		mvc.perform(get("/reviews")).andExpect(model().attribute("reviews", is(allReviews)));
	}
	
	
	
	
	
	
	
	
	
}
