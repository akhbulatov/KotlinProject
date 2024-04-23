package di

import data.di.DataFactory
import data.repository.RepoRepository

class CommonFactory {

    private val dataFactory by lazy {
        DataFactory()
    }

    val repoRepository: RepoRepository
        get() = dataFactory.repoRepository
}