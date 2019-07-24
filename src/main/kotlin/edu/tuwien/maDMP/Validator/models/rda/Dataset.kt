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
    override fun toString(): String {
        return "(title='$title', description='$description', type='$type', keyword='$keyword', " +
                "\ndata_quality_assurance='$data_quality_assurance', preservation_statement='$preservation_statement', " +
                "\nissued='$issued', dataset_id=$dataset_id, personal_data='$personal_data', sensitive_data='$sensitive_data', " +
                "\nsecurity_and_privacy=${security_and_privacy?.map { it->it.title }.toString()}, " +
                "\ntechnical_resource=${technical_resource?.map { it->it.description }.toString()}, " +
                "\nmetadata=${metadata.toString()}, \ndistribution=${distribution?.map {  it -> it.toString()}})\n"
    }


}