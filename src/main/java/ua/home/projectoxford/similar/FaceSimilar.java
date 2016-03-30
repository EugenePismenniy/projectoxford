package ua.home.projectoxford.similar;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.JSONObject;
import ua.home.projectoxford.detect.FaceDetector;
import ua.home.projectoxford.verify.FaceVerifier;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Evgeniy Pismenny 31.03.16 00:37
 */
public class FaceSimilar {


    static final String FIND_SIMILARS_URL = "https://api.projectoxford.ai/face/v1.0/findsimilars";

    public static void main(String[] args) {

        JSONObject json = new JSONObject();

        //json.put("faceId", FaceVerifier.faceId4);
        json.put("faceId", FaceVerifier.faceXId1);

        json.append("faceIds", FaceVerifier.faceId1);
        json.append("faceIds", FaceVerifier.faceId2);
        json.append("faceIds", FaceVerifier.faceId3);

        System.out.println(json.toString());


        Client client = Client.create();

        WebResource webResource = client.resource(FIND_SIMILARS_URL);

        ClientResponse response = webResource
                .header("Ocp-Apim-Subscription-Key", FaceDetector.KEY)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .post(ClientResponse.class, json.toString());
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

// face 4 in face 1 - 3
//    status code = 200
//    resp phrase = OK
//            [{"faceId":"2aeee5e8-db72-47d5-a3e3-5991ab6ca4e7","confidence":0.782843}
//        ,{"faceId":"a2675873-ff60-404d-a312-bc65ca4627ce","confidence":0.7348433},
//        {"faceId":"0cea8dc9-d058-4cb0-921b-555169c6e75e","confidence":0.6919607}]


// face x1 in face 1 - 3
//    status code = 200
//    resp phrase = OK
//[]
