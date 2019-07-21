package edu.tuwien.maDMP.Validator.controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import edu.tuwien.maDMP.Validator.models.maDMP
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "api/")
class ValidationController {

    @PostMapping(value = "/")
    fun validate(@RequestParam(value = "dmp", defaultValue = "empty") dmp: String): String? {
        println("validation api")
        if(dmp.toLowerCase().equals("empty")) {
            println("empty")
            return "Halo"
        }
        try {
            println("mapping")
            val mapper = jacksonObjectMapper()
            var maDMP: maDMP = mapper.readValue<maDMP>(dmp)
            return maDMP.dmp?.title

        }catch (e : Exception){
            println("ERROR ${e.message}")
            return "maDMP Validator cannot convert $dmp into json object!"
        }
    }
}