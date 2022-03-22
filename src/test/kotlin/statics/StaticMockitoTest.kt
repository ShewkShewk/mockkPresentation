package statics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.mockStatic
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.Instant

@ExtendWith(MockitoExtension::class)
class StaticMockitoTest {

    private val subject = Executor()

    @Test
    fun `execute records start and end metrics accurately`() {
        val startTime = Instant.parse("2022-03-16T23:27:35Z")
        val endTime = startTime.plusSeconds(1)
        val expectedResult = 100

        val actionMock = mock<() -> Int>()
        val instantMock = mockStatic(Instant::class.java)

        instantMock.`when`<Instant> { Instant.now() }.thenReturn(startTime, endTime)
        whenever(actionMock.invoke()).thenReturn(expectedResult)

        val executionResult = subject.execute(actionMock)

        // Cannot verify order of static calls in mockito : (
        // https://github.com/mockito/mockito/issues/2548

        verify(actionMock).invoke()

        assertEquals(startTime, executionResult.startTime)
        assertEquals(endTime, executionResult.endTime)
        assertEquals(expectedResult, executionResult.result)
        instantMock.close()
    }
}