package edu.tuwien.maDMP.Validator.models.rda

class DMPStaff {
    var mbox : String = ""
    var name : String = ""
    var contributor_type : String = ""
    var user_id : TypeIdentifier? = null
    override fun toString(): String {
        return "(mbox='$mbox', name='$name', contributor_type='$contributor_type', user_id=${user_id?.identifier})"
    }


}