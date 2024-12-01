package org.gematik;

import ca.uhn.fhir.rest.annotation.Operation;
import org.hl7.fhir.r4.model.Parameters;
import org.hl7.fhir.r4.model.StringType;
import org.springframework.stereotype.Component;

@Component
public class AcceptOperation {

    public AcceptOperation() {
        System.out.println("AcceptOperation component initialized");
    }

    @Operation(name = "$accept", idempotent = true)
    public Parameters accept() {
        Parameters retVal = new Parameters();
        retVal.addParameter().setName("result").setValue(new StringType("Hello World"));
        return retVal;
    }
}