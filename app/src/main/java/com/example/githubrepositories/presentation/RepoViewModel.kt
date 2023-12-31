package com.example.githubrepositories.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepositories.data.repository.RepoRepositoryImpl
import com.example.githubrepositories.domain.common.Resource
import com.example.githubrepositories.domain.common.Resource.Loading
import com.example.githubrepositories.domain.entity.RepoDetails
import com.example.githubrepositories.domain.usecase.GetRepoDetailsUseCase
import com.example.githubrepositories.domain.usecase.GetRepoListUseCase
import kotlinx.coroutines.launch

class RepoViewModel : ViewModel() {

    private val repoRepository = RepoRepositoryImpl()

    private val getRepoListUseCase = GetRepoListUseCase(repoRepository)
    private val getRepoDetailsUseCase = GetRepoDetailsUseCase(repoRepository)

    private val _repoList = MutableLiveData<Resource<List<RepoDetails>>>()
    val repoList: LiveData<Resource<List<RepoDetails>>>
        get() = _repoList

    private val _repoDetails = MutableLiveData<Resource<RepoDetails>>()
    val repoDetails: LiveData<Resource<RepoDetails>>
        get() = _repoDetails

    suspend fun getRepoList(){
        viewModelScope.launch{
            _repoList.value = Loading()

            _repoList.value = getRepoListUseCase()
        }
    }

    suspend fun getRepoDetails(id: Int){
        viewModelScope.launch{
            _repoDetails.value = Loading()

            _repoDetails.value = getRepoDetailsUseCase(id)
        }
    }
}