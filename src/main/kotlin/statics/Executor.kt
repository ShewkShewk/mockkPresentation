package statics

import java.time.Instant

class Executor {

    fun execute(action: () -> Int): ExecutionResult {
        val startTime = Instant.now()
        val result = action.invoke()
        val endTime = Instant.now()
        return ExecutionResult(startTime, endTime, result)
        // return ExecutionResult(Instant.now(), Instant.now(), action.invoke())
    }
}

data class ExecutionResult(val startTime: Instant?, val endTime: Instant?, val result: Int?)
