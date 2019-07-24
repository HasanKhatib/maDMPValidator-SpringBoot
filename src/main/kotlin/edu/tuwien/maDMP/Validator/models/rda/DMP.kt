package edu.tuwien.maDMP.Validator.models.rda

class DMP {
    var title : String = ""
    var description : String = ""

    var dmp_id : TypeIdentifier? = null

    var language : String = ""
    var created : String = ""
    var modified : String = ""
    var ethical_issues_exist : String = ""
    var ethical_issues_report : String = ""
    var ethical_issues_description : String = ""

    var contact : Contact? = null
    var dataset : List<Dataset>? = null
    var dm_staff : List<DMPStaff>? = null
    var cost : List<Cost>? = null
    var project : List<Project>? = null



}