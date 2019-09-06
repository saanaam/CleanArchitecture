package ir.sanam.common.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.google.android.material.snackbar.Snackbar
import ir.sanam.common.base.util.setupSnackbar
import androidx.navigation.fragment.findNavController
import ir.sanam.navigation.NavigationCommand


abstract class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListener()
    }

    @SuppressLint("CheckResult")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeNavigation(getViewModel())
        observeViewModel()
        setupSnackbar(this, getViewModel().snackBarError, Snackbar.LENGTH_LONG)
    }


    abstract fun getViewModel(): BaseViewModel

    /**
     * Observe a [NavigationCommand] [Event] [LiveData].
     * When this [LiveData] is updated, [Fragment] will navigate to its destination
     */
    private fun observeNavigation(viewModel: BaseViewModel) {
        viewModel.navigation.observe(viewLifecycleOwner, Observer {
            it?.getContentIfNotHandled()?.let { command ->
                when (command) {
                    is NavigationCommand.To -> {
                        if (command.bundle == null)
                            findNavController().navigate(command.directions, getExtras())
                        else
                            findNavController().navigate(command.directions.actionId, command.bundle, null, getExtras())
                    }
                    is NavigationCommand.Back -> findNavController().navigateUp()
                }
            }
        })
    }


    /**
     * [FragmentNavigatorExtras] mainly used to enable Shared Element transition
     */
    open fun getExtras(): FragmentNavigator.Extras = FragmentNavigatorExtras()




    protected open fun setUpListener() {}
    protected open fun observeViewModel() {}


    protected fun hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    protected fun showKeyboard() {
        view?.let { activity?.showKeyboard(it) }
    }

    private fun Context.showKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInputFromWindow(view.windowToken, 1, 0)
    }


}




