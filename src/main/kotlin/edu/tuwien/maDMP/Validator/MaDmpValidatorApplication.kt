/***
 * @author: Hasan Alkhatib (HasanKhatib)
 */
package edu.tuwien.maDMP.Validator

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MaDmpValidatorApplication

fun main(args: Array<String>) {
	runApplication<MaDmpValidatorApplication>(*args){
        setBannerMode(Banner.Mode.OFF)
    }
}
