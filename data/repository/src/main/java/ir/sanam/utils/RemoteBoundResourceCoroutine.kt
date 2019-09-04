package ir.sanam.utils

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlin.coroutines.coroutineContext

abstract class RemoteBoundResourceCoroutine<ResultType, RequestType> {

    private val supervisorJob = SupervisorJob()

    suspend fun build(): Deferred<Resource<ResultType>> {
        return CoroutineScope(coroutineContext).async (supervisorJob) {
            try {
                baseProcessDatabase()
                val data = fetchFromNetwork()
                saveInDatabase(data)
                return@async Resource.success(data)
            } catch (e: Exception) {
                return@async (Resource.error(null, RemoteErrorManager(e).errorModel()))
            }
        }
    }



    private suspend fun fetchFromNetwork(): ResultType {
        val apiResponse = createCallAsync().await()
        Log.e(NetworkBoundResource::class.java.name, "Data fetched from network")
        return processResponse(apiResponse)
    }

    @WorkerThread
    protected abstract suspend fun baseProcessDatabase()

    @WorkerThread
    protected abstract suspend fun saveInDatabase(data: ResultType)

    @MainThread
    protected abstract fun processResponse(response: RequestType): ResultType


    @MainThread
    protected abstract suspend fun createCallAsync(): Deferred<RequestType>
}