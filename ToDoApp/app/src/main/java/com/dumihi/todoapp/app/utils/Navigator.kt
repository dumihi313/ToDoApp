package com.dumihi.todoapp.app.utils

import androidx.fragment.app.FragmentManager
import com.dumihi.todoapp.app.di.AppScoped
import javax.inject.Inject

@AppScoped
class Navigator @Inject constructor() {
    private fun ensureSingleInstance(host: NavHost, tag: String) {
        val fm = host.navHostFragmentManager()
        fm.findFragmentByTag(tag)?.let {
            fm.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

//    fun navigateToMainAndFinish(host: NavHost?) {
//        host?.let {
//            host.navHostFragmentManager().apply {
//                popBackStack()
//                beginTransaction()
//                    .replace(host.containerId(), MainFragment.newInstance(), MainFragment.TAG)
//                    .commit()
//            }
//        }
//    }
}