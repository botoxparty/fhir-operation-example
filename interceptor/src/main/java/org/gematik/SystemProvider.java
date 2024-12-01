package org.gematik;

import ca.uhn.fhir.rest.annotation.Operation;
import org.hl7.fhir.r4.model.Parameters;
import org.hl7.fhir.r4.model.StringType;
import org.springframework.stereotype.Component;

@Component
public class SystemProvider {

    public static final String SYSTEM_OPERATION_NAME = "systemOperation";

    public SystemProvider() {
        System.out.println("SystemProvider component initialized");
    }

    @Operation(name = SYSTEM_OPERATION_NAME, idempotent = true)
    public Parameters systemOperation() {
        Parameters retVal = new Parameters();
        
        // First response
        Parameters.ParametersParameterComponent param1 = retVal.addParameter();
        param1.setName("response1");
        
        Parameters nestedParams1 = new Parameters();
        nestedParams1.addParameter()
            .setName("META_PARAM")
            .setValue(new StringType("META_PARAM_VALUE1"));
        param1.setResource(nestedParams1);
        
        // Second response
        Parameters.ParametersParameterComponent param2 = retVal.addParameter();
        param2.setName("response2");
        
        Parameters nestedParams2 = new Parameters();
        nestedParams2.addParameter()
            .setName("META_PARAM")
            .setValue(new StringType("META_PARAM_VALUE2"));
        param2.setResource(nestedParams2);
        
        return retVal;
    }
}