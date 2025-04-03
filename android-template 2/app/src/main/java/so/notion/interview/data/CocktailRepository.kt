package so.notion.interview.data

import io.reactivex.Single
import javax.inject.Inject

class CocktailRepository @Inject constructor(private val apiService: CocktailApiService) {
    fun getRandomCocktail(): Single<CocktailResponse> {
        return apiService.getRandomCocktail()
    }

}