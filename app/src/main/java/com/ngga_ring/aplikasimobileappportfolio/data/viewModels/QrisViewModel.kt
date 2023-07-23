package com.ngga_ring.aplikasimobileappportfolio.data.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ngga_ring.aplikasimobileappportfolio.data.models.Resource
import com.ngga_ring.aplikasimobileappportfolio.data.repository.QrisRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QrisViewModel @Inject constructor(private val userRepository: QrisRepository) : ViewModel() {





}