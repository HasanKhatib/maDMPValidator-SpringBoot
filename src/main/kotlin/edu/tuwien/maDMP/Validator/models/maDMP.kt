/***
 * @author: Hasan Alkhatib (HasanKhatib)
 */
package edu.tuwien.maDMP.Validator.models

import edu.tuwien.maDMP.Validator.models.rda.DMP

class maDMP {
    var dmp : DMP? = null


    override fun toString(): String {
        return "maDMP(${dmp?.title})" +
                "\n1- Title: ${dmp?.description}" +
                "\n2- Language: ${dmp?.language}" +
                "\n3- Created: ${dmp?.created}" +
                "\n4- Modified: ${dmp?.modified}" +
                "\n5- Contact: ${dmp?.contact?.contact}" +
                "\n6- Staff: ${dmp?.dm_staff?.map{it-> it.toString()}.toString()}" +
                "\n7= Datasets: ${dmp?.dataset?.map{it-> it.toString()}.toString()}" +
                ""
    }


}