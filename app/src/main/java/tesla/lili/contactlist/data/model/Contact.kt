package tesla.lili.contactlist.data.model

import java.io.Serializable

data class Contact (var id: Int,
                    var name: String,
                    var picture: String,
                    var email: String,
                    var phone: String,
                    var address: String) : Serializable
