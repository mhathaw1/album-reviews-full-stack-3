package reviews.albumreviewsfullstack3;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Category {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToMany
	private Collection <Review> reviews;

	private String genre;
	
	public Collection <Review> getReviews() {
		return reviews;
	}

	public Category() {
	}
	
	public Category(String genre, Review...reviews) {
		this.genre = genre;
		this.reviews = new HashSet<>(Arrays.asList(reviews));
	}

	public long getId() {
		return id;
	}

	public Object getGenre() {
		return genre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
