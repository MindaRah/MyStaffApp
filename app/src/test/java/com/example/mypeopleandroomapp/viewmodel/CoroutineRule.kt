package com.example.mypeopleandroomapp.viewmodel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

@ExperimentalCoroutinesApi
class CoroutineRule: TestRule {

    val coroutineDispatcherT = UnconfinedTestDispatcher()

    val coroutineScopeT = createTestCoroutineScope(TestCoroutineDispatcher() + TestCoroutineExceptionHandler()+ coroutineDispatcherT)
    override fun apply(base: Statement, description: Description?) = object: Statement() {
        @Throws(Throwable::class)
        override fun evaluate() {
            Dispatchers.setMain(coroutineDispatcherT)
            base.evaluate()
            Dispatchers.resetMain()
            coroutineScopeT.cleanupTestCoroutines()
        }
    }

    fun runBlockingTest(piece: suspend TestCoroutineScope.() -> Unit) =
        coroutineScopeT.runBlockingTest { piece()}
}