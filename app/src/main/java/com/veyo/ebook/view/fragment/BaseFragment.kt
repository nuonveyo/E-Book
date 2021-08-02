package com.veyo.ebook.view.fragment

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

/**
 * Created by Veyo Nuon on 9/17/2019.
 */

abstract class BaseFragment : Fragment() {
    open fun setFragment(@IdRes id: Int, fragment: Fragment, tag: String) {
        if (!activity?.isFinishing!! && !isFragmentAlreadyReplaced(tag)) {
            childFragmentManager
                .beginTransaction()
                .replace(id, fragment, tag)
                .commit()
        }
    }

    open fun isFragmentAlreadyReplaced(tag: String): Boolean {
        val fragment = childFragmentManager.findFragmentByTag(tag)
        return fragment != null && fragment.isVisible
    }

    open fun showFragment(showFragment: Fragment?, hideFragment: Fragment?) {
        if (hideFragment == null || showFragment == null) {
            return
        } else if (hideFragment === showFragment) {
            return
        }
        childFragmentManager
            .beginTransaction()
            .hide(hideFragment)
            .show(showFragment)
            .commit()
        //make sure fragment know fragment is paused, or resumed
        hideFragment.onPause()
        showFragment.onResume()
    }
}