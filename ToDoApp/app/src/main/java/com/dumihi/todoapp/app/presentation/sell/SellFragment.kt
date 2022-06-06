package com.dumihi.todoapp.app.presentation.sell

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
import com.dumihi.todoapp.app.presentation.buy.ProductAdapter
import com.dumihi.todoapp.databinding.FragmentSellListBinding
import com.dumihi.todoapp.utils.navigateBack
import com.dumihi.todoapp.utils.observeEvent
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.layout_menu_bar.*
import javax.inject.Inject

class SellFragment : DaggerFragment() {
    companion object {
        val TAG = SellFragment::class.java.simpleName

        fun newInstance(): SellFragment {
            return SellFragment()
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appContext: Context

    private var _binding: FragmentSellListBinding? = null

    private val binding get() = _binding!!

    private val buyViewModel by viewModels<SellViewModel> { viewModelFactory }

    private val productAdapter = ProductAdapter()

    init {
        lifecycleScope.launchWhenCreated {
            buyViewModel.getSells()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSellListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tvTitle.text = "Sell List"

        with(binding) {
            sellList.adapter = productAdapter
            sellList.layoutManager =
                LinearLayoutManager(appContext, RecyclerView.VERTICAL, false)
        }

        btnBack.setOnClickListener {
            navigateBack()
        }

        buyViewModel.sellItems.observeEvent(viewLifecycleOwner) {
            productAdapter.setData(it)
        }
    }
}