package data.di

import data.network.di.NetworkFactory
import data.repository.RepoRepository

class DataFactory {

    private val networkFactory by lazy {
        NetworkFactory()
    }

    val repoRepository: RepoRepository by lazy {
        RepoRepository(
            gitHubApi = networkFactory.gitHubApi,
        )
    }
}