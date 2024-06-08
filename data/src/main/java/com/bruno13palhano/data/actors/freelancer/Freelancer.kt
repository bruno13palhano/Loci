package com.bruno13palhano.data.actors

import com.bruno13palhano.data.network.LocalService
import com.bruno13palhano.data.network.RemoteService
import com.bruno13palhano.model.ServiceData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class Freelancer(
    private val remoteService: RemoteService,
    private val localService: LocalService
) {

    suspend fun creteService(
        serviceData: ServiceData,
        remoteService: RemoteService,
        localService: LocalService,
        onError: (error: Int) -> Unit,
        onSuccess: (id: Long) -> Unit,
    ) {
        remoteService.createService(
            serviceData = serviceData,
            onError = onError,
        ) {
            CoroutineScope(Dispatchers.IO).launch {
                localService.createService(
                    serviceData = serviceData,
                    onError = onError,
                    onSuccess = onSuccess
                )
            }
        }
    }

    suspend fun deleteService(
        id: Long,
        remoteService: RemoteService,
        localService: LocalService,
        onError: (error: Int) -> Unit,
        onSuccess: () -> Unit,
    ) {
        remoteService.deleteService(
            id = id,
            onError = onError,
        ) {
            CoroutineScope(Dispatchers.IO).launch {
                localService.deleteService(
                    id = id,
                    onError = onError,
                    onSuccess = onSuccess
                )
            }
        }
    }

    suspend fun updateService(
        serviceData: ServiceData,
        remoteService: RemoteService,
        localService: LocalService,
        onError: (error: Int) -> Unit,
        onSuccess: () -> Unit,
    ) {
        remoteService.updateService(
            serviceData = serviceData,
            onError = onError,
        ) {
            CoroutineScope(Dispatchers.IO).launch {
                localService.updateService(
                    serviceData = serviceData,
                    onError = onError,
                    onSuccess = onSuccess
                )
            }
        }
    }
}