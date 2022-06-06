package com.dumihi.todoapp.app.presentation.call

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dumihi.todoapp.app.presentation.utils.StatefulViewModel
import com.dumihi.todoapp.data.model.Call
import com.dumihi.todoapp.data.model.TodoResult
import com.dumihi.todoapp.data.repository.CallRepository
import com.dumihi.todoapp.utils.TodoEvent
import com.dumihi.todoapp.utils.flowOnIO
import com.dumihi.todoapp.utils.launch
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class CallListViewModel @Inject constructor(
    private val callRepository: CallRepository
) : StatefulViewModel() {
    private val _calls = MutableLiveData<TodoEvent<List<Call>>>()
    val calls: LiveData<TodoEvent<List<Call>>> = _calls

    //
    fun getCalls() {
        launch {
            callRepository.getCalls()
                .flowOnIO()
                .onStart { setLoading(true) }
                .catch {
                    setLoading(false)
                    setError(it)
                }.onCompletion { setLoading(false) }
                .collect {
                    when (it) {
                        is TodoResult.Success -> {
                            val data = it.data ?: return@collect
                            _calls.value = TodoEvent(data)
                        }
                        is TodoResult.Error -> setError(it.throwable)
                    }
                }
        }
    }

}