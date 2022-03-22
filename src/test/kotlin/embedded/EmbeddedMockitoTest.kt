package embedded

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@ExtendWith(MockitoExtension::class)
class EmbeddedMockitoTest {

    private val subject = ContactConverter()

    @Test
    fun `extractContactInfo extracts a properly formatted string`() {
        val testName = "Javier"
        val testNumber = "555-555-5555"
        val testState = "MI"
        val testCity = "Dearborn"
        val testZip = "48120"
        val mockAddress = mock<Address> {
            on { state } doReturn testState
            on { city } doReturn testCity
            on { zip } doReturn testZip
        }
        val addressBookMock = mock<Contact> {
            on { name } doReturn testName
            on { telephone } doReturn testNumber
            on { address } doReturn mockAddress
        }
        assertEquals("Javier | Dearborn, MI 48120 | 555-555-5555", subject.extractContactInfo(addressBookMock))
    }
}
