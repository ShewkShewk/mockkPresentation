package companion

class EventReceiverMockitoChanges(val metricRecorderCompanion: MetricRecorderMockitoChanges = MetricRecorderMockitoChangesImpl.Companion) {

    companion object {
        const val EVENT_RECEIVED = "event.received"
    }

    fun receiveEvent(event: String) {
        metricRecorderCompanion.incrementMetric(EVENT_RECEIVED)
    }
}

class MetricRecorderMockitoChangesImpl {
    companion object: MetricRecorderMockitoChanges {
        override fun incrementMetric(key: String) {
            // TODO
        }
    }
}

interface MetricRecorderMockitoChanges {
    fun incrementMetric(key: String)
}