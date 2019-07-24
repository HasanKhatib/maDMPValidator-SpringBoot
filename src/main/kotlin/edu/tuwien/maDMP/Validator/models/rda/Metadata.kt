package edu.tuwien.maDMP.Validator.models.rda

class Metadata {
    var description : String = ""
    var language :String  =""
    var metadata_id : TypeIdentifier? = null
    override fun toString(): String {
        return "(description='$description', language='$language')"
    }


}