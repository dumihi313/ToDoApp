package com.dumihi.todoapp.app.presentation.sell

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dumihi.todoapp.app.presentation.utils.StatefulViewModel
import com.dumihi.todoapp.data.database.SellDbManager
import com.dumihi.todoapp.data.model.SellItem
import com.dumihi.todoapp.data.model.TodoResult
import com.dumihi.todoapp.data.repository.SellRepository
import com.dumihi.todoapp.utils.TodoEvent
import com.dumihi.todoapp.utils.flowOnIO
import com.dumihi.todoapp.utils.launch
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class SellViewModel @Inject constructor(
    private val sellRepository: SellRepository,
    private val dbManager: SellDbManager,
) : StatefulViewModel() {
    private val _sellItems = MutableLiveData<TodoEvent<List<SellItem>>>()
    val sellItems: LiveData<TodoEvent<List<SellItem>>> = _sellItems

    fun getSells() {
        launch {
            sellRepository.getSells()
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
                            _sellItems.value = TodoEvent(data)
                        }
                        is TodoResult.Error -> setError(it.throwable)
                    }
                }
        }
    }

    fun clearDb() {
        dbManager.clearDatabase()
    }

}