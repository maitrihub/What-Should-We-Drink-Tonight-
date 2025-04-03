package so.notion.interview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import so.notion.interview.data.CocktailRepository
import so.notion.interview.data.CocktailResponse
import javax.inject.Inject

@HiltViewModel
class CocktailViewModel @Inject constructor(
    private val cocktailRepository: CocktailRepository
) : ViewModel() {

    private val _cocktail = MutableLiveData<CocktailResponse>()
    val cocktail: LiveData<CocktailResponse> get() = _cocktail

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val compositeDisposable = CompositeDisposable()

    fun fetchRandomCocktail() {
        compositeDisposable.add(
            cocktailRepository.getRandomCocktail()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    Log.e("CocktailViewModel","not error")
                    _cocktail.value = response
                }, { err ->
                    Log.e("CocktailViewModel","error $err")
                    _error.value = err.message
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}