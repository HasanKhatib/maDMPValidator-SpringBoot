var maDMPJSON = null;
var maDMP = "";
var isVocabExisted = $('#certificateExistance').val();
var isValidationPassed = true;

var error_list = "";


function validate() {
    isValidationPassed = true;
    document.getElementById('vaildresultID').innerHTML = "";
    document.getElementById('invaildresultID').innerHTML = "";
    document.getElementById('datapathlabelID').innerHTML = "";
    document.getElementById('errdataPathID').innerHTML = "";
    document.getElementById('messagelabelID').innerHTML = "";
    document.getElementById('errmessageID').innerHTML = "";
    document.getElementById('allowedValueslabelID').innerHTML = "";
    document.getElementById('allowedValuesID').innerHTML = "";
    maDMP = document.getElementById("dataID").value;
    maDMPJSON = JSON.parse(maDMP);
    //var maDMPJSON = JSON.parse(maDMP.substring(1, maDMP.length));
    console.log("maDMPJSON object:");
    console.log(maDMPJSON);
    var schemaJSON = data;
    //console.log(schemaJSON);
    var ajv = new Ajv(); // options can be passed, e.g. {allErrors: true}
    var validate = ajv.compile(schemaJSON);
    var valid = validate(maDMPJSON);
    if (!valid) {
        console.log("validate.errors");
        console.log(validate.errors);
        var errors = validate.errors;
        document.getElementById('invaildresultID').innerHTML = "Not validated maDMP";
        document.getElementById('errdataPathID').innerHTML = "Data Path: " + errors[0]['dataPath'];
        document.getElementById('errmessageID').innerHTML = "Message: " + errors[0]['message'];
        if (errors[0]['params']['allowedValues']) {
            document.getElementById('allowedValuesID').innerHTML = "Allowed Values: " + errors[0]['params']['allowedValues'];
        } else {
            document.getElementById('allowedValueslabelID').innerHTML = "";
            document.getElementById('allowedValuesID').innerHTML = "";
        }
        document.getElementById('invaildresultID').classList.add('text-danger');
        document.getElementById('errdataPathID').classList.add('text-danger');
        document.getElementById('errmessageID').classList.add('text-danger');

        console.log("invalid");
    }

    //schema validated
    //validate vocabs files
    else {
        isValidationPassed = true;
        printResult();
        $.ajax({
            method: "POST",
            url: "/api/",
            data: {dmp: maDMP}
        }).done(function (msg) {
            console.log(msg);
        });

    }
}

function printResult() {
    document.getElementById('vaildresultID').innerHTML =
        isValidationPassed
            ? error_list
            : "Validated - the maDMP instance is conform the the schema";
    document.getElementById('vaildresultID').classList.add(
        isValidationPassed
            ? 'text-success'
            : 'text-danger'
    );
    console.log("vocab validation result :" + isValidationPassed);
}

function validURL(str) {
    var pattern = new RegExp('^(https?:\\/\\/)?' + // protocol
        '((([a-z\\d]([a-z\\d-]*[a-z\\d])*)\\.)+[a-z]{2,}|' + // domain name
        '((\\d{1,3}\\.){3}\\d{1,3}))' + // OR ip (v4) address
        '(\\:\\d+)?(\\/[-a-z\\d%_.~+]*)*' + // port and path
        '(\\?[;&a-z\\d%_.~+=-]*)?' + // query string
        '(\\#[-a-z\\d_]*)?$', 'i'); // fragment locator
    return !!pattern.test(str);
}

