package so.notion.interview.data

import io.reactivex.Single
import retrofit2.http.GET

interface CocktailApiService {
    @GET("random.php")
    fun getRandomCocktail(): Single<CocktailResponse>
}