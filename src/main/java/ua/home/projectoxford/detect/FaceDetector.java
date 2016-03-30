package ua.home.projectoxford.detect;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * https://dev.projectoxford.ai/docs/services/563879b61984550e40cbbe8d/operations/563879b61984550f30395236
 *
 * @author Evgeniy Pismenny 30.03.16 22:45
 */
public class FaceDetector {


    // https://api.projectoxford.ai/face/v1.0/detect[?returnFaceId][&returnFaceLandmarks][&returnFaceAttributes]

    private static final String FACE_DETECT_URL =
            "https://api.projectoxford.ai/face/v1.0/detect?returnFaceId=true";


    public static final String KEY = "";

    public static void main(String[] args) throws FileNotFoundException {


        File file = new File("face_x1.png");

        Client client = Client.create();

        WebResource webResource = client.resource(FACE_DETECT_URL);

        ClientResponse response = webResource
                .header("Ocp-Apim-Subscription-Key", KEY)
                .type(MediaType.APPLICATION_OCTET_STREAM_TYPE)
                .post(ClientResponse.class, new FileInputStream(file));
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
// face 1

//    status code = 200
//    resp phrase = OK
//            [{"faceId":"a2675873-ff60-404d-a312-bc65ca4627ce","faceRectangle":{"top":280,"left":152,"width":365,"height":365}}]


// face 2
//    status code = 200
//    resp phrase = OK
//            [{"faceId":"0cea8dc9-d058-4cb0-921b-555169c6e75e","faceRectangle":{"top":142,"left":121,"width":178,"height":178}}]

// face 3
//    status code = 200
//    resp phrase = OK
//            [{"faceId":"2aeee5e8-db72-47d5-a3e3-5991ab6ca4e7","faceRectangle":{"top":308,"left":215,"width":362,"height":362}}]

// face 4
//    status code = 200
//    resp phrase = OK
//            [{"faceId":"b4d17215-c66c-46ed-8d65-8eb7d5f642fa","faceRectangle":{"top":75,"left":67,"width":77,"height":77}}]


// face x1
//    status code = 200
//    resp phrase = OK
//            [{"faceId":"80ad7ad2-28b0-4d5e-9bac-d750ad5f7553","faceRectangle":{"top":136,"left":144,"width":212,"height":212}}]
