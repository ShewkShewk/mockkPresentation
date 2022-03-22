package statics

import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verifyOrder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.Instant

@ExtendWith(MockKExtension::class)
class StaticMockkTest {

    private val subject = Executor()

    @Test
    fun `execute records start and end metrics accurately`() {
        val startTime = Instant.parse("2022-03-16T23:27:35Z")
        val endTime = startTime.plusSeconds(1)
        val expectedResult = 100

        val actionMock = mockk<() -> Int>()
        mockkStatic(Instant::class) {
            every { actionMock.invoke() } returns expectedResult
            every { Instant.now() }.returnsMany(startTime, endTime)

            val executionResult = subject.execute(actionMock)

            verifyOrder {
                Instant.now()
                actionMock.invoke()
                Instant.now()
            }

            assertEquals(startTime, executionResult.startTime)
            assertEquals(endTime, executionResult.endTime)
            assertEquals(expectedResult, executionResult.result)
        }
    }
}