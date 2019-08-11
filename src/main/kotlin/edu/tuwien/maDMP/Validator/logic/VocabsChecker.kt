/***
 * @author: Hasan Alkhatib (HasanKhatib)
 */
package edu.tuwien.maDMP.Validator.logic

import edu.tuwien.maDMP.Validator.models.maDMP
import org.springframework.core.io.ClassPathResource
import java.nio.file.Files


class VocabsChecker {
    fun checkDatasetType(maDMP: maDMP): String {
        var datasetTypes: List<String?>? = maDMP.dmp?.dataset?.map { it.type.toLowerCase() }
        var outOfVocabs = ""

        if (!ClassPathResource("vocabs/dmp-dataset-type.txt").exists()) return ""

        val resource = ClassPathResource("vocabs/dmp-dataset-type.txt").file
        val locations = String(Files.readAllBytes(resource.toPath()))
        val fileVocabs = locations.lines().map { it.toLowerCase().trim() }

        datasetTypes?.forEach {
            outOfVocabs += if (fileVocabs.contains(it?.trim())) "" else {
                if (outOfVocabs.isNotEmpty()) ", $it" else "Dataset types vocab doesn't has the next vocabs: $it"
            }
        }
        return if (outOfVocabs.isNotEmpty()) outOfVocabs else ""
    }

    fun checkHostPID(maDMP: maDMP): String {
        var maDMPPid: List<String?>? = maDMP.dmp?.dataset
                ?.map {
                    it.distribution?.map { dist -> dist.host?.pid_system}?.joinToString(",")
                }?.first()?.split(",")?.toList()
        var outOfVocabs = ""

        if (!ClassPathResource("vocabs/dmp-dataset-distribution-host-pid.txt").exists()) return ""

        val resource = ClassPathResource("vocabs/dmp-dataset-distribution-host-pid.txt").file
        val fileVocabs = String(Files.readAllBytes(resource.toPath())).lines().map { it.trim() }

        maDMPPid?.forEach {
            outOfVocabs += if (fileVocabs.contains(it?.trim())) "" else {
                if (outOfVocabs.isNotEmpty()) ", $it" else "Hosts PID System vocab doesn't has the next vocabs: $it"
            }
        }
        return if (outOfVocabs.isNotEmpty()) outOfVocabs else ""
    }

    fun checkHostGeoLocationVocabs(maDMP: maDMP): String {
        var geoLocations: List<String?>? = maDMP.dmp?.dataset
                ?.map {
                    it.distribution?.map { dist -> dist.host?.geo_location }?.joinToString()
                }?.first()?.split(",")?.toList()
        var outOfVocabsLocation = ""

        if (!ClassPathResource("vocabs/dmp-dataset-distribution-host-geoLocation.txt").exists()) return ""

        val resource = ClassPathResource("vocabs/dmp-dataset-distribution-host-geoLocation.txt").file
        val locations = String(Files.readAllBytes(resource.toPath()))
        var fileVocabs = locations.lines().map { it.trim() }

        geoLocations?.forEach {
            outOfVocabsLocation += if (fileVocabs.contains(it?.trim())) "" else {
                if (outOfVocabsLocation.isNotEmpty()) ", $it" else "Hosts Geolocation vocab doesn't has the next vocabs: $it"
            }
        }
        return if (outOfVocabsLocation.isNotEmpty()) outOfVocabsLocation else ""
    }

    fun checkHostCertification(maDMP: maDMP): String {
        var maDMPCertifications: List<String?>? = maDMP.dmp?.dataset
                ?.map {
                    it.distribution?.map { dist -> dist.host?.certified_with }?.joinToString(",")
                }?.first()?.split(",")?.toList()
        var outOfVocabs = ""

        if (!ClassPathResource("vocabs/dmp-dataset-distribution-host-certifications.txt").exists()) return ""

        val resource = ClassPathResource("vocabs/dmp-dataset-distribution-host-certifications.txt").file
        var vocabsCertifications = String(Files.readAllBytes(resource.toPath())).lines().map { it.trim() }

        maDMPCertifications?.forEach {
            outOfVocabs += if (vocabsCertifications.contains(it?.trim())) "" else {
                if (outOfVocabs.isNotEmpty()) ", $it" else "Hosts Certifications vocab doesn't has the next vocabs: $it"
            }
        }
        return if (outOfVocabs.isNotEmpty()) outOfVocabs else ""
    }
}