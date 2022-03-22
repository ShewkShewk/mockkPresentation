package embedded

class ContactConverter {

    fun extractContactInfo(contact: Contact): String {
        return "${contact.name} " +
                "| ${contact.address.city}, ${contact.address.state} ${contact.address.zip} " +
                "| ${contact.telephone}"
    }
}

interface Contact {
    val name: String
    val telephone: String
    val address: Address
}

interface Address {
    val state: String
    val city: String
    val zip: String
}