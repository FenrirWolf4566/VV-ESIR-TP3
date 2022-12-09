package fr.istic.vv;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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

        @Test
        public void preparedSocket_NullProtocols()  {

            // Préparation du contexte : mocks et comportements
            SSLSocket socketMock = mock(SSLSocket.class);

            when(socketMock.getSupportedProtocols()).thenReturn(null);
            when(socketMock.getEnabledProtocols()).thenReturn(null);

            // Utilisation de la méthode à tester
            TLSSocketFactory f = new TLSSocketFactory();
            f.prepareSocket(socketMock);

            Mockito.verify(socketMock, Mockito.never()).setEnabledProtocols(null);
        };

        @Test
        public void preparedSocket_OneAvailable(){
            SSLSocket socketMock = mock(SSLSocket.class);

            String[] tab = new String[]{TLSProtocol.TLSv1.getProtocolName()};

            when(socketMock.getSupportedProtocols()).thenReturn(tab);
            when(socketMock.getEnabledProtocols()).thenReturn(null);

            TLSSocketFactory f = new TLSSocketFactory();
            f.prepareSocket(socketMock);

            Mockito.verify(socketMock).setEnabledProtocols(tab);
        }

        @Test
        public void preparedSocket_OneEnabled(){
            SSLSocket socketMock = mock(SSLSocket.class);

            String tab[] = new String[]{TLSProtocol.TLSv1.getProtocolName()};

            when(socketMock.getEnabledProtocols()).thenReturn(tab);
            when(socketMock.getSupportedProtocols()).thenReturn(null);

            TLSSocketFactory f = new TLSSocketFactory();
            f.prepareSocket(socketMock);

            Mockito.verify(socketMock).setEnabledProtocols(tab);
        }

        @Test
        public void preparedSocket_NotExist(){
            SSLSocket socketMock = mock(SSLSocket.class);

            String tab[] = new String[]{"hello world"};

            when(socketMock.getSupportedProtocols()).thenReturn(tab);
            when(socketMock.getEnabledProtocols()).thenReturn(null);

            TLSSocketFactory f = new TLSSocketFactory();
            f.prepareSocket(socketMock);

            Mockito.verify(socketMock, Mockito.never()).setEnabledProtocols(null);
        }

        @Test
        public void preparedSocket_2elements(){
            SSLSocket socketMock = mock(SSLSocket.class);

            String tab[] = new String[]{
                    TLSProtocol.TLSv1.getProtocolName(),
                    TLSProtocol.TLSv1_2.getProtocolName()
            };

            when(socketMock.getSupportedProtocols()).thenReturn(new String[]{TLSProtocol.TLSv1.getProtocolName()});
            when(socketMock.getEnabledProtocols()).thenReturn(new String[]{TLSProtocol.TLSv1_2.getProtocolName()});

            TLSSocketFactory f = new TLSSocketFactory();
            f.prepareSocket(socketMock);

            Mockito.verify(socketMock).setEnabledProtocols(tab);
        }
        @Test
        public void preparedSocket_2elementsSame(){
            SSLSocket socketMock = mock(SSLSocket.class);

            String tab[] = new String[]{
                    TLSProtocol.TLSv1.getProtocolName()
            };

            when(socketMock.getSupportedProtocols()).thenReturn(tab);
            when(socketMock.getEnabledProtocols()).thenReturn(tab);

            TLSSocketFactory f = new TLSSocketFactory();
            f.prepareSocket(socketMock);

            Mockito.verify(socketMock).setEnabledProtocols(tab);
        }

//        @Test
//        public void typicalMock()  {
//            TLSSocketFactory f = new TLSSocketFactory();
//
//            MockitoAnnotations.initMocks(this);
//            SSLSocket socketMock = mock(SSLSocket.class);
//
//            when(socketMock.getSupportedProtocols()).thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
//            when(socketMock.getEnabledProtocols()).thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));
//            doThrow(new AssertionError()).when(socketMock).setEnabledProtocols(new String[] {"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3" });
//
//            f.prepareSocket(socketMock);
//
//        }
//
//
//        // Fonction supplémentaire au test qui n'a pas besoion d'être testée.
//        private String[] shuffle(String[] in) {
//            List<String> list = new ArrayList<String>(Arrays.asList(in));
//            Collections.shuffle(list);
//            return list.toArray(new String[0]);
//        }

}


