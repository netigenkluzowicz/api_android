package com.netigen.sampleapp

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.junit.Test
import kotlin.system.measureTimeMillis

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun coroutines() {
        runBlocking {
            repeat(1_000_000) { // launch a lot of coroutines
                launch {
                    delay(1000L)
                    print(".")
                }
            }
        }
    }

    var count = 0
    private val mutex = Mutex()

    suspend fun massiveRun(action: suspend () -> Unit) {
        val n = 1000000  // number of coroutines to launch
        val k = 5 // times an action is repeated by each coroutine

        val time = measureTimeMillis {
            coroutineScope {
                // scope for coroutines
                repeat(n) {
                    launch {
                        repeat(k) {
                            action()
                            mutex.withLock { count++ }
                        }
                        println("${Thread.currentThread().name}, + $count")
                    }
                }
            }
        }
        print("$count;")
        println("\nCompleted ${n * k} actions in $time ms")
    }
}
