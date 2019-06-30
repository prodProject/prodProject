/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.FireBase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author shubham
 */
public class FireBaseConnector {

    public void getConnectToFirebase() throws FileNotFoundException, IOException {
        FileInputStream serviceAccount
                = new FileInputStream("src/main/resources/prod-project-239707-firebase-adminsdk-rtder-0b4a5c404f (1).json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setServiceAccountId("firebase-adminsdk-rtder@prod-project-239707.iam.gserviceaccount.com")
                .build();

        FirebaseApp status = FirebaseApp.initializeApp(options); 
       FirebaseApp.initializeApp().getOptions();
      
    
    }

}
