package edu.tuwien.maDMP.Validator.models.rda

class Distribution {
    var title: String = ""
    var description: String = ""
    var format: String = ""
    var byte_size: String = ""
    var data_access: String = ""
    var access_url: String = ""
    var download_url: String = ""
    var available_till: String = ""
    var license: List<License>? = null
    var host: Host? = null
    override fun toString(): String {
        return "(title='$title', description='$description', format='$format', byte_size='$byte_size', data_access='$data_access', access_url='$access_url', license=$license, host=${host.toString()})\n"
    }


}