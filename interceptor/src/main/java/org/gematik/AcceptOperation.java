package org.gematik;

import ca.uhn.fhir.rest.annotation.Operation;
import org.hl7.fhir.r4.model.StringType;

public class AcceptOperation {

    @Operation(name = "$accept", idempotent = true)
    public StringType accept() {
        return new StringType("Hello World");
    }
}