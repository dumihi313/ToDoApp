package com.dumihi.todoapp.app.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import com.dumihi.todoapp.R
import com.dumihi.todoapp.app.local.BackPressedListener
import com.dumihi.todoapp.app.utils.NavHost
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity(), NavHost {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.mainNavContainer, MainFragment.newInstance(), MainFragment.TAG)
            .commit()
    }

    override fun containerId(): Int {
        return R.id.mainNavContainer
    }

    override fun navHostFragmentManager(): FragmentManager {
        return supportFragmentManager
    }

    override fun containerView(): View {
        return findViewById(containerId())
    }

    override fun onBackPressed() {
        val fragments = supportFragmentManager.fragments
        for (f in fragments) {
            if (f.isVisible && f is BackPressedListener && f.onBackPressed()) {
                return
            }
        }
        super.onBackPressed()
    }
}