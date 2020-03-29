
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

/**
 * For Actvities, allows declarations like
 * ```
 * val myViewModel = viewModelProvider(myViewModelFactory)
 * ```
 */
inline fun <reified VM: ViewModel> FragmentActivity.ViewModelProvider(
    provider: ViewModelProvider.Factory
) = ViewModelProviders.of(this).get(VM::class.java)

/**
 * For Fragments, allows declarations like
 * ```
 * val myViewModel = viewModelProvider(myViewModelFactory)
 * ```
 */
inline fun <reified VM : ViewModel> Fragment.ViewModelProvider(
        provider: ViewModelProvider.Factory
) = ViewModelProviders.of(this).get(VM::class.java)

/**
 * Like [Fragment.viewModelProvider] for Fragments that want a [ViewModel] scoped to the Activity.
 */
inline fun <reified VM: ViewModel> Fragment.ViewModelActivityProvider(
        provider: ViewModelProvider.Factory
) = ViewModelProviders.of(requireActivity()).get(VM::class.java)

/**
 * Like [Fragment.viewModelProvider] for Fragments that want a [ViewModel] scoped to the parent
 * Fragment.
 */
inline fun <reified VM: ViewModel> Fragment.ViewModelParentProvider(
    provider: ViewModelProvider.Factory
) = ViewModelProviders.of(parentFragment!!).get(VM::class.java)


