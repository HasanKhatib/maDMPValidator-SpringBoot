var maDMPJSON = null;
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
    var maDMP = document.getElementById("dataID").value;
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
        }
        else {
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
        if (maDMPJSON != null && maDMPJSON.dmp != null && maDMPJSON.dmp != undefined) {
            var isValidLink = true;
            var isWorkingLink = true;
            //Is link working?
            //maDMPJSON.dmp.dataset[0].distribution[0].license[0].license_ref
            maDMPJSON.dmp.dataset.forEach(function (ds_element) {
                //dataset distribtuion loop
                ds_element.distribution.forEach(function (dist_element) {
                    dist_element.license.forEach(function (li_element) {
                        try {
                            $.ajax({
                                type: "GET",
                                url: li_element.license_ref
                            }).done(function (result) {
                                console.log(li_element.license_ref + " is a working link")
                            }).fail(function () {
                                console.log(li_element.license_ref + "Sorry URL is not accessable");
                                error_list += li_element.license_ref + " is not a working URL!\n";
                                //document.getElementById('invaildresultID').innerHTML = li_element.license_ref + " is not a working URL!";
                                //document.getElementById('invaildresultID').classList.add('text-danger');
                                //isValidationPassed = false;
                                //return;
                            });
                        } catch (e) {
                            console.log("ERROR: " + e);
                        }
                    });//end license foreach 

                    //checking host certificates
                    if (dist_element.host != null && dist_element.host != undefined) {
                        var file = document.getElementById("distCertificates").files[0];
                        if(file == undefined || file == null)
                            return;
                        checkVocabsVsFile(file, dist_element.host.certified_with.split(','));
                        console.log('waiting!');
                        setTimeout(function () {
                            checkCertificates(dist_element.host);
                        }, 500);

                        // console.log("checking vocabs result is " + isVocabExisted);

                    }

                }); //end of distribution loop
            }); //end of datasets loop

        }
        setTimeout(function () {
            printResult();
        }, 500);
    }
}

function checkCertificates(host) {
    isVocabExisted = $('#certificateExistance').val();
    if (isVocabExisted == "false") {
        console.log(host.certified_with + " is not supported!");
        document.getElementById('invaildresultID').innerHTML = host.certified_with + " is not a supported certificate according to the uploaded vocab!";
        document.getElementById('invaildresultID').classList.add('text-danger');
        isValidationPassed = false;
        $('#certificateExistance').val(false);

        return;
    }
}

function printResult() {
    if (!isValidationPassed)
        return;
    document.getElementById('vaildresultID').innerHTML = "Validated - the maDMP instance is conform the the schema";
    document.getElementById('vaildresultID').classList.add('text-success');
    console.log("valid");
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

function checkVocabsVsFile(vocabsFile, maDMPVocabs) {
    if (vocabsFile) {
        const reader = new FileReader();

        reader.onload = (event) => {
            const file = event.target.result;
            const allLines = file.split(/\r\n|\n/);
            // Reading line by line
            $('#certificateExistance').val(maDMPVocabs.every(function (i) {
                return allLines.includes(i);
            }));

        };

        reader.onerror = (event) => {
            alert(event.target.error.name);
        };

        reader.readAsText(vocabsFile);
    }
}

function changeInputText(inputID, labelID) {
    $('#' + labelID).text($('#' + inputID).val().split('\\').pop());
}