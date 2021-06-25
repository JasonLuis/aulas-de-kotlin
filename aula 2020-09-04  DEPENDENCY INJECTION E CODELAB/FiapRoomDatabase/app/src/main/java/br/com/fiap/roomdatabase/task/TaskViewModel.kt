package br.com.fiap.roomdatabase.task

import android.os.SystemClock
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.roomdatabase.database.TaskDao
import br.com.fiap.roomdatabase.service.TaskService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(
    private val service: TaskService,
    private val taskDao: TaskDao
): ViewModel() {

    val taskLiveData = MutableLiveData<List<Task>>()
    val newTaskLiveData = MutableLiveData<Task>()
    var id: Int = 0

    val error = MutableLiveData<Boolean>()

    init {
        fetchTasks()
    }

    private fun fetchTasks(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                //Busca do banco local
                val cacheData = taskDao.getTasks()
                taskLiveData.postValue(cacheData)

                //busca dp servidor
                SystemClock.sleep(2000)
                val result: List<Task> = service.getTasks()
                taskLiveData.postValue(result)

                //Atualizar banco local
                taskDao.deleteAll()
                taskDao.insertTask(*result.toTypedArray())

            } catch (t: Throwable){
                error.postValue(true)
            }
        }
    }

    fun newTask(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            service.newTask(task)
            newTaskLiveData.postValue(task)
        }
    }

}