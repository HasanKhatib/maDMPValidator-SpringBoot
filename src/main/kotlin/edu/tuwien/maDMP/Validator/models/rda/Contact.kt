package edu.tuwien.maDMP.Validator.models.rda

class Contact {
    var mail : String = ""
    var name : String = ""
    var contact_id : TypeIdentifier? = null
    var contact : String = ""
    override fun toString(): String {
        return "(mail='$mail', name='$name', contact_id=${contact_id?.identifier}, contact='$contact')"
    }


}