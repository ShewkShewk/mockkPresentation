package companion

import io.mockk.junit5.MockKExtension
import io.mockk.mockkObject
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class EventReceiverMockkTest {

    private val subject = EventReceiver()

    @Test
    fun `receiveEvent should record received metric`() {
        mockkObject(MetricRecorder) {
            subject.receiveEvent("Sample event")
            verify { MetricRecorder.incrementMetric(EventReceiver.EVENT_RECEIVED) }
        }
    }
}
