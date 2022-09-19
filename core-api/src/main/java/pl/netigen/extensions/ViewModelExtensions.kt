package pl.netigen.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun ViewModel.launchIO(block: suspend CoroutineScope.() -> Unit): Job = viewModelScope.launch(Dispatchers.IO) { block() }

fun ViewModel.launchMain(block: suspend CoroutineScope.() -> Unit): Job = viewModelScope.launch(Dispatchers.Main) { block() }

fun ViewModel.launch(context: CoroutineContext = EmptyCoroutineContext, block: suspend CoroutineScope.() -> Unit): Job =
    viewModelScope.launch(context) { block() }
