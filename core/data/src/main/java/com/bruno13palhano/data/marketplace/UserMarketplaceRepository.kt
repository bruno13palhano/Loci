package com.bruno13palhano.data.marketplace

import com.bruno13palhano.model.Job
import com.bruno13palhano.model.Service
import kotlinx.coroutines.flow.Flow

internal class UserMarketplaceRepository : MarketplaceRepository {
    override fun searchServices(search: String): Flow<List<Service>> {
        TODO("Not yet implemented")
    }

    override fun searchJobs(search: String): Flow<List<Job>> {
        TODO("Not yet implemented")
    }

    override fun recommendedServices(): Flow<List<Service>> {
        TODO("Not yet implemented")
    }

    override fun recommendedJobs(): Flow<List<Job>> {
        TODO("Not yet implemented")
    }
}