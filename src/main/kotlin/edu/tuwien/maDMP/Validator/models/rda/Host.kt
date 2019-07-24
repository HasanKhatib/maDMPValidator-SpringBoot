package edu.tuwien.maDMP.Validator.models.rda

class Host {
    var title : String = ""
    var description: String = ""
    var support_versioning: String = ""
    var backup_type: String = ""
    var backup_frequency: String = ""
    var availability: String = ""
    var certified_with: String = ""
    var geo_location: String = ""
    var pid_system: String = ""
    override fun toString(): String {
        return "(title='$title', description='$description', support_versioning='$support_versioning', backup_type='$backup_type', backup_frequency='$backup_frequency', availability='$availability', certified_with='$certified_with', geo_location='$geo_location', pid_system='$pid_system')"
    }


}