package embedded

import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class EmbeddedMockkTest {

    private val subject = ContactConverter()

    @Test
    fun `extractContactInfo extracts a properly formatted string`() {
        val testName = "Javier"
        val testNumber = "555-555-5555"
        val testState = "MI"
        val testCity = "Dearborn"
        val testZip = "48120"
        val addressBookMock = mockk<Contact> {
            every { name } returns testName
            every { telephone } returns testNumber
            every { address.state } returns testState
            every { address.city } returns testCity
            every { address.zip } returns testZip
        }
        assertEquals("Javier | Dearborn, MI 48120 | 555-555-5555", subject.extractContactInfo(addressBookMock))
    }
}
