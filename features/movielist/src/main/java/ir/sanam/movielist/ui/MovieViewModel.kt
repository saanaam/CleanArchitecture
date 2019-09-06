package ir.sanam.movielist.ui

import androidx.lifecycle.viewModelScope
import ir.sanam.AppDispatchers
import ir.sanam.common.base.BaseViewModel


import ir.sanam.movielist.domain.MovieUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel (private val movieUseCase: MovieUseCase , private val dispatchers: AppDispatchers) : BaseViewModel() {

    // FOR DATA
//    private val _tripList = MediatorLiveData<Resource<MovieResponse>>()
//    val tripList: LiveData<Resource<MovieResponse>> get() = _tripList
//    private var tipListSource: LiveData<Resource<MovieResponse>> = MutableLiveData()


    init {
//        getMovieOList()
        getMovie()
    }

//    fun getMovieOList() =
//        viewModelScope.launch(dispatchers.main) {
//            // We make sure there is only one source of livedata (allowing us properly refresh)
//            _tripList.removeSource(tipListSource)
//            withContext(dispatchers.io) {
//                tipListSource = movieUseCase.invoke()
//            }
//            _tripList.addSource(tipListSource) {
//
//                when (it.status) {
//                    Resource.Status.SUCCESS -> _tripList.value = it
//                    Resource.Status.ERROR -> {
//                        if (it.errorModel?.response?.message?.isEmpty() == true) {
//                            snackBarError.postValue(Event(("unexpected")))
//
//                        } else if (it.errorModel?.type == ErrorModel.Type.NETWORK) {
//                            snackBarError.postValue(Event("no connection"))
//                        }
////                        }
//                    }
//                }
//            }
//        }

    fun getMovie(){
        viewModelScope.launch(dispatchers.main) {
            withContext(dispatchers.io){
                val tt = movieUseCase.invoke()
            }
        }
    }
}