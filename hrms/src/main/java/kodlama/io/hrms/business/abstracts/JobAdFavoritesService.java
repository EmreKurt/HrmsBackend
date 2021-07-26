package kodlama.io.hrms.business.abstracts;

import java.util.List;

import kodlama.io.hrms.core.result.DataResult;
import kodlama.io.hrms.core.result.Result;
import kodlama.io.hrms.entities.concretes.JobAdFavorites;

public interface JobAdFavoritesService {
	public DataResult<List<JobAdFavorites>> getByJobSeekerId(int seekerId);
	public Result addFavorite(int seekerId, int jobAdId);
    public Result removeFavorite(int favoriteId);
}
