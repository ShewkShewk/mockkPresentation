package companion

class EventReceiver() {
    companion object {
        const val EVENT_RECEIVED = "event.received"
    }

    fun receiveEvent(event: String) {
        MetricRecorder.incrementMetric(EVENT_RECEIVED)
    }
}

class MetricRecorder {
    companion object {
        fun incrementMetric(key: String) {
            // TODO
        }
    }
}
