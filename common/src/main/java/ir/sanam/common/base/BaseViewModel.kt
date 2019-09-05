package ir.sanam.common.base

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import ir.sanam.common.base.util.Event
import ir.sanam.navigation.NavigationCommand

 open class BaseViewModel : ViewModel() {


    // FOR ERROR HANDLER
    val snackBarError = MutableLiveData<Event<Any>>()


    // FOR NAVIGATION
    private val _navigation = MutableLiveData<Event<NavigationCommand>>()
    val navigation: LiveData<Event<NavigationCommand>> = _navigation

    /**
     * Convenient method to handle navigation from a [ViewModel]
     */
    fun navigate(directions: NavDirections, bundle: Bundle? = null) {
        _navigation.postValue(Event(NavigationCommand.To(directions, bundle)))
    }

    fun back(){
        _navigation.postValue(Event(NavigationCommand.Back))
    }





}