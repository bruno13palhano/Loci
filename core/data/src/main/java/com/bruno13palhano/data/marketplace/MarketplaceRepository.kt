package com.bruno13palhano.data.marketplace

import com.bruno13palhano.model.Job
import com.bruno13palhano.model.Service
import kotlinx.coroutines.flow.Flow

interface MarketplaceRepository {
    fun searchServices(search: String): Flow<List<Service>>

    fun searchJobs(search: String): Flow<List<Job>>

    fun recommendedServices(): Flow<List<Service>>

    fun recommendedJobs(): Flow<List<Job>>
}