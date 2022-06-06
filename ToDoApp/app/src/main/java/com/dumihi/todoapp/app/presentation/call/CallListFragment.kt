package com.dumihi.todoapp.app.presentation.call

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dumihi.todoapp.databinding.FragmentCallListBinding
import com.dumihi.todoapp.utils.navigateBack
import com.dumihi.todoapp.utils.observeEvent
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.layout_menu_bar.*
import javax.inject.Inject

class CallListFragment : DaggerFragment() {
    companion object {
        val TAG = CallListFragment::class.java.simpleName

        fun newInstance(): CallListFragment {
            return CallListFragment()
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var _binding: FragmentCallListBinding? = null

    private val binding get() = _binding!!

    private val callListViewModel by viewModels<CallListViewModel> { viewModelFactory }

    private val callAdapter = CallAdapter()

    init {
        lifecycleScope.launchWhenCreated {
            callListViewModel.getCalls()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCallListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tvTitle.text = "Call List"

        with(binding) {
            callList.adapter = callAdapter
            callList.layoutManager =
                LinearLayoutManager(callList.context, RecyclerView.VERTICAL, false)
        }
        btnBack.setOnClickListener {
            navigateBack()
        }

        callListViewModel.calls.observeEvent(viewLifecycleOwner) {
            callAdapter.setData(it)
        }
    }
}