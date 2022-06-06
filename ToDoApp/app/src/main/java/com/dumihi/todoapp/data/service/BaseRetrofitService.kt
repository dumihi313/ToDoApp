package com.dumihi.todoapp.data.service

import com.dumihi.todoapp.app.ErrorCodes
import com.dumihi.todoapp.app.local.exception.ApiException
import com.dumihi.todoapp.app.utils.TodoFlow
import com.dumihi.todoapp.data.model.TodoResult
import com.dumihi.todoapp.data.model.base.BaseResponse
import com.dumihi.todoapp.utils.networkinterceptor.NoConnectivityException
import kotlinx.coroutines.flow.flow
import retrofit2.Response

abstract class BaseRetrofitService {
    fun <T> execute(request: suspend () -> Response<T>): TodoFlow<T> {
        return flow {
            try {
                val result = request.invoke()
                if (result.isSuccessful) {
                    emit(TodoResult.Success(result.body()))
                } else {
                    emit(TodoResult.Error(ApiException.wrap("Error")))
                }
            } catch (e: Exception) {
                if (e is NoConnectivityException) {
                    emit(
                        TodoResult.Error(
                            ApiException.wrap(
                                code = ErrorCodes.NETWORK_ERROR,
                                e.message
                            )
                        )
                    )
                } else {
                    emit(TodoResult.Error(e.cause ?: Throwable("Unknown Error")))
                }
                emit(TodoResult.Error(e.cause ?: Throwable("Unknown Error")))
            }
        }
    }

    fun <T, R> executeWithParse(
        request: suspend () -> Response<T>,
        parseFunction: (T?) -> R?
    ): TodoFlow<R> {
        return flow {
            try {
                val result = request.invoke()
                if (result.isSuccessful) {
                    emit(TodoResult.Success(parseFunction(result.body())))
                } else {
                    emit(TodoResult.Error(ApiException.wrap("Error")))
                }
            } catch (e: Exception) {
                if (e is NoConnectivityException) {
                    emit(
                        TodoResult.Error(
                            ApiException.wrap(
                                code = ErrorCodes.NETWORK_ERROR,
                                e.message
                            )
                        )
                    )
                } else {
                    emit(TodoResult.Error(e.cause ?: Throwable("Unknown Error")))
                }
            }
        }
    }

    fun <T> execute2(request: suspend () -> BaseResponse<T>): TodoFlow<T> {
        return flow {
            try {
                val result = request.invoke()
                if (result.isSuccess) {
                    emit(TodoResult.Success(result.getData()))
                } else {
                    emit(TodoResult.Error(ApiException.wrap("Error")))
                }
            } catch (e: Exception) {
                if (e is NoConnectivityException) {
                    emit(
                        TodoResult.Error(
                            ApiException.wrap(
                                code = ErrorCodes.NETWORK_ERROR,
                                e.message
                            )
                        )
                    )
                } else {
                    emit(TodoResult.Error(e.cause ?: Throwable("Unknown Error")))
                }
                emit(TodoResult.Error(e.cause ?: Throwable("Unknown Error")))
            }
        }
    }


//
//    fun <T1, T2> executeZip(request1: suspend () -> Response<T1>, request2: suspend () -> Response<T2>): LiveFlow<Pair<T1?, T2?>> {
//        return flow {
//            try{
//                var data1: T1? = null
//                var data2: T2? = null
//                val flow1 = flowOf(request1.invoke())
//                val flow2 = flowOf(request2.invoke())
//                flow1.zip(flow2) { result1, result2 ->
//                    return@zip Pair(result1, result2)
//                }.collect { data ->
//                    if (data.first.isSuccess) {
//                        data1 = data.first.getData()
//                    } else {
//                        emit(LiveResult.Error(ApiException(data.first.getCode(), data.first.getMessage())))
//                    }
//                    if (data.second.isSuccess) {
//                        data2 = data.second.getData()
//                    } else {
//                        emit(LiveResult.Error(ApiException(data.second.getCode(), data.second.getMessage())))
//                    }
//                }
//                emit(LiveResult.Success(Pair(data1, data2)))
//            } catch (e: Exception) {
//                if (e is NoConnectivityException) {
//                    emit(LiveResult.Error(ApiException.wrap(code = ErrorCodes.NETWORK_ERROR, e.message)))
//                } else {
//                    emit(LiveResult.Error(e.cause ?: Throwable("Unknown Error")))
//                }
//            }
//        }
//    }
}
