package so.notion.interview

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: CocktailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the content view to the activity_main XML layout
        setContentView(R.layout.activity_main)
        val cocktailName: TextView = findViewById(R.id.cocktailName)
        val cocktailImage: ImageView = findViewById(R.id.cocktailImage)
        val cocktailInstructions: TextView = findViewById(R.id.cocktailInstructions)
        val fetchButton: Button = findViewById(R.id.fetchButton)

        // observe live data and update the UI
        viewModel.cocktail.observe(this) {
            response ->
            val drink = response.drinks?.firstOrNull()
            drink.let {
                if (it != null) {
                    cocktailName.text = it.name
                    cocktailInstructions.text = it.instructions
                    Glide.with(this).load(it.drinkThumb).into(cocktailImage)
                }
            }
        }

        // Observe error messages
        viewModel.error.observe(this) { errorMsg ->
            cocktailName.text = "Error: $errorMsg"
        }

        fetchButton.setOnClickListener{
            viewModel.fetchRandomCocktail()
        }

        // or alternatively, you can use Jetpack Compose
//        setContent {
//            Text(text = "Hello world!")
//        }
    }
}
