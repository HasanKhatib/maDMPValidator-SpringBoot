package edu.tuwien.maDMP.Validator.models.rda

class Distribution {
    var title: String = ""
    var description: String = ""
    var format: String = ""
    var byte_size: String = ""
    var data_access: String = ""
    var access_url: String = ""
    var license: List<License>? = null
    var host: Host? = null

}