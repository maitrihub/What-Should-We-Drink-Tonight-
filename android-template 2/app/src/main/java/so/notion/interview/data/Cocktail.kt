package so.notion.interview.data

import com.google.gson.annotations.SerializedName

// extract the API response so we can get the data
data class Cocktail(
    @SerializedName("idDrink") val id: Int,
    @SerializedName("strDrink") val name: String,
    @SerializedName("strCategory") val category: String,
    @SerializedName("strAlcoholic") val isAlcoholic: String,
    @SerializedName("strGlass") val glass: String,
    @SerializedName("strInstructions") val instructions: String,
    @SerializedName("strDrinkThumb") val drinkThumb: String?
)
