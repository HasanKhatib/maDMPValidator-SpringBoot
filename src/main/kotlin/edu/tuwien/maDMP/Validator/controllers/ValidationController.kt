/***
 * @author: Hasan Alkhatib (HasanKhatib)
 */
package edu.tuwien.maDMP.Validator.controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import edu.tuwien.maDMP.Validator.logic.VocabsChecker
import edu.tuwien.maDMP.Validator.models.maDMP
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "api/")
class ValidationController {
    var vocabsChecker: VocabsChecker = VocabsChecker()
    @PostMapping(value = "/")
    fun validate(@RequestParam(value = "dmp", defaultValue = "empty") dmp: String): String? {
        var errorList: MutableList<String> = mutableListOf<String>()

        println("validation api")
        if (dmp.toLowerCase() == "empty") {
            println("empty")
            return "Halo"
        }
        try {
            println("mapping")
            val mapper = jacksonObjectMapper()
            var maDMP: maDMP = mapper.readValue(dmp)

            // Hosts Geolocation
            var hostsGeoLocationsError = vocabsChecker.checkHostGeoLocationVocabs(maDMP)
            if (hostsGeoLocationsError.isNotEmpty())
                errorList.add(hostsGeoLocationsError)

            // dataset types
            var datasetTypesError = vocabsChecker.checkDatasetType(maDMP)
            if (datasetTypesError.isNotEmpty())
                errorList.add(datasetTypesError)

            // dist host certifications
            var hostsCertificationError = vocabsChecker.checkHostCertification(maDMP)
            if (hostsCertificationError.isNotEmpty())
                errorList.add(hostsCertificationError)

            errorList = errorList.map { "$it <br/>" }.toMutableList()
            return if (errorList.isNotEmpty()) errorList.joinToString("") else ""
        } catch (e: Exception) {
            println("ERROR ${e.message}")
            return "maDMP Validator cannot convert this maDMP into json object!"
        }
    }
}