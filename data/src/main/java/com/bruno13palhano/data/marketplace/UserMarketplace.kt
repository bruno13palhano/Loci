package com.bruno13palhano.data.marketplace

import com.bruno13palhano.model.JobData
import com.bruno13palhano.model.ServiceData
import kotlinx.coroutines.flow.Flow

internal class UserMarketplace : Marketplace {
    override fun searchServices(search: String): Flow<List<ServiceData>> {
        TODO("Not yet implemented")
    }

    override fun searchJobs(search: String): Flow<List<JobData>> {
        TODO("Not yet implemented")
    }

    override fun recommendedServices(): Flow<List<ServiceData>> {
        TODO("Not yet implemented")
    }

    override fun recommendedJobs(): Flow<List<JobData>> {
        TODO("Not yet implemented")
    }
}