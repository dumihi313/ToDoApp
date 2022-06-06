package com.dumihi.todoapp.app.presentation.buy

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dumihi.todoapp.databinding.FragmentBuyListBinding
import com.dumihi.todoapp.utils.navigateBack
import com.dumihi.todoapp.utils.observeEvent
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.layout_menu_bar.*
import javax.inject.Inject

class BuyFragment : DaggerFragment() {
    companion object {
        val TAG = BuyFragment::class.java.simpleName

        fun newInstance(): BuyFragment {
            return BuyFragment()
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appContext: Context

    private var _binding: FragmentBuyListBinding? = null

    private val binding get() = _binding!!

    private val buyViewModel by viewModels<BuyViewModel> { viewModelFactory }

    private val productAdapter = ProductAdapter()

    init {
        lifecycleScope.launchWhenCreated {
            buyViewModel.getBuys()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBuyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tvTitle.text = "Buy List"

        with(binding) {
            buyList.adapter = productAdapter
            buyList.layoutManager =
                LinearLayoutManager(appContext, RecyclerView.VERTICAL, false)
        }

        btnBack.setOnClickListener {
            navigateBack()
        }

        buyViewModel.buyItems.observeEvent(viewLifecycleOwner) {
            productAdapter.setData(it)
        }
    }
}