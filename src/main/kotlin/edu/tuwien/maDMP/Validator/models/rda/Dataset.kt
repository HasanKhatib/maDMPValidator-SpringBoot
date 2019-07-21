package edu.tuwien.maDMP.Validator.models.rda

class Dataset {
    var title : String = ""
    var description : String = ""
    var type : String = ""
    var keyword : String = ""
    var data_quality_assurance: String = ""
    var preservation_statement: String = ""
    var issued: String = ""
    var dataset_id : TypeIdentifier? = null
    var personal_data : String = ""
    var sensitive_data : String = ""
    var security_and_privacy: List<SecurityAndPrivacy>? = null
    var technical_resource: List<TechnicalResource>? = null
    var metadata : List<Metadata>? = null
    var distribution : List<Distribution>? = null
}