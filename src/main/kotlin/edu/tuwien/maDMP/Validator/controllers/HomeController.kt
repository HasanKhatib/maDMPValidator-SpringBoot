/****
 * author: HasanKhatib
 */
package edu.tuwien.maDMP.Validator.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(value = "/")
class HomeController {

    @RequestMapping(value  = "/")
    fun home(model : Model) : String{
        model["title"] = "maDMP Validator - RDA"
        return "views/home"
    }
}