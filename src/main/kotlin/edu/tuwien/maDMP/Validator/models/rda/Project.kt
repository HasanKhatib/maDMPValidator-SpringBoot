package edu.tuwien.maDMP.Validator.models.rda

class Project {
    var title : String = ""
    var description : String = ""
    var project_start : String = ""
    var project_end: String = ""
    var funding : List<Funding>? = null
}