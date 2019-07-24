var maDMPJSON = null;
var maDMP = "";
var isVocabExisted = $('#certificateExistance').val();
var isValidationPassed = true;

var error_list = "";


function validate() {
    isValidationPassed = true;
    document.getElementById('validationMessage').innerHTML = "";
    document.getElementById('schemaErrorPath').innerHTML = "";
    document.getElementById('schemaErrorMessage').innerHTML = "";
    document.getElementById('schemaAllowedEnums').innerHTML = "";

    document.getElementById('validationMessage').classList.remove('text-success');
    document.getElementById('validationMessage').classList.remove('text-danger');


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
        document.getElementById('validationMessage').innerHTML = "Not validated maDMP";
        document.getElementById('schemaErrorPath').innerHTML = "Data Path: " + errors[0]['dataPath'];
        document.getElementById('schemaErrorMessage').innerHTML = "Message: " + errors[0]['message'];
        if (errors[0]['params']['allowedValues']) {
            document.getElementById('schemaAllowedEnums').innerHTML = "Allowed Values: " + errors[0]['params']['allowedValues'];
        } else {
            document.getElementById('schemaAllowedEnums').innerHTML = "";
        }
        document.getElementById('validationMessage').classList.add('text-danger');
        document.getElementById('schemaErrorPath').classList.add('text-danger');
        document.getElementById('schemaErrorMessage').classList.add('text-danger');

        console.log("invalid");
    }

    //schema validated
    //validate vocabs files
    else {
        isValidationPassed = true;
        $.ajax({
            method: "POST",
            url: "/api/",
            data: {dmp: maDMP}
        }).done(function (msg) {
            isValidationPassed = (msg === '')
            console.log(msg);
            if(!isValidationPassed)
                error_list = msg;
            printResult();
        });

    }
}

function printResult() {
    document.getElementById('validationMessage').innerHTML =
        isValidationPassed
            ? "Validated - the maDMP instance is conform the the schema and vocabulary files!"
            : error_list;
    document.getElementById('validationMessage').classList.add(
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

