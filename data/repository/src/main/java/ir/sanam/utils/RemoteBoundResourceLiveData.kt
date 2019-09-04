package ir.sanam.utils

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.liveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlin.coroutines.coroutineContext


abstract class RemoteBoundResourceLiveData<ResultType, RequestType> {
    val result = liveData {
        emit(build())
    }
    private val supervisorJob = SupervisorJob()

    @SuppressLint("LongLogTag")
    private suspend fun build(): Resource<ResultType> {
        return CoroutineScope(coroutineContext).async(supervisorJob) {
            try {
                baseProcessDatabase()
                val data = fetchFromNetwork()
                saveInDatabase(data)
                return@async Resource.success(data)
            } catch (e: Exception) {
                return@async (Resource.error(null, RemoteErrorManager(e).errorModel()))
            }
        }.await()
    }

    private suspend fun fetchFromNetwork(): ResultType {
        val apiResponse = createCallAsync().await()
        Log.e(NetworkBoundResource::class.java.name, "Data fetched from network")
        return processResponse(apiResponse)
    }


    protected abstract suspend fun baseProcessDatabase()

    @WorkerThread
    protected abstract suspend fun saveInDatabase(data: ResultType)

    @MainThread
    protected abstract fun processResponse(response: RequestType): ResultType

    protected abstract suspend fun createCallAsync(): Deferred<RequestType>
}