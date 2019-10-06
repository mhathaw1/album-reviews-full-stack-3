package reviews.albumreviewsfullstack3;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CategoryPopulator implements CommandLineRunner{

	@Resource
	private CategoryRepository categoryRepo;
	
	@Resource 
	private ReviewRepository reviewRepo;
	
	@Override
	public void run(String... args) throws Exception {
		Review okComputer = new Review("Radiohead: OK Computer", "Released: June 16, 1997", "/images/okcomputer.jpg");
		okComputer = reviewRepo.save(okComputer);
		Review loveless = new Review("My Bloody Valentine: Loveless", "Released: November 4, 1991", "/images/loveless2.jpeg");
		loveless = reviewRepo.save(loveless);
		Review daydreamNation = new Review("Sonic Youth: Daydream Nation", "Released: October 18, 1988", "/images/daydream.jpg");
		daydreamNation = reviewRepo.save(daydreamNation);
		Review remainInLight = new Review("Talking Heads: Remain In Light", "Released: October 8, 1980", "/images/remaininlight.jpg");
		remainInLight = reviewRepo.save(remainInLight);
		
		Category eightiesAlbums = new Category("80s Albums", remainInLight, daydreamNation);
		eightiesAlbums = categoryRepo.save(eightiesAlbums);
		
		Category ninetiesAlbums = new Category("90s Albums", okComputer, loveless);
		ninetiesAlbums = categoryRepo.save(ninetiesAlbums);
	}
}
