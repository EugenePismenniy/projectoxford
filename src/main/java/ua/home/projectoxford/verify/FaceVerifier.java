package ua.home.projectoxford.verify;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.JSONObject;
import ua.home.projectoxford.detect.FaceDetector;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * https://dev.projectoxford.ai/docs/services/563879b61984550e40cbbe8d/operations/563879b61984550f3039523a
 *
 * @author Evgeniy Pismenny 31.03.16 00:03
 */
public class FaceVerifier {

    static final String FACE_VERIFY_URL = "https://api.projectoxford.ai/face/v1.0/verify";

    public static final String faceId1 = "a2675873-ff60-404d-a312-bc65ca4627ce";
    public static final String faceId2 = "0cea8dc9-d058-4cb0-921b-555169c6e75e";
    public static final String faceId3 = "2aeee5e8-db72-47d5-a3e3-5991ab6ca4e7";
    public static final String faceId4 = "b4d17215-c66c-46ed-8d65-8eb7d5f642fa";
    public static final String faceXId1 = "80ad7ad2-28b0-4d5e-9bac-d750ad5f7553";

    public static void main(String[] args) {


        JSONObject reqJson = new JSONObject();
        reqJson.put("faceId1", faceId1);
        reqJson.put("faceId2", faceXId1);


        System.out.println(reqJson);

        Client client = Client.create();

        WebResource webResource = client.resource(FACE_VERIFY_URL);

        ClientResponse response = webResource
                .header("Ocp-Apim-Subscription-Key", FaceDetector.KEY)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .post(ClientResponse.class, reqJson.toString());
        try {


            Response.StatusType statusInfo = response.getStatusInfo();

            int statusCode = statusInfo.getStatusCode();
            System.out.println("status code = " + statusCode);
            System.out.println("resp phrase = " + statusInfo.getReasonPhrase());

            String entity = response.getEntity(String.class);

            System.out.println(entity);


        } finally {
            response.close();
        }




    }



}
// face 1 and face 2
//    status code = 200
//    resp phrase = OK
//{"isIdentical":true,"confidence":0.87224}

// face 1 and face 3
//    status code = 200
//    resp phrase = OK
//{"isIdentical":true,"confidence":0.7992}

// face 2 and face 3
//    status code = 200
//    resp phrase = OK
//{"isIdentical":true,"confidence":0.70414}

//  face 1 and face 4
//    status code = 200
//    resp phrase = OK
//{"isIdentical":true,"confidence":0.73484}

// face 3 and face 4
//    status code = 200
//    resp phrase = OK
//{"isIdentical":true,"confidence":0.78284}


// face 3 and face x1
//    status code = 200
//    resp phrase = OK
//{"isIdentical":false,"confidence":0.25026}

// face 1 and face x1
//    status code = 200
//    resp phrase = OK
//{"isIdentical":false,"confidence":0.25666}
