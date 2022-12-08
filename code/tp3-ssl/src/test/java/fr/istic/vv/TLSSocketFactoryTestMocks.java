package fr.istic.vv;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class) 
public class TLSSocketFactoryTestMocks {

        // But : Tester la classe TLSSocketFactory indépendamment de SSLSocket et TLSProtocol en utilisant des classes Mock de substitution

        @Mock
        private SSLSocket socketMock;
        private TLSSocketFactory fMock;

        @Test
        public void preparedSocket_NullProtocols()  {

            // Préparation du contexte : mocks et comportements
            MockitoAnnotations.initMocks(this);
            socketMock = mock(SSLSocket.class);
            fMock = mock(TLSSocketFactory.class);

            when(socketMock.getSupportedProtocols()).thenReturn(null);
            when(socketMock.getEnabledProtocols()).thenReturn(null);
            doThrow(new AssertionError()).when(socketMock).setEnabledProtocols(any(String[].class));
            
            // Utilisation de la méthode à tester
            fMock.prepareSocket(socketMock);

            // Oracle ?
        };
        

        @Test
        public void typicalMock()  {

            MockitoAnnotations.initMocks(this);
            socketMock = mock(SSLSocket.class);
            fMock = mock(TLSSocketFactory.class);

            when(socketMock.getSupportedProtocols()).thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
            when(socketMock.getEnabledProtocols()).thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));
            doThrow(new AssertionError()).when(socketMock).setEnabledProtocols(new String[] {"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3" });
            
            fMock.prepareSocket(socketMock);

        }


        // Fonction supplémentaire au test qui n'a pas besoion d'être testée.
        private String[] shuffle(String[] in) {
            List<String> list = new ArrayList<String>(Arrays.asList(in));
            Collections.shuffle(list);
            return list.toArray(new String[0]);
        }

}


