package com.example.myapplication.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _input1 = MutableStateFlow("")
    val input1: StateFlow<String> = _input1.asStateFlow()
    private val _input2 = MutableStateFlow("")
    val input2: StateFlow<String> = _input2.asStateFlow()
    private val _input3 = MutableStateFlow("")
    val input3: StateFlow<String> = _input3.asStateFlow()
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun onInput1Change(value: String) { _input1.value = value }
    fun onInput2Change(value: String) { _input2.value = value }
    fun onInput3Change(value: String) { _input3.value = value }

    fun validateAndSubmit() {
        val v1 = _input1.value.trim()
        val v2 = _input2.value.trim()
        val v3 = _input3.value.trim()
        if (v1.isEmpty() || v2.isEmpty() || v3.isEmpty()) {
            _errorMessage.value = "Por favor, completa todos los campos."
        }
        if(v1.length>8){
            _errorMessage.value = "No debe tener mas de 8 caracteres el celular."
        }
        if(v2.length>10){
            _errorMessage.value = "No debe tener mas de 10 caracteres el carnet"
        }
        if(v3.length>2){
            _errorMessage.value = "No debe tener mas de 2 caracteres el complemento"
        }

    }
}