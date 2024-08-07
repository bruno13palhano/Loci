package com.bruno13palhano.data.marketplace

import com.bruno13palhano.model.JobData
import com.bruno13palhano.model.ServiceData
import kotlinx.coroutines.flow.Flow

interface Marketplace {
    fun searchServices(search: String): Flow<List<ServiceData>>

    fun searchJobs(search: String): Flow<List<JobData>>

    fun recommendedServices(): Flow<List<ServiceData>>

    fun recommendedJobs(): Flow<List<JobData>>
}