package com.dumihi.todoapp.app.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.dumihi.todoapp.app.Constants
import com.dumihi.todoapp.app.local.BackPressedListener
import com.dumihi.todoapp.app.presentation.buy.BuyFragment
import com.dumihi.todoapp.app.presentation.call.CallListFragment
import com.dumihi.todoapp.app.presentation.sell.SellFragment
import com.dumihi.todoapp.app.presentation.sell.SellViewModel
import com.dumihi.todoapp.databinding.FragmentMainBinding
import com.dumihi.todoapp.utils.findNavHost
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : DaggerFragment(), BackPressedListener {
    companion object {
        const val TAG = "MainFragment"

        fun newInstance(): MainFragment {
            val args = Bundle()

            val fragment = MainFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var appContext: Context


    private var _binding: FragmentMainBinding? = null

    private val binding get() = _binding!!

    private var lastBackPressedTs = 0L


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toCall.setOnClickListener {
            goToFragment({ CallListFragment.newInstance() }, CallListFragment.TAG)
        }

        binding.toBuy.setOnClickListener {
            goToFragment({ BuyFragment.newInstance() }, BuyFragment.TAG)
        }
        binding.toSell.setOnClickListener {
            goToFragment({ SellFragment.newInstance() }, SellFragment.TAG)
        }
    }

    private fun goToFragment(createFragment: () -> Fragment, tag: String) {
        findNavHost()?.let { host ->
            val fragmentManager = host.navHostFragmentManager()
            fragmentManager.beginTransaction().apply {
                fragmentManager.findFragmentById(host.containerId())
                    ?.let { hide(it) }
                add(host.containerId(), createFragment.invoke())
                addToBackStack(tag)
                commit()
            }
        }
    }

    override fun onBackPressed(): Boolean {
        val currentMillis = System.currentTimeMillis()
        return if (currentMillis - lastBackPressedTs <= Constants.BACK_PRESSED_INTERVAL) {
            false
        } else {
            Toast.makeText(
                appContext,
                "Press back again to quit",
                Toast.LENGTH_SHORT
            )
                .show()
            lastBackPressedTs = currentMillis
            true
        }
    }
}