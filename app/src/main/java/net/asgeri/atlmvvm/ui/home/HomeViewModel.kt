package net.asgeri.atlmvvm.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.asgeri.atlmvvm.api.ApiUtils
import net.asgeri.atlmvvm.model.Todo
import net.asgeri.atlmvvm.model.TodoResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val api = ApiUtils.getApi()

    // val todos = MutableLiveData<List<Todo>>()

    private val _todo = MutableLiveData<List<Todo>>()
    val todo: LiveData<List<Todo>> = _todo

    val isLoading = MutableLiveData<Boolean>()

    init {
        getTodo()
    }


    private fun getTodo() {
        isLoading.value = true
        api.getTodoApi().enqueue(object : Callback<TodoResponseModel> {
            override fun onResponse(
                call: Call<TodoResponseModel>,
                response: Response<TodoResponseModel>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { model ->
                        model.todos?.let {
                            _todo.value = it
                            isLoading.value = false
                        }
                    }
                }
            }

            override fun onFailure(call: Call<TodoResponseModel>, t: Throwable) {

            }

        })
    }

}