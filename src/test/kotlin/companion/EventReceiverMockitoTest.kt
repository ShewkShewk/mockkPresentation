package companion

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExtendWith(MockitoExtension::class)
class EventReceiverMockitoTest {

    @Test
    fun `receiveEvent should record received metric`() {
        val mockCompanionObj = mock<MetricRecorderMockitoChanges>()
        val subject = EventReceiverMockitoChanges(mockCompanionObj)
        subject.receiveEvent("Sample event")
        verify(mockCompanionObj).incrementMetric(EventReceiverMockitoChanges.EVENT_RECEIVED)
    }
}
