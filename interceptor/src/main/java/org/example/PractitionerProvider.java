package org.example;

import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.annotation.Operation;
import ca.uhn.fhir.rest.annotation.OperationParam;
import org.hl7.fhir.r4.model.Parameters;
import org.hl7.fhir.r4.model.Practitioner;
import org.hl7.fhir.r4.model.StringType;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
public class PractitionerProvider implements IResourceProvider {

    public PractitionerProvider() {
        System.out.println("PractitionerProvider component initialized");
    }

    @Operation(name = "$obfuscateName", idempotent = true)
    public Parameters obfuscateName(
        @OperationParam(name = "oldName", min = 1) StringType theOldName
    ) {
        if (!theOldName.hasValue()) {
            throw new IllegalArgumentException(
                    "Parameter named 'oldName' must be present and have a non-empty string value");
        }
        
        Parameters retVal = new Parameters();
        retVal.addParameter().setName("oldName").setValue(theOldName);
        
        String obfuscated = UUID.nameUUIDFromBytes(theOldName.getValue().getBytes(StandardCharsets.UTF_8)).toString();
        retVal.addParameter().setName("newName").setValue(new StringType(obfuscated));
        
        return retVal;
    }

    @Override
    public Class<Practitioner> getResourceType() {
        return Practitioner.class;
    }
}